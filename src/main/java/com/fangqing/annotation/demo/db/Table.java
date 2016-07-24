package com.fangqing.annotation.demo.db;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @���� �Զ������ݱ�ע��
 *
 *  @author FangQing
 *  @date 2016��6��25�� 
 *  @time ����1:01:17
 */
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Table {
    
    String value() default "";
    
}
