package com.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 */
@Target(ElementType.TYPE) //声明注解的作用域
@Retention(RetentionPolicy.CLASS) //声明注解的生命周期 java -->class --> jvm
public @interface BindPath {
    String value();
}
