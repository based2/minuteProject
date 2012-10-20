package net.sf.minuteProject.utils.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;

public class ParserUtils {

	public static List<String> getList(String s) {
		List<String> l = new ArrayList<String>();
		if (s!=null) {
			s = StringUtils.replace(s, " ", "");
			return Arrays.asList(StringUtils.split(s, ","));
//			StringTokenizer st = new StringTokenizer(s,",");
//			while (st.hasMoreElements()) {
//				String element = (String) st.nextElement();
//				l.add(element);
//			}
		}
		return l;
	}

	public static boolean isInList(String name,
			String list) {
		if (list==null || list.isEmpty()) 
			return false;
		return getList(list).contains(name);
	}
}
