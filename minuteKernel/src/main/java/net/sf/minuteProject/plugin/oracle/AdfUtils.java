package net.sf.minuteProject.plugin.oracle;

import org.codehaus.groovy.runtime.ConvertedClosure;

import net.sf.minuteProject.configuration.bean.Model;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.utils.ColumnUtils;
import net.sf.minuteProject.utils.CommonUtils;
import net.sf.minuteProject.utils.ConvertUtils;
import net.sf.minuteProject.utils.FormatUtils;

public class AdfUtils {

	public String getAdfType(Column column) {
		if (ColumnUtils.isTimeColumn(column))
			return "oracle.jbo.domain.Date";
		if (ColumnUtils.isNumeric(column))
			return "oracle.jbo.domain.Number";
		return "java.lang.String";
	}
	
	public String getAdfSQLType(Column column) {
		if (ColumnUtils.isTimeColumn(column))
			return "DATE";
		if (ColumnUtils.isNumeric(column))
			return "NUMERIC";
		return "VARCHAR";
	}
	
	public String getColumnType(Column column) {
		if ("SMALLINT".equals(column.getType()))
			return "NUMERIC";
		return column.getType();
	}
	
	public String getSDOColumnTypeBegin(Column column) {
		if ("INTEGER".equals(column.getType()) || 
			"SMALLINT".equals(column.getType())	
				)
			return "new Integer(getInt";
		if ("DATE".equals(column.getType()) 
				)
			return "(java.sql.Timestamp)get";
		return "get"+CommonUtils.getJavaType(column);
	}	
	
	public String getSDOColumnFullType(Column column) {
		if ("DATE".equals(column.getType()) 
				)
			return "java.sql.Timestamp";
		return CommonUtils.getFullType2(column);
	}
	
	public String getSDOColumnTypeEnd(Column column) {
		if ("INTEGER".equals(column.getType()) || 
			"SMALLINT".equals(column.getType())	
				)
			return ")";
		return "";
	}
	
	public String getSdoXsdType(Column column) {
		if (ColumnUtils.isTimeColumn(column)) {
			return "ns0:dateTime-Timestamp";
		}
		return "xsd:"+getSdoXsdTypeValue(column);
	}

	private String getSdoXsdTypeValue(Column column) {
		String uml = ConvertUtils.getUMLTypeFromDBFullType(column.getType());
		if ("integer".equals(uml))
			return "int";
		return uml;
	}
	
	public boolean hasSdoXmlDataType(Column column) {
		return (getSdoXmlDataType(column)!=null);
	}
	
	public String getSdoXmlDataType(Column column) {
		if (ColumnUtils.isNumeric(column)) 
			return "sdoJava:IntObject";
		return null;
	}
	
	public static String getNamespacePackage(Model model, Template template, String targetTemplate) {
		String packageName = CommonUtils.getPackageName(model, CommonUtils.getTemplate(model.getConfiguration(), targetTemplate));
		return FormatUtils.getDirFromPackage(packageName);
	}
}
