package com.smec.mpaas.unicorn.plugin.druid.annotation;

import com.smec.mpaas.unicorn.plugin.druid.config.DruidConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({DruidConfig.class})
public @interface UnicornPluginDruid {

}
