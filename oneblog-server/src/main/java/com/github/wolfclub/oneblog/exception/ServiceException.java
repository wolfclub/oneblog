package com.github.wolfclub.oneblog.exception;

import java.text.MessageFormat;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
public class ServiceException extends Exception{

    public ServiceException() {
        super();
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String pattern, Object... arguments) {
        super(MessageFormat.format(pattern, arguments));
    }

    public ServiceException(Throwable cause, String pattern, Object... arguments) {
        super(MessageFormat.format(pattern, arguments), cause);
    }
}
