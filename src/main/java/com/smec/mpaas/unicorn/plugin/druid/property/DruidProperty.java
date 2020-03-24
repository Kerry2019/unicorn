package com.smec.mpaas.unicorn.plugin.druid.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: kerry.wu
 * @since: 2020/3/24 8:31
 * @history: 1.2020/3/24 created by kerry.wu
 */

@ConfigurationProperties(prefix = "mpaas.unicorn.druid")
public class DruidProperty {
    /**
     * 监控页面-账号
     */
    private String loginUsername="admin";
    /**
     * 监控页面-密码
     */
    private String loginPassword="password";

    public String getLoginUsername() {
        return loginUsername;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
}
