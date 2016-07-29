package com.fangqing.annotation;
import java.lang.annotation.Documented;  
import java.lang.annotation.ElementType;  
import java.lang.annotation.Inherited;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  
  
/**
 * @功能 自定义数据列注解
 *
 *  @author FangQing
 *  @date 2016年6月25日 
 *  @time 下午1:02:36
 */
@Inherited  
@Target({ElementType.FIELD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public @interface Column {
    
    String value() default "";  
    
}  