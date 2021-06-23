package com.wuyu.easyid.base.generator;


import com.wuyu.easyid.base.entity.SegmentId;
import com.wuyu.easyid.base.service.SegmentIdService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ID块缓存.
 */
public class SegmentIdCache implements IdGenerator {
    /**
     * 业务类型.
     */
    protected Integer bizType;
    /**
     * ID块服务.
     */
    protected SegmentIdService segmentIdService;
    /**
     * 当前ID块.
     */
    protected volatile SegmentId current;
    /**
     * 下一个ID块.
     */
    protected volatile SegmentId next;
    /**
     * 是否正在加载下一个代码块.
     */
    private volatile boolean isLoadingNext;
    /**
     * 创建单个线程池服务.
     */
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    /**
     * 构造ID块缓存.
     *
     * @param bizType          业务类型
     * @param segmentIdService ID块服务
     */
    public SegmentIdCache(Integer bizType, SegmentIdService segmentIdService) {
        this.bizType = bizType;
        this.segmentIdService = segmentIdService;
        loadSegmentId();
    }

    /**
     * 加载ID块.
     */
    private synchronized void loadSegmentId() {
        if (current == null || !current.useful()) {
            //第一次
            if (next == null) {
                //加载当前ID块
                SegmentId currentSegmentId = getSegmentId();
                this.current = currentSegmentId;
                //后续只需要跳转到下一个ID块
            } else {
                current = next;
                next = null;
            }
        }
    }

    /**
     * 获取ID块.
     *
     * @return ID块
     */
    private SegmentId getSegmentId() {
        String message = null;
        try {
            SegmentId segmentId = segmentIdService.getNextSegmentId(bizType);
            if (segmentId != null) {
                return segmentId;
            }
        } catch (Exception e) {
            message = e.getMessage();
        }
        throw new RuntimeException("error query segmentId: " + message);
    }

    /**
     * 加载下一个ID块.
     */
    private synchronized void loadNextSegmentId() {
        if (next == null && !isLoadingNext) {
            isLoadingNext = true;
            executorService.submit(() -> {
                SegmentId segmentId = getSegmentId();
                next = segmentId;
                isLoadingNext = false;
            });
        }
    }

    @Override
    public Long getNextId() {
        while (true) {
            //初始化失败
            if (current == null) {
                loadSegmentId();
                continue;
            }
            long id = current.nextId();
            //没有ID可使用
            if (id == -1) {
                loadSegmentId();
            } else {
                //异步加载下一个ID块
                if (next == null && current.needLoading()) {
                    loadNextSegmentId();
                }
                return id;
            }
        }
    }

    @Override
    public List<Long> getNextIds(Integer batchSize) {
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < batchSize; i++) {
            Long id = getNextId();
            ids.add(id);
        }
        return ids;
    }

}
