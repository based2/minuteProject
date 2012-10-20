package net.sf.minuteProject.utils;

import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.system.Property;

public class EnumUtils {

	public static final String TEMPLATE_ENUMERATION_REFERENCE_DATA = "EnumerationReferenceData";

	public static boolean isEnumType (Template template, Column column) {
		return (getEnumType(template, column)!=null)?true:false;
	}
	
	public static String getEnumType(Template template, Column column) {
		if (ColumnUtils.hasCheckConstraint(column) && ColumnUtils.isEnumColumn(column)) {
			return getEnumTemplateType(template, column);
		}
		return null;
	}
	
	public static String getEnumImport(Template template, Column column) {
		if (ColumnUtils.hasCheckConstraint(column) && ColumnUtils.isEnumColumn(column)) {
			return getEnumTemplateImport(template, column);
		}
		return null;
	}

	private static String getEnumTemplateType(Template template, Column column) {
		Template t = getEnumTemplateDependencyAvailable(template);
		if (t != null)
			return CommonUtils.getClassName(column, t);
		return null;
	}

	private static String getEnumTemplateImport(Template template, Column column) {
		Template t = getEnumTemplateDependencyAvailable(template);
		if (t != null)
			return CommonUtils.getEntityLevelTemplateFullPath(column, t, t.getName());
		return null;
	}
	
	private static Template getEnumTemplateDependencyAvailable(Template template) {
		String property = template.getPropertyValue(TEMPLATE_ENUMERATION_REFERENCE_DATA);
		if (property != null) {
			return CommonUtils.getTemplate(template.getTemplateTarget().getTarget(), property);
		}
		return null;
	}
}
