package com.smec.mpaas.unicorn.plugin.druid.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import org.springframework.stereotype.Component;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: kerry.wu
 * @since: 2020/3/23 12:41
 * @history: 1.2020/3/23 created by kerry.wu
 */

//@Component
public class BeanPostProcessorImpl implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        System.out.println("---- "+beanName);
//        EnableUnicornDruid enableUnicornDruid = AnnotationUtils.findAnnotation(bean.getClass(), EnableUnicornDruid.class);
//        if (null != enableUnicornDruid) {
//
//        }
        return bean;
    }



}
