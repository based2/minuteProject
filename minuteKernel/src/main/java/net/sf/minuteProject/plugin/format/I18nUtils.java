package net.sf.minuteProject.plugin.format;

import java.util.List;

import net.sf.minuteProject.configuration.bean.Package;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.utils.ColumnUtils;
import net.sf.minuteProject.utils.CommonUtils;
import net.sf.minuteProject.utils.FormatUtils;
import net.sf.minuteProject.utils.TemplateUtils;

import org.apache.commons.lang.StringUtils;

public class I18nUtils {
	public static final String BLANK = "";
	public static final String ONE_SPACE = " ";
	public static final String UNDERSCORE = "_";
	public static final String SEPARATOR_WORD= ".";
	public static final String DEFAULT_SEPARATOR="_";

	public static String getColumnDescription(Column column) {
		return (column.getDescription()!=null)?column.getDescription():"";
	}
	
	public static String getI18nFromDBName (String input) {
		return getI18nFromDBName(input, true);
	}
	
	public static String getI18nFromDBName (String input, boolean firstUpperCase) {
		input = StringUtils.replace(input, UNDERSCORE, ONE_SPACE);
		if (firstUpperCase)
			input = FormatUtils.firstUpperCaseOnly(input);
		else 
			input = FormatUtils.fullLowerCase(input);
		return input;
	}
	
	public static String plurializeUppercase(String input) {
		return plurialize(input.toLowerCase()).toUpperCase();
	}
	
	public static String plurialize(String input) {
		if (input!=null) {
			String lastChar = getLastChar (input, 1);//StringUtils.substring(input, input.length()-1);
			if (lastChar!=null) {
				String strippedInput = input.substring(0, input.length()-1);
				if (lastChar.equals("y")) {
					String last2Char = getLastChar (input, 2);
					if ("ay".equals(last2Char) || "uy".equals(last2Char) || "oy".equals(last2Char)|| "ey".equals(last2Char))
						return input+"s";
					return strippedInput + "ies";
				}
				if (lastChar.equals("s")) return strippedInput + "ses";
				return input + "s";
			}
		}
		return null;
	}

	public static String singularize (String input) {
		if (input!=null) {
			String lastChar = getLastChar (input, 1);
			if (lastChar!=null) {				
				if (lastChar.equals("s")) {
//					String strippedInput = input.substring(0, input.length()-1);
					String last3Char = getLastChar (input, 3);
					if ("ies".equals(last3Char))
						return getBeforeLastChar(input,3)+"y";
					if ("ses".equals(last3Char))
						return getBeforeLastChar(input,2);
					return getBeforeLastChar(input,1);
				}
			}
			return input;
		}
		return null;		
	}
	
	private static String getLastChar(String input, int i) {
		if (input!=null && input.length()>=i)
			return StringUtils.substring(input, input.length()-i);
		return null;
	}

	private static String getBeforeLastChar(String input, int i) {
		if (input!=null && input.length()>=i)
			return input.substring(0, input.length()-i);
		return null;
	}
	
	public static String getI18nFromDBNameStripPrefix (String input) {
		if (input==null) return null;
		String prefix = StringUtils.substringBefore(input, UNDERSCORE);
		if (prefix.equals(input))
			return input;
		input = StringUtils.removeStart(input, prefix);
		input = StringUtils.stripStart(input, UNDERSCORE);
		return getI18nFromDBName(input);
	}

	public static String getI18nFromDBNameStripSufixFromSet (String input, List<String> set) {
		if (set!=null) {
			String sufix = StringUtils.substringAfterLast(input, UNDERSCORE);
			if (!sufix.equals(input))
				input = StringUtils.removeEnd(input, sufix);
			if (set.contains(input)) {
				input = StringUtils.stripStart(input, UNDERSCORE);
				return getI18nFromDBName(input);
			}
		}
		return input;
	}
	
	public static String getI18nFromDBNameStripSufix (String input, boolean firstUpperCase) {
		String sufix = StringUtils.substringAfterLast(input, UNDERSCORE);
		if (!sufix.equals(input))
			input = StringUtils.removeEnd(input, sufix);
		input = StringUtils.stripStart(input, UNDERSCORE);
		return getI18nFromDBName(input, firstUpperCase);
	}
	
	public static String getI18nFromDBObject (String name) {
		return getI18nFromDBObject(name, true);
	}
	
	public static String getI18nFromDBObject (String name, boolean firstUpperCase) {
		return getI18nFromDBName(name, firstUpperCase);
	}
	
	public static String getI18nFromDBObject (Table table) {
		String alias = table.getAlias();
//		if (table.getName().equals(alias))
//			return getI18nFromDBNameStripPrefix(alias);
		return getI18nFromDBName(alias);
	}
	
	public static String getI18nFromDBObject (Column column) {
		return getI18nFromDBObject (column, true);
	}
	
	public static String getI18nFromDBObject (Column column, boolean firstUpperCase) {
		if (ColumnUtils.isForeignKey(column)){
			return getI18nFromDBNameStripSufix(column.getAlias(), firstUpperCase);
		}
		return getI18nFromDBName(column.getAlias(), firstUpperCase);
	}
	
	public static String getSeparator (Template template) {
		return getSeparator(template, template.getName());
	}
	
	public static String getSeparator (Template template, String i18nTemplateName) {
		if (!i18nTemplateName.equals(template.getName()))
			template = CommonUtils.getTemplate(template.getTarget(), i18nTemplateName);
		return template.getPropertyValue(SEPARATOR_WORD, DEFAULT_SEPARATOR);
	}
	
	public static String getPackageName(Package pack) {
		return FormatUtils.getJavaName(pack.getAlias());
	}
}
