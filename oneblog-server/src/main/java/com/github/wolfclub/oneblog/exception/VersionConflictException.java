package com.github.wolfclub.oneblog.exception;

import java.text.MessageFormat;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
public class VersionConflictException extends Exception{
    public VersionConflictException() {
        super();
    }

    public VersionConflictException(Throwable cause) {
        super(cause);
    }

    public VersionConflictException(String pattern, Object... arguments) {
        super(MessageFormat.format(pattern, arguments));
    }

    public VersionConflictException(Throwable cause, String pattern, Object... arguments) {
        super(MessageFormat.format(pattern, arguments), cause);
    }
}
