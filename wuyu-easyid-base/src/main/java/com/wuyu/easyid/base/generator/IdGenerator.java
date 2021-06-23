package com.wuyu.easyid.base.generator;

import java.util.List;

/**
 * ID生成器.
 * @author zhouhongjun
 */
public interface IdGenerator {
    /**
     * 获取下一个ID.
     * @return 下一个ID
     */
    Long getNextId();

    /**
     * 获取下一批ID.
     * @param batchSize 需要获取的个数
     * @return 下一批ID
     */
    List<Long> getNextIds(Integer batchSize);
}
