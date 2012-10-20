package net.sf.minuteProject.plugin.jooq;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import net.sf.minuteProject.configuration.bean.Model;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Database;
import net.sf.minuteProject.configuration.bean.model.data.Function;
import net.sf.minuteProject.configuration.bean.model.data.FunctionColumn;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.utils.ColumnUtils;
import net.sf.minuteProject.utils.CommonUtils;
import net.sf.minuteProject.utils.ConvertUtils;
import net.sf.minuteProject.utils.RoutineUtils;
import net.sf.minuteProject.utils.TableUtils;
import net.sf.minuteProject.utils.parser.ParserUtils;

public class JooqUtils {

	private static final String TO_IMPLEMENT = "to implement";
	private static final String JAVA_LANG_VOID = "java.lang.Void";
	private static final String JOOQ_RESERVED_WORD_IN_METHOD = "jooq-reserved-word-in-method";
	private static final String JOOQ_SCHEMA = "schema";
	public static final String JOOQ_DECIMAL = "DECIMAL";
	public static final String JOOQ_FLOAT = "FLOAT";
	public static final String JOOQ_BIGINT = "BIGINT";
	public static final String JOOQ_SMALLINT = "SMALLINT";
	public static final String JOOQ_INTEGER = "INTEGER";
	
	private static List<String> columnInJooqRecordReservedWords;

	public static String getDatabaseJavaPackage(Database database) {
		if (database==null) return TO_IMPLEMENT;
		if ("MYSQL".equals(database.getType()))
			return "org.jooq.util.mysql.MySQLDatabase";
		return TO_IMPLEMENT;
	}

	public static String getEntitySuperClass(Table table) {
		if (TableUtils.isView(table))
			return TO_IMPLEMENT;
		return "org.jooq.impl.UpdatableTableImpl";
	}

	public static String getJooqFullType(Column column) {
		String type = column.getType();
		String javaType = ConvertUtils.getJavaTypeFromDBFullType(column);
		choice: {
			if (ConvertUtils.JAVA_BIGDECIMAL_TYPE.equals(javaType)) {
				type = JOOQ_DECIMAL;
				break choice;
			}
			if (ConvertUtils.JAVA_LONG_TYPE.equals(javaType)) {
				type = JOOQ_BIGINT;
				break choice;
			}
			if (ConvertUtils.JAVA_SHORT_TYPE.equals(javaType)) {
				type = JOOQ_SMALLINT;
				break choice;
			}
			if (ConvertUtils.JAVA_INTEGER_TYPE.equals(javaType)) {
				type = JOOQ_INTEGER;
				break choice;
			}
			if (ConvertUtils.DB_BLOB.equals(type)) {
				type = "BLOB";
				break choice;
			}
			if (ConvertUtils.DB_LONGVARBINARY.equals(type) || ConvertUtils.DB_LONGBLOB.equals(type)) {
				type = ConvertUtils.DB_LONGVARBINARY;
				break choice;
			}
				
		}
		return "org.jooq.impl.SQLDataType." + type;
	}

	public static String getJooqColumnFullType(Column column) {
		String type = column.getType();
		if (ConvertUtils.DB_BLOB.equals(type) ||
			ConvertUtils.DB_CLOB.equals(type) || 
			ConvertUtils.DB_LONGTEXT.equals(type) || 
			ConvertUtils.DB_LONGVARBINARY.equals(type) || 
			ConvertUtils.DB_LONGBLOB.equals(type))
			return "byte[]";
		return CommonUtils.getFullType2(column);
	}

	public static String getRandomSerialNumber() {
		return "123456789";// tochange
	}

	public static boolean isTableNameAndAnyColumnNameAmbiguous(Table table) {
		String proposedName = table.getAlias();
		for (Column column : table.getColumns()) {
			if (proposedName.toLowerCase().equals(column.getAlias().toLowerCase()))
				return true;
		}
		return false;
	}
	public static boolean isTableNameAndAnyColumnNameAmbiguous(Table table, String name) {
		String proposedName = table.getAlias();
		return (proposedName.toLowerCase().equals(name.toLowerCase()))?true:false;
	}

	public static String getTableConstant(Table table) {
//		return isTableNameAndAnyColumnNameAmbiguous(table) ? "ENTITY_"
//				+ table.getAlias() : table.getAlias();
		if (table==null) return "ERROR_JOOQ_TABLE_CONSTANT_TABLE_IS_NULL";
		return "__"+table.getAlias();
	}

	public static String getTableColumnConstant(Table table, String name) {
//		return isTableNameAndAnyColumnNameAmbiguous(table, name) ? "FIELD_" + name
//				: name;
		return name;
	}
	
	public static String getSchema(Template template, Model model) {
		String schema = model.getDataModel().getSchema();
		if (!StringUtils.isEmpty(schema)) 
			return schema;
		schema = template.getPropertyValue(JOOQ_SCHEMA);
		if (schema!=null) return schema;
		return model.getName();
	}

	public static String getRecordColumnNaming (String name, Template template) {
		return (isColumnInJooqRecordReservedWords(name, template))?name+"_":name;
	}

	private static boolean isColumnInJooqRecordReservedWords(String name, Template template) {
		for (String value:getColumnInJooqRecordReservedWords(template)) {
			if (value.toLowerCase().equals(name.toLowerCase()))
				return true;
		}
		return false;
	}

	private static List<String> getColumnInJooqRecordReservedWords(Template template) {
		if (columnInJooqRecordReservedWords==null) {
			columnInJooqRecordReservedWords = 
				ParserUtils.getList(template.getPropertyValue(JOOQ_RESERVED_WORD_IN_METHOD));
		}
		return columnInJooqRecordReservedWords;
	}
	
	public static boolean hasReturn (Function function) {
		return function.hasReturn();
	}
	
	public static boolean isReturn (FunctionColumn functionColumn) {
		return functionColumn.isReturn();
	}

	public static String getReturnType (Function function) {
		String s = ConvertUtils.getJavaTypeFromDBFullType(RoutineUtils.getColumn(function.getReturnFunctionColumn()));
		return (s!=null)?s:JAVA_LANG_VOID;
	}
	
	public static String getReturnFullType (Function function) {
		Column column = RoutineUtils.getColumn(function.getReturnFunctionColumn());
		if (column==null)
			return JAVA_LANG_VOID;
		return getJooqFullType(column);
	}
	
	public static String getDialect (Database database) {
		return "org.jooq.SQLDialect."+database.getType();
	}
	
	public static String getModelConstant (Template template, Model model) {
		return "my.jooqtech.JooqTech."+getSchema(template, model);
	}
	
}
