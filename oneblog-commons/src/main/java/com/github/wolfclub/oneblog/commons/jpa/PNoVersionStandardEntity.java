package com.github.wolfclub.oneblog.commons.jpa;

import com.github.wolfclub.oneblog.commons.entity.HasCreateInfo;
import com.github.wolfclub.oneblog.commons.entity.HasLastModifyInfo;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
public class PNoVersionStandardEntity extends PEntity implements HasCreateInfo,HasLastModifyInfo {
  private static final long serialVersionUID = -5010950422726537120L;
  
    @Column(name = "creator_code")
    private String creatorCode;
    @Column(name = "creator_name")
    private String creatorName;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "last_modifier_code")
    private String lastModifierCode;
    @Column(name = "last_modifier_name")
    private String lastModifierName;
    @Column(name = "last_modify_time")
    private Date lastModifyTime;

    @Override
    public String getCreatorCode() {
        return creatorCode;
    }

    @Override
    public PNoVersionStandardEntity setCreatorCode(String creatorCode) {
        this.creatorCode = creatorCode;
        return this;
    }

    @Override
    public String getCreatorName() {
        return creatorName;
    }

    @Override
    public PNoVersionStandardEntity setCreatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public PNoVersionStandardEntity setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    @Override
    public String getLastModifierCode() {
        return lastModifierCode;
    }

    @Override
    public PNoVersionStandardEntity setLastModifierCode(String lastModifierCode) {
        this.lastModifierCode = lastModifierCode;
        return this;
    }

    @Override
    public String getLastModifierName() {
        return lastModifierName;
    }

    @Override
    public PNoVersionStandardEntity setLastModifierName(String lastModifierName) {
        this.lastModifierName = lastModifierName;
        return this;
    }

    @Override
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    @Override
    public PNoVersionStandardEntity setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
        return this;
    }
}
