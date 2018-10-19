package com.test.demo.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 通用的缓存数据
 */

//指定注解的生命周期(源码、class文件、运行时),其参考值见类的定义:java.lang.annotation.RetentionPolicy
@Retention(RetentionPolicy.RUNTIME)
//指定注解使用的目标范围(类、方法、字段等),其参考值见类的定义:java.lang.annotation.ElementType
@Target(ElementType.METHOD)
public @interface CommonRedis {
    public String key();
}
