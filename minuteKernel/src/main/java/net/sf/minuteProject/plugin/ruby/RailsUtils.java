package net.sf.minuteProject.plugin.ruby;

import net.sf.minuteProject.configuration.bean.model.data.Column;

public class RailsUtils {

	public static String getRubyType (Column column) {
		return RubyUtils.getRubyType(column);
	}
	
	public static String getRubyTypeOnly (Column column) {
		return "TODO";		
	}
	
	public static String getRubyName (String name) {
		return "TODO";		
	}
	
	public static String getRubyNameVariable (String column) {
		return column.toLowerCase();
	}
	
}
