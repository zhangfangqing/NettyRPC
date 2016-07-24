package com.fangqing.annotation.demo.db;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @���� TODO
 *
 *  @author FangQing
 *  @date 2016��6��25�� 
 *  @time ����1:09:16
 */
public class TestDbAnnotation {

    public static void main(String[] args) {
	TestDto testDto = new TestDto("123", "34");
	TestDto testDto1 = new TestDto("123", "test1");
	TestDto testDto2 = new TestDto("", "test1,test2,test3,test4");
	String sql = assembleSqlFromObj(testDto);
	String sql1 = assembleSqlFromObj(testDto1);
	String sql2 = assembleSqlFromObj(testDto2);
	System.out.println(sql);
	System.out.println(sql1);
	System.out.println(sql2);
    }

    /**
     * ͨ��ע������װ��ѯ���������ɲ�ѯ���
     * 
     * @param obj
     * @return
     */
    public static String assembleSqlFromObj(Object obj) {
	Table table = obj.getClass().getAnnotation(Table.class);
	StringBuffer sbSql = new StringBuffer();
	String tableName = table.value();
	sbSql.append("select * from " + tableName + " where 1=1 ");
	Field[] fileds = obj.getClass().getDeclaredFields();
	for (Field field : fileds) {
	    String fieldName = field.getName();
	    String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	    try {
		Column column = field.getAnnotation(Column.class);
		if (column != null) {
		    Method method = obj.getClass().getMethod(methodName);
		    String value = (String) method.invoke(obj);
		    if (value != null && !value.equals("")) {
			if (!isNum(column.value()) && !isNum(value)) {
			    // �жϲ����ǲ��� in ���Ͳ��� 1,2,3
			    if (value.contains(",")) {
				sbSql.append(" and " + column.value() + " in (" + value + ") ");
			    } else {
				sbSql.append(" and " + column.value() + " like '%" + value + "%' ");
			    }
			} else {
			    sbSql.append(" and " + column.value() + "=" + value + " ");
			}
		    }
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	return sbSql.toString();
    }

    /**
     * ��������ֵ�ǲ��� id ���� 1.����ֶ����� 2.����ֶ�ֵ
     * 
     * @param target
     * @return
     */
    public static boolean isNum(String target) {
	boolean isNum = false;
	if (target.toLowerCase().contains("id")) {
	    isNum = true;
	}
	if (target.matches("\\d+")) {
	    isNum = true;
	}
	return isNum;
    }
}
