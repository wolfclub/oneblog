package com.github.wolfclub.oneblog.commons.jpa;


import com.github.wolfclub.oneblog.commons.entity.HasId;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
public class PEntity implements Serializable, HasId {
    private static final long serialVersionUID = 6222185321222376682L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public PEntity setId(Long id) {
        this.id = id;
        return this;
    }
}
