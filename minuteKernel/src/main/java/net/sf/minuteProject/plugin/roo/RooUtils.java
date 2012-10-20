package net.sf.minuteProject.plugin.roo;

import static net.sf.minuteProject.utils.ConvertUtils.isDateType;
import static net.sf.minuteProject.utils.ConvertUtils.isNumberType;
import static net.sf.minuteProject.utils.ConvertUtils.isStringType;
import static net.sf.minuteProject.utils.ConvertUtils.isBooleanType;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import static net.sf.minuteProject.utils.ColumnUtils.isLengthPrecisionColumn;
import static net.sf.minuteProject.db.type.FieldType.*;

import net.sf.minuteProject.utils.ConvertUtils;
import net.sf.minuteProject.utils.FormatUtils;
import net.sf.minuteProject.utils.java.JavaUtils;
import net.sf.minuteProject.utils.sql.SqlUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class RooUtils {

	static Logger log = Logger.getLogger(RooUtils.class);
	
	public static String getRooConsoleType (Column column){
	   return StringUtils.lowerCase(ConvertUtils.getJavaTypeFromDBType(column));
	}
	
	public static String getRooTypeChunk (Column column){
		return StringUtils.lowerCase(ConvertUtils.getJavaTypeFromDBType(column));
	}
	
	public static RooColumn getRooColumn (Column column) {
		RooColumn rooColumn = new RooColumn();
		String type = column.getType();
//		System.out.println(">>> type = "+type+" for "+column.getName()+" isString? "+isStringType (type));
		if (isStringType (type)) {
		   rooColumn.setRooConsoleType("string");
		   rooColumn.setTypeChunk("");
		} else if (isDateType (type)) {
		   rooColumn.setRooConsoleType("date");
		   rooColumn.setTypeChunk(getRooColumnTypeChunck("java.lang.Date"));
	    } else if (isNumberType (type)) {
			rooColumn.setRooConsoleType("number");
			rooColumn.setTypeChunk(getRooColumnTypeChunck(getNumberType(type)));
	    }  else if (isBooleanType (type)) {
				rooColumn.setRooConsoleType("boolean");
				rooColumn.setTypeChunk(getRooColumnTypeChunck("java.lang.Boolean"));
		 }else {
	    	log.error(">> untreated type "+type +" add th");
	    }
		// mandatory value
		rooColumn.setNotNullChunk ((column.isRequired())?"--notNull":"");
		rooColumn.setMinSizeChunk("");//TODO with enrichment
		rooColumn.setSizeMaxChunk (isLengthPrecisionColumn(column)?"--sizeMax "+column.getSizeAsInt():"");
		rooColumn.setColumnVariableName (getRooEntityFieldVariable(column));
		rooColumn.setColumnClassName(getRooEntityClassName(column));
		return rooColumn;
	}

	private static String getRooColumnTypeChunck (String value) {
		return "--type "+value;
	}
	private static String getNumberType(String dBType) {
		if (BIGINT.toString().equals(dBType)) return "java.lang.Long";
		if (DECIMAL.toString().equals(dBType)) return "java.lang.Double";
		if (INTEGER.toString().equals(dBType)) return "java.lang.Integer";
		return "";
	}
	
	public static String getRooEntityFieldVariable (Column column) {
		return getRooEntityFieldVariable(column.getName());
	}

	public static String getRooEntityClassName (Column column) {
		return getRooEntityClassName(column.getName());
	}

	private static String getRooEntityClassName(String name) {
		String nameLC = StringUtils.lowerCase(name);
		String s = FormatUtils.getJavaName(name);
		if (SqlUtils.isReservedWord(nameLC) || JavaUtils.isReservedWord(nameLC))
			return s+"_";
		return s;
	}
	
	private static String getRooEntityFieldVariable(String name) {
		String nameLC = StringUtils.lowerCase(name);
		String s = FormatUtils.getJavaNameVariable(name);
		if (SqlUtils.isReservedWord(nameLC) || JavaUtils.isReservedWord(nameLC))
			return s+"_";
		return s;
	}
	
}
