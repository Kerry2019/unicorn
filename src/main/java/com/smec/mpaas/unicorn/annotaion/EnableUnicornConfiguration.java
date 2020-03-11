package com.smec.mpaas.unicorn.annotaion;

import com.smec.mpaas.unicorn.config.UnicornAutoConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: kerry.wu
 * @since: 2020/3/6 23:37
 * @history: 1.2020/3/6 created by kerry.wu
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({UnicornAutoConfig.class})
public @interface EnableUnicornConfiguration {
}
