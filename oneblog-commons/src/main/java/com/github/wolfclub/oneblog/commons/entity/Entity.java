package com.github.wolfclub.oneblog.commons.entity;

import java.io.Serializable;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
public class Entity implements Serializable, HasId {
    private static final long serialVersionUID = -868062373779567033L;

    private Long id;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Entity setId(Long id) {
        this.id = id;
        return this;
    }
}
