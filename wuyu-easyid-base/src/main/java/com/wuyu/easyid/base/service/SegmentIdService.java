package com.wuyu.easyid.base.service;


import com.wuyu.easyid.base.entity.SegmentId;

/**
 * ID块业务对象.
 * 
 * @author zhouhongjun
 */
public interface SegmentIdService {

    /**
     * 根据bizType获取下一个ID块.
     * 
     * @param bizType 业务类型
     * @return ID块
     */
    SegmentId getNextSegmentId(Integer bizType);

}
