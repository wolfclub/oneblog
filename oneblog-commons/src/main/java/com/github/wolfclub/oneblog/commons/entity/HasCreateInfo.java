package com.github.wolfclub.oneblog.commons.entity;

import java.util.Date;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
public interface HasCreateInfo {

    /**
     * 获取创建人编码
     * @return
     */
    String getCreatorCode();

    /**
     * 设置创建人编码
     * @param creatorCode
     * @return
     */
    HasCreateInfo setCreatorCode(String creatorCode);

    /**
     * 获取创建人名称
     * @return
     */
    String getCreatorName();

    /**
     * 设置创建人名称
     * @param creatorName
     * @return
     */
    HasCreateInfo setCreatorName(String creatorName);

    /**
     * 获取创建时间
     * @return
     */
    Date getCreateTime();

    /**
     * 设置创建时间
     * @param createTime
     * @return
     */
    HasCreateInfo setCreateTime(Date createTime);
}
