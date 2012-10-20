package net.sf.minuteProject.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.MethodUtils;

import net.sf.minuteProject.configuration.bean.Condition;
import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.configuration.bean.model.data.Column;

public class StringUtils {

	public static boolean startsWithIgnoreCase(String valueToTest, String startsWith) {
		if (valueToTest==null) return false;
		if (startsWith==null) return false;
		valueToTest = org.apache.commons.lang.StringUtils.upperCase(valueToTest);
		startsWith = org.apache.commons.lang.StringUtils.upperCase(startsWith);
		return valueToTest.startsWith(startsWith);
	}
	
	public static boolean equalsIgnoreCase(String valueToTest, String startsWith) {
		if (valueToTest==null) return false;
		if (startsWith==null) return false;
		valueToTest = org.apache.commons.lang.StringUtils.upperCase(valueToTest);
		startsWith = org.apache.commons.lang.StringUtils.upperCase(startsWith);
		return valueToTest.equals(startsWith);
	}
	
	public static boolean endsWithIgnoreCase(String valueToTest, String endsWith) {
		if (valueToTest==null) return false;
		if (endsWith==null) return false;
		valueToTest = org.apache.commons.lang.StringUtils.upperCase(valueToTest);
		endsWith = org.apache.commons.lang.StringUtils.upperCase(endsWith);
		return valueToTest.endsWith(endsWith);
	}
	
	public static boolean containsIgnoreCase(String valueToTest, String endsWith) {
		if (valueToTest==null) return false;
		if (endsWith==null) return false;
		valueToTest = org.apache.commons.lang.StringUtils.upperCase(valueToTest);
		endsWith = org.apache.commons.lang.StringUtils.upperCase(endsWith);
		return valueToTest.contains(endsWith);
	}

	public static boolean isEmpty(String str) {
		return org.apache.commons.lang.StringUtils.isEmpty(str);
	}

	public static boolean checkExpression(String valueToTest, String expression, String pattern) {
		if (expression==null || valueToTest==null || pattern==null)
			return false;
		if (Condition.STARTS_WITH.equals(expression))
			return startsWithIgnoreCase(valueToTest, pattern);
		if (Condition.EQUALS.equals(expression))
			return equalsIgnoreCase(valueToTest, pattern);
		if (Condition.ENDS_WITH.equals(expression))
			return endsWithIgnoreCase(valueToTest, pattern);
		if (Condition.CONTAINS.equals(expression))
			return containsIgnoreCase(valueToTest, pattern);
		return false;
	}

	public static boolean regex(String valueToTest, String regex) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean compareName (String name1, String name2) {
//		if (name1==null || name2==null)	return false;
		return org.apache.commons.lang.StringUtils.equalsIgnoreCase(name1, name2);
	}

	public static String asNameStringList(List<Object> beans, String method) {
		try {
			return asNameStringListIntrospect(beans, method);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return "";
	}
	public static String asNameStringListIntrospect(List<Object> beans, String method) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		StringBuffer sb = new StringBuffer();
		int cpt=0;
		int size = beans.size();
		for (Object bean : beans) {
			sb.append(MethodUtils.invokeMethod(bean, method, null));
			if (cpt<size) {
				sb.append(",");
				cpt++;
			}
		}
		return sb.toString();
	}
}
