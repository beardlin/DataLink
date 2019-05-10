package com.ucar.datalink.biz.utils;

import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * Created by lubiao on 2017/1/4.
 */
public class DataLinkFactory {

    @SuppressWarnings("unchecked")
    public static <T> T getObject(String name) {
        return (T) ContextHolder.getApplicationContext().getBean(name);
    }

    public static <T> T getObject(Class<? extends T> classz) {
        return (T) ContextHolder.getApplicationContext().getBean(classz);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        ApplicationContext applicationContext = ContextHolder.getApplicationContext();
        return applicationContext.getBeansOfType(clazz);
    }
}
