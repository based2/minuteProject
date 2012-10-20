package net.sf.minuteProject.plugin.jpa;

import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.model.data.Table;

public class JpaUtils {

	public static final String JPA2_IMPLEMENTATION_HIBERNATE = "hibernate";
	public static final String JPA2_IMPLEMENTATION_ECLIPSELINK = "eclipselink";
	
	public static String getMetamodelVolatileObjects (Table table) {
		return "";
	}
	
	public static String getJpa2Implementation (Template template) {
		String s = template.getPropertyValue("add-jpa2-implementation");
		if (s==null) return JPA2_IMPLEMENTATION_HIBERNATE;
		return s.toLowerCase();
	}
}
