package com.fangqing.annotation.demo.fruit;

import java.lang.reflect.Field;

/**
 * @���� ע�⴦����
 *
 *  @author FangQing
 *  @date 2016��6��25�� 
 *  @time ����12:30:41
 */
public class FruitInfoUtil {
    
    /**
     * @���� TODO
     *
     *  @author FangQing
     *  @date 2016��6��25�� 
     *  @time ����12:32:06
     */
    public static void getFruitInfo(Class<?> clazz){
        String strFruitName=" ˮ�����ƣ�";
        String strFruitColor=" ˮ����ɫ��";
        String strFruitProvicer="��Ӧ����Ϣ��";
        
        Field[] fields = clazz.getDeclaredFields();
        for(Field field :fields){
            if(field.isAnnotationPresent(FruitName.class)){
                FruitName fruitName = (FruitName) field.getAnnotation(FruitName.class);
                strFruitName=strFruitName+fruitName.value();
                System.out.println(strFruitName);
            }
            else if(field.isAnnotationPresent(FruitColor.class)){
                FruitColor fruitColor= (FruitColor) field.getAnnotation(FruitColor.class);
                strFruitColor=strFruitColor+fruitColor.fruitColor().toString();
                System.out.println(strFruitColor);
            }
            else if(field.isAnnotationPresent(FruitProvider.class)){
                FruitProvider fruitProvider= (FruitProvider) field.getAnnotation(FruitProvider.class);
                strFruitProvicer=" ��Ӧ�̱�ţ�"+fruitProvider.id()+" ��Ӧ�����ƣ�"+fruitProvider.name()+" ��Ӧ�̵�ַ��"+fruitProvider.address();
                System.out.println(strFruitProvicer);
            }
        }
    }
}