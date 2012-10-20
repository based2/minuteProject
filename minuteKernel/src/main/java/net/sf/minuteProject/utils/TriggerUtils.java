package net.sf.minuteProject.utils;

import static net.sf.minuteProject.configuration.bean.enrichment.Trigger.INSERT;
import static net.sf.minuteProject.configuration.bean.enrichment.Trigger.UPDATE;
import static net.sf.minuteProject.utils.property.PropertyUtils.isPropertyTagContain;
import static net.sf.minuteProject.utils.property.PropertyUtils.isPropertyTagStartWith;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.enrichment.Trigger;
import net.sf.minuteProject.configuration.bean.enumeration.CRUDEnum;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.impl.UMLNotation.ColumnUMLNotation;
import net.sf.minuteProject.configuration.bean.system.Property;

public class TriggerUtils {

	private static final String TRIGGER = "TRIGGER";
	
	public static String getTriggerAlias (Trigger trigger) {
		return CommonUtils.getColumnVariableName(getColumn(trigger));
	}
	
	public static String getTriggerType (Trigger trigger) {
		return CommonUtils.getJavaType(getColumn(trigger));
	}
	
	public static Column getColumn(Trigger trigger) {
		return trigger.getColumn();
	}

	public static Trigger getTriggerFromProperty(Property property, Column column) {
		if (isTriggerTag(property)) {
			Trigger trigger = new Trigger();
			trigger.setColumn(column);
			trigger.setCruds(getCruds(property));
			trigger.setValue(getValue(property));
			return trigger;
		}
		return null;
	}
	
	public static String getJavaDisplayChunk (Trigger trigger, Template template) {
		if (trigger.getValue()==null)
			return getTriggerAlias(trigger);
		if (Trigger.CURRENT_TIME.equals(trigger.getValue()))
			return getJavaCurrentTime(trigger, template);
		return getTriggerAlias(trigger);
	}
	
	public static String getSqlDisplayChunk (Trigger trigger, Template template) {
		if (trigger.getValue()==null)
			return getTriggerAlias(trigger);
		if (Trigger.CURRENT_TIME.equals(trigger.getValue()))
			return getSQLCurrentTime(trigger, template);
		return getTriggerAlias(trigger);
	}

	private static String getJavaCurrentTime(Trigger trigger, Template template) {
		//TODO add temporal in trigger
		Column column = getColumn(trigger);
		String javaFullType = CommonUtils.getFullType2(column);
		if (template.hasProperty("use-temporal") && ColumnUtils.isTimeColumn(column)) {
			return "new java.util.Date()";
		}
		if (ConvertUtils.JAVA_SQL_DATE_TYPE.equals(javaFullType))
			return "new java.sql.Date((new java.util.Date().getTime()))";
		if (ConvertUtils.JAVA_SQL_TIMESTAMP_TYPE.equals(javaFullType))
			return "new java.sql.Timestamp(new java.util.Date().getTime()))";
		if (ConvertUtils.JAVA_SQL_TIME_TYPE.equals(javaFullType))
			return "new java.sql.Time(new java.util.Date().getTime()))";
		return getTriggerAlias(trigger);
	}
	
	private static String getSQLCurrentTime(Trigger trigger, Template template) {
		//TODO add temporal in trigger
		Column column = getColumn(trigger);
		String javaFullType = CommonUtils.getFullType2(column);
		if (ConvertUtils.JAVA_SQL_DATE_TYPE.equals(javaFullType))
			return "sysdate";
		if (ConvertUtils.JAVA_SQL_TIMESTAMP_TYPE.equals(javaFullType))
			return "systimestamp";
		if (ConvertUtils.JAVA_SQL_TIME_TYPE.equals(javaFullType))
			return "sysdate";
		return getTriggerAlias(trigger);
	}

	public static CRUDEnum getCRUDEnum (String crud) {
		if (StringUtils.isEmpty(crud))
			return null;
		for (CRUDEnum e : CRUDEnum.values()) {
			if (e.toString().equals(crud))
				return e;
		}
		return null;
	}
	
	private static String getValue(Property property) {
		if (org.apache.commons.lang.StringUtils.contains(property.getTag(), Trigger.CURRENT_TIME)
			|| org.apache.commons.lang.StringUtils.contains(property.getTag(), Trigger.CURRENT_DATE))

			return Trigger.CURRENT_TIME;
		return null;
	}

	private static List<CRUDEnum> getCruds(Property property) {
		List<CRUDEnum> list = new ArrayList<CRUDEnum>();
		for (CRUDEnum c : CRUDEnum.values()) {
			if (isPropertyTagContain(property, c.toString()))
				list.add(c);
		}
		return list;
	}

	public static boolean isTriggerTag(Property property) {
		return isPropertyTagStartWith(property, TRIGGER);
	}

	public static boolean isInsertTrigger(Property property) {
		return isTriggerTag(property) && isInsertTag(property);
	}

	public static boolean isUpdateTrigger(Property property) {
		return isTriggerTag(property) && isUpdateTag(property);
	}
	
	private static boolean isInsertTag(Property property) {
		return isPropertyTagContain(property, INSERT);
	}

	private static boolean isUpdateTag(Property property) {
		return isPropertyTagContain(property, UPDATE);
	}

}
