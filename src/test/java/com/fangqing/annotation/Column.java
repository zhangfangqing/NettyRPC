package com.fangqing.annotation;
import java.lang.annotation.Documented;  
import java.lang.annotation.ElementType;  
import java.lang.annotation.Inherited;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  
  
/**
 * @���� �Զ���������ע��
 *
 *  @author FangQing
 *  @date 2016��6��25�� 
 *  @time ����1:02:36
 */
@Inherited  
@Target({ElementType.FIELD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public @interface Column {
    
    String value() default "";  
    
}  