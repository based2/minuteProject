package net.sf.minuteProject.utils.sql;

import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.statement.QueryParam;
import net.sf.minuteProject.utils.ConvertUtils;
import net.sf.minuteProject.utils.FormatUtils;

import net.sf.minuteProject.db.type.FieldType;

public class StatementUtils {

	public static final String getJavaType(QueryParam queryParam) {
		return ConvertUtils.getJavaTypeFromDBType(queryParam.getType());
	}
	
	public static final String getJavaVariableName(QueryParam queryParam) {
		return FormatUtils.getJavaNameVariable(queryParam.getName());
	}
	
	private static final String getJdbcType(QueryParam queryParam) {
		return getJdbcType(queryParam.getType());
	}
	
	public static final String getJdbcType(Column column) {
		return getJdbcType(column.getType());
	}
	
	private static final String getJdbcType(String type) {
		if (type.equals(FieldType.INTEGER.toString()))
			return "Int";
		if (type.equals(FieldType.DECIMAL.toString()))
			return "Long";
		if (type.equals(FieldType.DOUBLE.toString()))
			return "BigDecimal";
		if (type.equals(FieldType.CHAR.toString()))
			return "String";
		return "String";
	}
	
	public static final String getJdbcGetter(QueryParam queryParam) {
		return "get"+getJdbcType(queryParam);
	}
	
	public static final String getJdbcSetter(QueryParam queryParam) {
		return "get"+getJdbcType(queryParam);
	}
}
