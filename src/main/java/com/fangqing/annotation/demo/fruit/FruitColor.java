package com.fangqing.annotation.demo.fruit;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @���� ˮ����ɫע��
 *
 *  @author FangQing
 *  @date 2016��6��25�� 
 *  @time ����11:27:40
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {
    
    /**
     * ��ɫö��
     * @author FangQing
     *
     */
    public enum Color{ BULE,RED,GREEN};
    
    /**
     * ��ɫ����
     * @return
     */
    Color fruitColor() default Color.GREEN;

}