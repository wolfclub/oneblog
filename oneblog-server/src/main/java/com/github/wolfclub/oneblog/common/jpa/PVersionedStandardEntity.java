package com.github.wolfclub.oneblog.common.jpa;

import com.github.wolfclub.oneblog.commons.entity.Versionable;
import com.github.wolfclub.oneblog.commons.jpa.PNoVersionStandardEntity;
import tk.mybatis.mapper.annotation.Version;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
public class PVersionedStandardEntity extends PNoVersionStandardEntity implements Versionable {
    private static final long serialVersionUID = -4671605873941668916L;

    @Version
    private Long version;
    @Column(name = "version_time")
    private Date versionTime;

    @Override
    public Long getVersion() {
        return version;
    }

    @Override
    public PVersionedStandardEntity setVersion(Long version) {
        this.version = version;
        return this;
    }

    @Override
    public Date getVersionTime() {
        return versionTime;
    }

    @Override
    public PVersionedStandardEntity setVersionTime(Date versionTime) {
        this.versionTime = versionTime;
        return this;
    }
}
