package com.github.wolfclub.oneblog.commons.entity;

import java.io.Serializable;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
public class Operator implements Serializable{
    private static final long serialVersionUID = -2980370733840401768L;

    private String operatorCode;
    private String operatorName;

    public Operator() {
    }

    public Operator(String operatorCode, String operatorName) {
        this.operatorCode = operatorCode;
        this.operatorName = operatorName;
    }

    public static Operator newInstance(Operator source){
        if(source == null){
            return new Operator();
        }
        return new Operator().setOperatorCode(source.getOperatorCode())
                .setOperatorName(source.getOperatorName());
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public Operator setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
        return this;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public Operator setOperatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }

}
