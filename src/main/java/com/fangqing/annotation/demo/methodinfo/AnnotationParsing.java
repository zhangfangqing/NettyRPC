package com.fangqing.annotation.demo.methodinfo;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
 
/**
 * @功能 TODO
 *
 *  @author FangQing
 *  @date 2016年6月25日 
 *  @time 下午12:48:38
 */
public class AnnotationParsing {
 
    /**
     * @功能 TODO
     *
     *  @author FangQing
     *  @date 2016年6月25日 
     *  @time 下午12:53:04
     */
    public static void main(String[] args) throws Exception {
        for (Method method : AnnotationParsing.class.getClassLoader().loadClass(("com.fangqing.annotation.demo.methodinfo.AnnotationExample")).getMethods()) {
            if (method.isAnnotationPresent(com.fangqing.annotation.demo.methodinfo.MethodInfo.class)) {
                try {
                    for (Annotation anno : method.getDeclaredAnnotations()) {
                        System.out.println("Annotation in Method '" + method + "' : " + anno);
                    }
                    MethodInfo methodAnno = method.getAnnotation(MethodInfo.class);
                    if (methodAnno.revision() == 1) {
                        System.out.println("Method with revision no 1 = " + method);
                    }
                } catch (Throwable ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
 
}