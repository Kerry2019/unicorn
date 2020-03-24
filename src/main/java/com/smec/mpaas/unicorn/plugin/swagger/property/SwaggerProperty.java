package com.smec.mpaas.unicorn.plugin.swagger.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mpaas.unicorn.swagger")
public class SwaggerProperty {
    /**
     * 文档标题
     */
    private String title="Swagger接口文档";
    /**
     * 文档描述
     */
    private String description;
    /**
     * 文档版本
     */
    private String version="v1.0";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

