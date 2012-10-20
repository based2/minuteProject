package net.sf.minuteProject.plugin.presentation;

import net.sf.minuteProject.loader.presentation.node.Component;

public class HtmlUtils {

	public static String getComponentJavaType(Component component) {
		if (component.getType().equals("textField"))
			return "String";
		return "String";
	}
}
