package com.wuyu.easyid.base.factory;

import com.wuyu.easyid.base.generator.IdGenerator;

import java.util.concurrent.ConcurrentHashMap;

/**
 * ID生成抽象工厂.
 */
public abstract class AbstractIdGeneratorFactory implements IdGeneratorFactory {

    /**
     * 业务类型ID生成器缓存.
     */
    private static ConcurrentHashMap<Integer, IdGenerator> generatorCache = new ConcurrentHashMap<>();

    @Override
    public IdGenerator getIdGenerator(Integer bizType) {
        if (generatorCache.containsKey(bizType)) {
            return generatorCache.get(bizType);
        }
        synchronized (this) {
            if (generatorCache.containsKey(bizType)) {
                return generatorCache.get(bizType);
            }
            IdGenerator idGenerator = createIdGenerator(bizType);
            generatorCache.put(bizType, idGenerator);
            return idGenerator;
        }
    }

    /**
     * 根据bizType创建id生成器.
     *
     * @param bizType 业务类型
     * @return ID生成器
     */
    public abstract IdGenerator createIdGenerator(Integer bizType);
}
