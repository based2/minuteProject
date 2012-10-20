package net.sf.minuteProject.utils.binding;

import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.utils.FormatUtils;
import net.sf.minuteProject.utils.StringUtils;
import static net.sf.minuteProject.utils.FormatUtils.*;

public class JaxbUtils {
	
	//java-variable, uppercase-underscore, lowercase-hyphen, java-class
	public static final String JAVA_VARIABLE_FORMAT = "java-variable";
	public static final String UPPERCASE_UNDERSCORE_FORMAT = "uppercase-underscore";
	public static final String LOWERCASE_HYPHEN_FORMAT = "lowercase-hyphen";
	public static final String JAVA_CLASS_FORMAT = "java-class";
	
	public static String getXmlElementValue (Column column, String xmlFormat) {
		if (xmlFormat==null) return null;
		String name=null;
		String alias = column.getAlias();
		return format(xmlFormat, name, alias);
	}
	
	public static String getXmlElementValue (String input, String xmlFormat) {
		if (xmlFormat==null) return null;
		String name=null;
		String alias = FormatUtils.formatToSQLSingleString(input);
		return format(xmlFormat, name, alias);
	}

	private static String format(String xmlFormat, String name, String alias) {
		if (JAVA_VARIABLE_FORMAT.equals(xmlFormat))
			name=getJavaNameVariable(alias);
		if (UPPERCASE_UNDERSCORE_FORMAT.equals(xmlFormat))
			name=getUppercaseUnderscore(alias);
		if (LOWERCASE_HYPHEN_FORMAT.equals(xmlFormat))
			name=getLowcaseHyphen(alias);
		if (JAVA_CLASS_FORMAT.equals(xmlFormat))
			name=getJavaName(alias);	
		if (name!=null) {
			return "(name=\""+name+"\")";
		}
		return null;
	}
}
