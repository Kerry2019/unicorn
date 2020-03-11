package com.smec.mpaas.unicorn.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Optional;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: kerry.wu
 * @since: 2020/3/6 22:53
 * @history: 1.2020/3/6 created by kerry.wu
 */
@ConfigurationProperties(prefix = "mpaas.unicorn.security")
public class SecurityProperty {
    public enum MODE_ENUM {
        oam,
        adfs,
        custom
    }

    public static String HEADER_NAME_UID = "uid";

    /**
     * 是否开启 安全限制
     */
    private boolean open;
    /**
     * 开放登录权限的路由（多个用逗号隔开，可写正则表达式）
     * 宽松规则绑定，可以绑定配置文件中的 public_route、public-route、publicRoute、publicroute、PUBLIC_ROUTE
     */
    private String publicRoute;
    /**
     * oam/adfs/custom
     */
    private String mode;
    /**
     * header中参数命名
     */
    private String headerName;

    /**
     * 如果headerName有值，则返回，否则默认返回 uid
     *
     * @return
     */
    public String headName() {
        return Optional.ofNullable(headerName).orElse(HEADER_NAME_UID);
    }


    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getPublicRoute() {
        return publicRoute;
    }

    public void setPublicRoute(String publicRoute) {
        this.publicRoute = publicRoute;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }
}
