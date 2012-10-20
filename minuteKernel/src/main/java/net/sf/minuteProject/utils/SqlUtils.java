package net.sf.minuteProject.utils;

import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Table;

public class SqlUtils {
	
	public static boolean isQuotedColumn (Column column) {
		if (column == null)
			return false;
		if (column.getType().equals("INT") 
			|| column.getType().equals("NUMBER")
			|| column.getType().equals("INTEGER")
			|| column.getType().equals("LONG")
			|| column.getType().equals("DECIMAL")
			|| column.getType().equals("BIGINT"))
			return false;
		return true;
			
	}
	
	/**
	 * isPKQuotedColumn return true is the First pk column is not a sql type of integer.
	 * @param table
	 * @return
	 */
	public static boolean isPKQuotedColumn (Table table) {
		return isQuotedColumn (TableUtils.getPrimaryFirstColumn(table));
	}
	
	public static String getTimeConversionExpression (String columnExpression, Column column) {
		return "";
	}
	
	public static String getSqlTypeFormat(Column column, String databaseType) {
		return getDbSqlTypeFormat(column, databaseType)+getSqlTypeSizeFormat(column, databaseType);
	}

	private static String getDbSqlTypeFormat(Column column, String databaseType) {
		if (databaseType.equals("ORACLE"))
			return getOracleSqlTypeFormat(column);
		return column.getType();
	}

	private static String getOracleSqlTypeFormat(Column column) {
		if (column.getType().equals("INT") 
				|| column.getType().equals("INTEGER")
				|| column.getType().equals("LONG")
				|| column.getType().equals("BIGINT"))
			return "NUMBER";
		return column.getType();
	}

	private static String getSqlTypeSizeFormat(Column column, String databaseType) {
		if (databaseType.equals("ORACLE"))
			return getOracleSqlTypeSizeFormat(column);
		return getSqlTypeSizeFormat(column);
	}

	private static String getOracleSqlTypeSizeFormat(Column column) {
		if (column.getType().equals("DECIMAL"))
			return getSqlTypeSizeFormat(column, false);
		if (column.getType().equals("CLOB") 
				|| column.getType().equals("BLOB")
				|| column.getType().equals("TIMESTAMP"))
			return "";		
		return getSqlTypeSizeFormat(column);
	}

	private static String getSqlTypeSizeFormat(Column column, boolean appendScale) {
		String s = column.getSize();
		if (s!=null && !s.equals("")) {
			if (appendScale)
				return "("+s+getSqlTypeScaleFormat (column)+")";
			else
				return "("+s+")";
		}
		return "";
	}
	
	private static String getSqlTypeSizeFormat(Column column) {
		return getSqlTypeSizeFormat(column, true);
	}

	private static String getSqlTypeScaleFormat(Column column) {
		Integer sc = column.getScale();
		if (sc!=null && !isQuotedColumn(column))
			return ","+sc.intValue();
		return "";
	}

}
