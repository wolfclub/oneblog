package com.github.wolfclub.oneblog.commons.entity;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
public interface HasId {

    /**
     * 获取id
     * @return
     */
    Long getId();

    /**
     * 设置id
     * @param id
     * @return
     */
    HasId setId(Long id);
}
