package com.fangqing.annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
 
/**
 * @���� TODO
 *
 *  @author FangQing
 *  @date 2016��6��25�� 
 *  @time ����12:42:41
 */
@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodInfo{
    
    String author() default "FangQing";
    
    String date();
    
    int revision() default 1;
    
    String comments();
}