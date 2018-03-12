package com.github.wolfclub.oneblog.commons.entity;

import java.util.Date;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
public class NoVersionStandardEntity extends Entity implements HasCreateInfo,HasLastModifyInfo {
    private static final long serialVersionUID = -5565679795547332348L;

    private String creatorCode;
    private String creatorName;
    private Date createTime;
    private String lastModifierCode;
    private String lastModifierName;
    private Date lastModifyTime;

    @Override
    public String getCreatorCode() {
        return creatorCode;
    }

    @Override
    public NoVersionStandardEntity setCreatorCode(String creatorCode) {
        this.creatorCode = creatorCode;
        return this;
    }

    @Override
    public String getCreatorName() {
        return creatorName;
    }

    @Override
    public NoVersionStandardEntity setCreatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public NoVersionStandardEntity setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    @Override
    public String getLastModifierCode() {
        return lastModifierCode;
    }

    @Override
    public NoVersionStandardEntity setLastModifierCode(String lastModifierCode) {
        this.lastModifierCode = lastModifierCode;
        return this;
    }

    @Override
    public String getLastModifierName() {
        return lastModifierName;
    }

    @Override
    public NoVersionStandardEntity setLastModifierName(String lastModifierName) {
        this.lastModifierName = lastModifierName;
        return this;
    }

    @Override
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    @Override
    public NoVersionStandardEntity setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
        return this;
    }
}
