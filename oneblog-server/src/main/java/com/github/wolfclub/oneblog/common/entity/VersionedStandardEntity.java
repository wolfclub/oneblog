package com.github.wolfclub.oneblog.common.entity;

import com.github.wolfclub.oneblog.commons.entity.NoVersionStandardEntity;
import com.github.wolfclub.oneblog.commons.entity.Versionable;

import java.util.Date;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
public class VersionedStandardEntity extends NoVersionStandardEntity implements Versionable {
    private static final long serialVersionUID = 6496589828441769110L;

    private Long version;
    private Date versionTime;

    @Override
    public Long getVersion() {
        return version;
    }

    @Override
    public VersionedStandardEntity setVersion(Long version) {
        this.version = version;
        return this;
    }

    @Override
    public Date getVersionTime() {
        return versionTime;
    }

    @Override
    public VersionedStandardEntity setVersionTime(Date versionTime) {
        this.versionTime = versionTime;
        return this;
    }
}
