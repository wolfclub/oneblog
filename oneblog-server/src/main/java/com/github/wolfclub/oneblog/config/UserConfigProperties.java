package com.github.wolfclub.oneblog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
@ConfigurationProperties(prefix = "user.config")
@Validated
public class UserConfigProperties {
    @NotNull
    private String loginName;
    @NotNull
    private String password;
    @NotNull
    private String email;

    public String getLoginName() {
        return loginName;
    }

    public UserConfigProperties setLoginName(String loginName) {
        this.loginName = loginName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserConfigProperties setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserConfigProperties setEmail(String email) {
        this.email = email;
        return this;
    }
}
