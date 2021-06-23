package com.wuyu.easyid.base.factory;


import com.wuyu.easyid.base.generator.IdGenerator;

/**
 * ID生成服务接口.
 */
public interface IdGeneratorFactory {
    /**
     * 根据bizType创建id生成器.
     * 
     * @param bizType 业务类型
     * @return ID 生成器
     */
    IdGenerator getIdGenerator(Integer bizType);
}
