package com.smec.mpaas.unicorn.comm.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;
import java.util.Optional;


@ConfigurationProperties(prefix = "mpaas.unicorn.security")
@Validated
public class SecurityProperty {
    public enum MODE_ENUM {
        simple,
        jwks,
        custom
    }

    /**
     * 是否开启接口安全
     */
    private boolean open=false;
    /**
     * 开放登录权限的路由（多个用逗号隔开，可写正则表达式）
     * 宽松规则绑定，可以绑定配置文件中的 public_route、public-route、publicRoute、publicroute、PUBLIC_ROUTE
     */
    private String publicRoute;
    /**
     * simple/jwks/custom
     */
    @Pattern(regexp = "^simple$|^jwks$|^custom$",message = "请在三者中选择一个值：simple、jwks、custom")
    private String mode=MODE_ENUM.simple.name();
    /**
     * header中参数命名
     */
    private String headerName="uid";
    /**
     * jwks,公钥地址
     */
    private String jwksUri;


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

    public String getJwksUri() {
        return jwksUri;
    }

    public void setJwksUri(String jwksUri) {
        this.jwksUri = jwksUri;
    }
}
