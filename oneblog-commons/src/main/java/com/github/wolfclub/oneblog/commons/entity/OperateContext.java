package com.github.wolfclub.oneblog.commons.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
public class OperateContext implements Serializable{
  private static final long serialVersionUID = 5822611094461317487L;
  
    private Operator operator;
    private Date time;
    private Map<String, Serializable> attributes = new HashMap<>();

    public OperateContext() {
        this(null, null);
    }

    public OperateContext(Operator operator) {
        this(operator, null);
    }

    public OperateContext(Operator operator, Date time) {
        if(operator == null){
            this.operator = null;
        }else{
            this.operator = Operator.newInstance(operator);
        }

        this.time = time == null ? new Date() : time;
    }

    public Operator getOperator() {
        return operator;
    }

    public OperateContext setOperator(Operator operator) {
        this.operator = operator;
        return this;
    }

    public Date getTime() {
        return time;
    }

    public OperateContext setTime(Date time) {
        this.time = time;
        return this;
    }

    public Map<String, Serializable> getAttributes() {
        return attributes;
    }

    public OperateContext setAttributes(Map<String, Serializable> attributes) {
        this.attributes = attributes;
        return this;
    }
}
