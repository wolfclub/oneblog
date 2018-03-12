package com.github.wolfclub.oneblog.commons.entity;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
public interface HasVersion {

    /**
     * 获取版本号
     * @return
     */
    Long getVersion();

    /**
     * 设置版本号
     * @param version
     * @return
     */
    HasVersion setVersion(Long version);
}
