package com.fangqing.annotation.demo.methodinfo;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
 
/**
 * @功能 TODO
 *
 *  @author FangQing
 *  @date 2016年6月25日 
 *  @time 下午12:45:14
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
 