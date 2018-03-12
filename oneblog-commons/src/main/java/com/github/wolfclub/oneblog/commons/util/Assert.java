package com.github.wolfclub.oneblog.commons.util;

import java.text.MessageFormat;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
public class Assert {

    /**
     * 判断参数是否为null
     * @param object
     * @param objectName
     * @throws IllegalArgumentException
     */
    public static void assertArgumentNotNull(Object object, String objectName) throws IllegalArgumentException {
        if(object == null){
            throw new IllegalArgumentException(MessageFormat.format("参数{0}不能为null", objectName));
        }
    }
}
