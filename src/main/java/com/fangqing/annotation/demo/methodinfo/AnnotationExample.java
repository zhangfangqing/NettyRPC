package com.fangqing.annotation.demo.methodinfo;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
 
/**
 * @���� TODO
 *
 *  @author FangQing
 *  @date 2016��6��25�� 
 *  @time ����12:45:14
 */
public class AnnotationExample {
 
    public static void main(String[] args) {
    }
 
    @Override
    @MethodInfo(author = "FangQing", comments = "Main method", date = "Nov 17 2012", revision = 1)
    public String toString() {
        return "Overriden toString method";
    }
 
    @Deprecated
    @MethodInfo(comments = "deprecated method", date = "Nov 17 2012")
    public static void oldMethod() {
        System.out.println("old method, don't use it.");
    }
 
    @MethodInfo(author = "FangQing", comments = "Main method", date = "Nov 17 2012", revision = 10)
    public static void genericsTest() throws FileNotFoundException {
        List<String> list = new ArrayList<String>();
        list.add("abc");
        oldMethod();
    }
    
}
 