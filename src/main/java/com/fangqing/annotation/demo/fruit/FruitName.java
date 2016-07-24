package com.fangqing.annotation.demo.fruit;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @���� ˮ������ע��
 *
 * @author FangQing
 * @date 2016��6��25��
 * @time ����11:25:56
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName {
    
    String value() default "";
    
}