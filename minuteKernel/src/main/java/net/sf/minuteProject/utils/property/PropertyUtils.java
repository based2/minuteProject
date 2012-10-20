package net.sf.minuteProject.utils.property;

import org.apache.commons.lang.StringUtils;

import net.sf.minuteProject.configuration.bean.system.Property;
import net.sf.minuteProject.utils.code.RestrictedCodeUtils;
import static net.sf.minuteProject.configuration.bean.enrichment.Trigger.*;

public class PropertyUtils {

	private static final String START_WITH = "start-with";
	private static final String END_WITH = "end-with";
	private static final String CONTAIN = "contain";

	public static String getConstraintPropertyValue(Property property) {
		return RestrictedCodeUtils.convertToValidJavaWithUpperCase(property);
	}

	public static String getPropertyValue(Property property) {
		return (property.getValue() != null) ? property.getValue() : property
				.getName();
	}

	public static boolean isPropertyTagCondition(Property property,
			String name, String condition) {
		String tag = property.getTag();
		name = name.toUpperCase();
		if (!StringUtils.isEmpty(tag)) {
			if (START_WITH.equals(condition))
				return tag.toUpperCase().startsWith(name);
			if (END_WITH.equals(condition))
				return tag.toUpperCase().startsWith(name);
			if (CONTAIN.equals(condition))
				return tag.toUpperCase().contains(name);
		}
		return false;
	}

	public static boolean isPropertyTagStartWith(Property property, String name) {
		return isPropertyTagCondition(property, name, START_WITH);
	}
	
	public static boolean isPropertyTagContain(Property property, String name) {
		return isPropertyTagCondition(property, name, CONTAIN);
	}



}
