package com.fangqing.annotation.demo.fruit;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @功能 水果名称注解
 *
 * @author FangQing
 * @date 2016年6月25日
 * @time 上午11:25:56
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName {
    
    String value() default "";
    
}