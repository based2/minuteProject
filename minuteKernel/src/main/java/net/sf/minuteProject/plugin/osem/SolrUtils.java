package net.sf.minuteProject.plugin.osem;

import java.util.List;

import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Database;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.utils.TableUtils;

public class SolrUtils {

	public static final String SOLR_BOOLEAN_TYPE 				=   "boolean";					
	public static final String SOLR_LONG_TYPE 					=   "sfloat";	
	public static final String SOLR_DOUBLE_TYPE 				=   "sfloat";			
	public static final String SOLR_INTEGER_TYPE 				=   "integer";		
	public static final String SOLR_TIMESTAMP_TYPE 				=   "date";			
	public static final String SOLR_BIGDECIMAL_TYPE 			=   "sfloat";
	public static final String SOLR_STRING_TYPE 				=   "text";						
	public static final String SOLR_DATE_TYPE 					=   "date";
	public static final String SOLR_BLOB_TYPE 					=   "binary";	
	public static final String SOLR_CLOB_TYPE 					=   "text";
	public static final String SOLR_FLOAT_TYPE 					=   "sfloat";
	public static final String SOLR_PK_TYPE 					=   "string";
	
	
	public static Column getSolrPkColumn (Database database) {
		List<Table> roots = OsemUtils.getSearchRootTables(database);
		for (Table table : roots) {
			return TableUtils.getPrimaryFirstColumn(table);
		}
		return null;
	}
	
	public static Column getDefaultSearchColumn (Database database) {
		List<Table> roots = OsemUtils.getSearchRootTables(database);
		for (Table table : roots) {
			return getDefaultSearchColumn(table);
		}
		return null;
	}
	
	public static Column getDefaultSearchColumn (Table table) {
		return getFirstFieldNotPk (table);
	}
	
	private static Column getFirstFieldNotPk (Table table) {
		for (Column column : table.getAttributes()) {
			return column;
		}
		return null;
	}

	public static String getType (Column column) {
		if (column.isPrimaryKey())
			return SOLR_PK_TYPE;
		return getSolrType(column.getType());
	}

	private static String getSolrType(String dBType) {
		String retStr=null;
		dBType = dBType.toUpperCase();
		if (dBType.equals("BOOLEAN") || dBType.equals("TINYINT") )
			return  SOLR_BOOLEAN_TYPE;					
		if (dBType.equals("BIGINT"))
			return  SOLR_LONG_TYPE;	
		if (dBType.equals("DOUBLE"))
			return  SOLR_DOUBLE_TYPE;			
		if (dBType.equals("INT"))
			return  SOLR_INTEGER_TYPE;		
		if (dBType.equals("TIME"))
			return  SOLR_TIMESTAMP_TYPE;			
		if (dBType.equals("DECIMAL"))
			return  SOLR_BIGDECIMAL_TYPE;
		if (dBType.equals("SMALLINT"))
			return  SOLR_STRING_TYPE;	
		if (dBType.equals("VARCHAR"))
			return  SOLR_STRING_TYPE;	
		if (dBType.equals("FLOAT"))
			return  SOLR_FLOAT_TYPE;	
		if (dBType.equals("LONGVARCHAR"))
			return  SOLR_STRING_TYPE;	
		if (dBType.equals("VARCHAR2"))
			return  SOLR_STRING_TYPE;		
		if (dBType.equals("VARGRAPHIC"))
			return  SOLR_STRING_TYPE;			
		if (dBType.equals("CHAR"))
			return  SOLR_STRING_TYPE;		
		if (dBType.equals("INTEGER") || dBType.equals("INT"))
			return  SOLR_INTEGER_TYPE;	
		if (dBType.equals("NUMERIC"))
			return  SOLR_INTEGER_TYPE;		
		if (dBType.equals("NUMBER"))
			return  SOLR_LONG_TYPE;		
		if (dBType.equals("DATE"))
			return  SOLR_DATE_TYPE;
		if (dBType.equals("TIMESTAMP"))
			return  SOLR_TIMESTAMP_TYPE;	
		if (dBType.equals("BLOB"))
			return  SOLR_BLOB_TYPE;	
		if (dBType.equals("BINARY"))
			return  SOLR_BLOB_TYPE;	
		if (dBType.equals("CLOB") || dBType.equals("TEXT"))
			return  SOLR_CLOB_TYPE;	
		if (dBType.equals("NVARCHAR2"))
			return  SOLR_STRING_TYPE;	
		if (dBType.equals("NVARCHAR"))
			return  SOLR_STRING_TYPE;	
		if (dBType.equals("OTHER"))
			return  SOLR_STRING_TYPE;		
		return retStr;		
	}

}
