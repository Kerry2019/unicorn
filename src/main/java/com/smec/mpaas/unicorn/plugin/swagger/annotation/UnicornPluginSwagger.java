package com.smec.mpaas.unicorn.plugin.swagger.annotation;


import com.smec.mpaas.unicorn.plugin.swagger.config.SwaggerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({SwaggerConfig.class})
public @interface UnicornPluginSwagger {
}
