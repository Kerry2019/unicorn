package com.smec.mpaas.unicorn.annotaion;

import com.smec.mpaas.unicorn.config.UnicornAutoConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({UnicornAutoConfig.class})
public @interface EnableUnicornConfiguration {
}
