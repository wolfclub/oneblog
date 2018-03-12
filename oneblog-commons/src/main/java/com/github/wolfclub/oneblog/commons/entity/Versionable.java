package com.github.wolfclub.oneblog.commons.entity;

import java.util.Date;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
public interface Versionable extends HasVersion{

    /**
     * 获取版本时间
     * @return
     */
    Date getVersionTime();

    /**
     * 设置版本时间
     * @param versionTime
     * @return
     * @throws UnsupportedOperationException 实现类不提供修改时抛出
     */
    Versionable setVersionTime(Date versionTime) throws UnsupportedOperationException;
}
