package com.smec.mpaas.unicorn.comm.annotation;

import com.smec.mpaas.unicorn.comm.config.UnicornAutoConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({UnicornAutoConfig.class})
public @interface EnableUnicorn {
}
