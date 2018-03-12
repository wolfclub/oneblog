package com.github.wolfclub.oneblog.commons.entity;

import java.util.Date;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
public interface HasLastModifyInfo {

    /**
     * 获取修改人编码
     * @return
     */
    String getLastModifierCode();

    /**
     * 设置修改人编码
     * @param lastModifierCode
     * @return
     */
    HasLastModifyInfo setLastModifierCode(String lastModifierCode);

    /**
     * 获取修改人名称
     * @return
     */
    String getLastModifierName();

    /**
     * 设置修改人名称
     * @param lastModifierName
     * @return
     */
    HasLastModifyInfo setLastModifierName(String lastModifierName);

    /**
     * 获取修改时间
     * @return
     */
    Date getLastModifyTime();

    /**
     * 设置修改时间
     * @param lastModifyTime
     * @return
     */
    HasLastModifyInfo setLastModifyTime(Date lastModifyTime);
}
