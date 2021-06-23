package com.wuyu.easyid.base.entity;

import java.util.concurrent.atomic.AtomicLong;

/**
 * ID代码块.
 *
 * @author zhouhongjun
 */
public class SegmentId {
    /**
     * 最大ID.
     */
    private long maxId;
    /**
     * 当前ID.
     */
    private AtomicLong currentId;
    /**
     * 增长步伐.
     */
    private int delta = 1;

    /**
     * 需要加载ID.
     */
    private long loadingId;

    /**
     * 构造ID块.
     *
     * @param newMaxId 新的最大ID
     * @param step     步长
     */
    public SegmentId(long newMaxId, int step) {
        this.maxId = newMaxId;
        this.currentId = new AtomicLong(newMaxId - step + 1);
        this.loadingId = newMaxId * 20 / 100;
    }

    /**
     * 下一个ID.
     *
     * @return 返回下一个ID
     */
    public Long nextId() {
        Long id = currentId.getAndAdd(delta);
        if (id > maxId) {
            return -1L;
        }
        return id;
    }

    /**
     * 当前ID是否可用.
     *
     * @return 是否可用
     */
    public boolean useful() {
        return currentId.get() <= maxId;
    }

    /**
     * 是否需要加载ID块.
     *
     * @return 需要获不需要
     */
    public boolean needLoading() {
        return currentId.get() >= loadingId;
    }

    public long getLoadingId() {
        return loadingId;
    }

    public void setLoadingId(long loadingId) {
        this.loadingId = loadingId;
    }

    public long getMaxId() {
        return maxId;
    }

    public void setMaxId(long maxId) {
        this.maxId = maxId;
    }

    public AtomicLong getCurrentId() {
        return currentId;
    }

    public void setCurrentId(AtomicLong currentId) {
        this.currentId = currentId;
    }

    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }
}
