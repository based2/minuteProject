package net.sf.minuteProject.utils.java;

import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Table;

public class NamingAffectationUtils {

	public static enum Scope {attributeVar, primaryKeyVar, parentVar, childrenVar, many2manyVar}
	public static boolean validateGlobalVariable (String proposedName, Column column, Table table) {
		return false;
	}
	/**
	 * if proposed name not present in suite of alias=> column
	 */
}
