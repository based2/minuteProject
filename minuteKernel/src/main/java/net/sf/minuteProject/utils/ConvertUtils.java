package net.sf.minuteProject.utils;

import net.sf.minuteProject.configuration.bean.model.data.Column;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import net.sf.minuteProject.db.type.FieldType;

public class ConvertUtils {
	
	public static final String DB_TYPE_ORACLE                   	=   "ORACLE";
	public static final String JAVA_BOOLEAN_TYPE 					=   "java.lang.Boolean";					
	public static final String JAVA_LONG_TYPE 						=   "java.lang.Long";	
	public static final String JAVA_DOUBLE_TYPE 					=   "java.lang.Double";			
	public static final String JAVA_INTEGER_TYPE 					=   "java.lang.Integer";		
	public static final String JAVA_SHORT_TYPE 			 			=   "java.lang.Short";		
	public static final String JAVA_TIMESTAMP_TYPE 					=   "java.sql.Timestamp";			
	public static final String JAVA_SQL_TIMESTAMP_TYPE 				=   "java.sql.Timestamp";			
	public static final String JAVA_TIME_TYPE 						=   "java.sql.Time";			
	public static final String JAVA_SQL_TIME_TYPE 					=   "java.sql.Time";			
	public static final String JAVA_BIGDECIMAL_TYPE 				=   "java.math.BigDecimal";
	public static final String JAVA_BIGINTEGER_TYPE 				=   "java.math.BigInteger";
	public static final String JAVA_STRING_TYPE 					=   "java.lang.String";						
	public static final String JAVA_DATE_TYPE 						=   "java.sql.Date";
	public static final String JAVA_SQL_DATE_TYPE 					=   "java.sql.Date";
	public static final String JAVA_BLOB_TYPE 						=   "java.sql.Blob";	
	public static final String JAVA_CLOB_TYPE 						=   "java.sql.Clob";	

	public static final String DB_STRING_CHAR_TYPE 					=   "CHAR";	
	public static final String DB_STRING2_CHAR_TYPE 				=   "CHAR2";	
	public static final String DB_STRING_TYPE 						=   "VARCHAR";	
	public static final String DB_STRING2_TYPE 						=   "VARCHAR2";	
	public static final String DB_INTEGER_TYPE 						=   "INTEGER";	
	public static final String DB_INT_TYPE 							=   "INT";	
	public static final String DB_DECIMAL_TYPE 						=   "DECIMAL";
	public static final String DB_DOUBLE_TYPE 						=   "DOUBLE";
	public static final String DB_TIMESTAMP_TYPE 					=   "TIMESTAMP";
	public static final String DB_TIME_TYPE 						=   "TIME";
	public static final String DB_TIMESTAMPZ_TYPE 					=   "TIMESTAMPZ";
	public static final String DB_DATE_TYPE 						=   "DATE";
	public static final String DB_NUMERIC_TYPE 						=   "NUMERIC";	
	public static final String DB_SMALLINT_TYPE 					=   "SMALLINT";	
	public static final String DB_TINYINT_TYPE 						=   "TINYINT";
	public static final String DB_BYTE_TYPE 						=   "BYTE";
	public static final String DB_BLOB 								=	"BLOB";
	public static final String DB_CLOB  							=	"CLOB";
	public static final String DB_LONGTEXT  						=	"LONGTEXT";
	public static final String DB_LONGBLOB  						=	"DB_LONGBLOB";
	public static final String DB_LONGVARBINARY  					=	"LONGVARBINARY";
	public static final String DB_VARBINARY  						=	"VARBINARY";
	public static final String DB_BIGDECIMAL_TYPE  					=	"BIGDECIMAL";
	
	public static final String UML_STRING_TYPE 						=   "string";	
	public static final String UML_INTEGER_TYPE 					=   "integer";	
	public static final String UML_LONG_TYPE 						=   "decimal";	
	public static final String UML_DOUBLE_TYPE 						=   "double";	
	public static final String UML_BIGDECIMAL_TYPE 					=   "bigdecimal";	
	public static final String UML_BYTE_TYPE 						=   "byte";	
	
	private static Logger logger = Logger.getLogger(ConvertUtils.class);
	
	public static String getDBFullTypeFromUMLType (String type) {
		if (type==null) return DB_STRING_TYPE;
		type = StringUtils.lowerCase(type);
		if (UML_STRING_TYPE.equals(type)) return DB_STRING_TYPE;
		if (UML_INTEGER_TYPE.equals(type)) return DB_INTEGER_TYPE;
		if (UML_LONG_TYPE.equals(type)) return DB_DECIMAL_TYPE;
		if (UML_DOUBLE_TYPE.equals(type)) return DB_DOUBLE_TYPE;
		return DB_STRING_TYPE;
	}
	
	public static String getUMLTypeFromDBFullType (String type) {
		if (type==null) return DB_STRING_TYPE;
		type = StringUtils.upperCase(type);
		if (DB_STRING_TYPE.equals(type)) return UML_STRING_TYPE;
		if (DB_STRING2_TYPE.equals(type)) return UML_STRING_TYPE;
		if (DB_STRING_CHAR_TYPE.equals(type)) return UML_STRING_TYPE;
		if (DB_STRING2_CHAR_TYPE.equals(type)) return UML_STRING_TYPE;
		if (DB_INTEGER_TYPE.equals(type)) return UML_INTEGER_TYPE;
		if (DB_INT_TYPE.equals(type)) return UML_INTEGER_TYPE;
		if (DB_SMALLINT_TYPE.equals(type)) return UML_INTEGER_TYPE;
		if (DB_DECIMAL_TYPE.equals(type)) return UML_LONG_TYPE;
		if (DB_DOUBLE_TYPE.equals(type)) return UML_DOUBLE_TYPE;
		return UML_STRING_TYPE;
	}
	
	public static String getDDLUtilsTypeFromDBType (String type) {
		return getDBFullTypeFromUMLType(getUMLTypeFromDBFullType(type.trim()));
//		return (type==null)?DB_STRING_TYPE:type.toUpperCase();
	}	
	
	public static String getJavaTypeFromDBFullType (String dBType, int size, String databaseType) {
		String retStr=null;
		if (dBType.equals("BOOLEAN"))
			return  JAVA_BOOLEAN_TYPE;
		if (dBType.equals("BIGINT"))
			return  JAVA_LONG_TYPE;	
		if (dBType.equals("DOUBLE"))
			return  JAVA_BIGDECIMAL_TYPE;	
		if (dBType.equals("FLOAT"))
			return  JAVA_BIGDECIMAL_TYPE;		
		if (dBType.equals("INT"))
			return  JAVA_INTEGER_TYPE;		
		if (dBType.equals("TIME"))
			return  JAVA_TIME_TYPE;
		if (dBType.equals("DECIMAL"))
			return  JAVA_BIGDECIMAL_TYPE;
		if (dBType.equals("SMALLINT"))
			return  JAVA_INTEGER_TYPE;	
		if (dBType.equals(DB_TINYINT_TYPE))
			return  JAVA_SHORT_TYPE;	
		if (dBType.equals("VARCHAR"))
			return  JAVA_STRING_TYPE;	
		if (dBType.equals("LONGVARCHAR"))
			return  JAVA_STRING_TYPE;	
		if (dBType.equals("VARCHAR2"))
			return  JAVA_STRING_TYPE;		
		if (dBType.equals("VARGRAPHIC"))
			return  JAVA_STRING_TYPE;			
		if (dBType.equals("CHAR"))
			return  JAVA_STRING_TYPE;		
		if (dBType.equals("INTEGER"))
			return  JAVA_INTEGER_TYPE;	
		if (dBType.equals("NUMERIC"))
			return  JAVA_INTEGER_TYPE;		
		if (dBType.equals("NUMBER")  
			|| dBType.equals("REAL") 
			|| dBType.equals(DB_BYTE_TYPE) 
			) 
			{
			if (size==1 ) //&&databaseType.equals(DB_TYPE_ORACLE) &&  )
				return JAVA_BOOLEAN_TYPE;
			return  JAVA_LONG_TYPE;
		}
		if (dBType.equals("DATE"))
			return  JAVA_DATE_TYPE;
		if (dBType.equals("TIMESTAMP"))
			return  JAVA_TIMESTAMP_TYPE;	
		if (dBType.equals("TIMESTAMPZ"))
			return  JAVA_TIMESTAMP_TYPE;		
		if (dBType.equals("BLOB"))
			return  JAVA_BLOB_TYPE;	
		if (dBType.equals("BINARY"))
			return  JAVA_BLOB_TYPE;	
		if (dBType.equals("CLOB"))
			return  JAVA_CLOB_TYPE;	
		if (dBType.equals("NVARCHAR2"))
			return  JAVA_STRING_TYPE;	
		if (dBType.equals("NVARCHAR"))
			return  JAVA_STRING_TYPE;
		if (dBType.equals("VARBINARY"))
			return  JAVA_STRING_TYPE;
		
		// to re implement when externalizing the mapping
		if (dBType.equals("OTHER"))
			return  JAVA_STRING_TYPE;		
		return retStr;		
	}	

	public static String getJavaTypeFromDBFullType (Column column) {
		if (column==null)
			return null;
		String type=(column.getTable()!=null 
				     && column.getTable().getDatabase()!=null)?
				    		 column.getTable().getDatabase().getType():null;
		return getJavaTypeFromDBFullType(column.getTypeAlias(), //column.getType(), 
				column.getSizeAsInt(), 
				column.getScale(), 
				type);
	}
	
	public static String getJavaDefaultMask (Column column) {
		String type = getJavaTypeFromDBFullType(column);
		if (JAVA_BOOLEAN_TYPE.equals(type)) return "new Boolean(\"false\")";					
		if (JAVA_LONG_TYPE.equals(type)) return "Long.valueOf(-1)";
		if (JAVA_DOUBLE_TYPE.equals(type)) return "Double.valueOf(-1)";	
		if (JAVA_INTEGER_TYPE.equals(type)) return "Integer.valueOf(-1)";	
		if (JAVA_TIMESTAMP_TYPE.equals(type)) return "null"; //not supported yet	
		if (JAVA_BIGDECIMAL_TYPE.equals(type)) return "java.math.BigDecimal.valueOf(-1)";
		if (JAVA_STRING_TYPE.equals(type)) return "new String()";				
		if (JAVA_DATE_TYPE.equals(type)) return "new Date()";
		if (JAVA_BLOB_TYPE.equals(type)) return "null";	
		if (JAVA_CLOB_TYPE.equals(type)) return "null";
		return "null";
	}
	
	public static String getJavaTypeFromDBFullType (String dBType, int size, int scale, String databaseType) {
		String retStr=getJavaTypeFromDBFullType (dBType, size, databaseType);		
		if (dBType.equals("DECIMAL")) {
			if (scale==0)
				return JAVA_LONG_TYPE;
			else
				return  JAVA_BIGDECIMAL_TYPE;
		}
//		if (dBType.equals("NUMBER")  || dBType.equals("REAL") ) {
//			if (databaseType.equals(DB_TYPE_ORACLE) && size==1 )
//				return JAVA_BOOLEAN_TYPE;
//			return  JAVA_LONG_TYPE;
//		}	
		return retStr;		
	}

	public static String getJavaTypeClassFromDBType (Column column) {
		return getJavaTypeClassFromDBType(column.getType(), column.getScale(), getDatabaseType(column));
	}
	
	private static String getDatabaseType (Column column) {
		String databaseType=null;
		if (column.getTable()!=null &&
			column.getTable().getDatabase() != null)
			databaseType = column.getTable().getDatabase().getType();
		return databaseType;
	}
	
	public static String getJavaTypeClassFromDBType (String dBType, int scale, String databaseType) {
		if (dBType==null){
			String s = "ERROR column dBType is null";
			logger.error(s);
			return s;
		}
		if (dBType.equals("BOOLEAN"))
			return  "Boolean";	
		if (dBType.equals("NUMBER")  
				|| dBType.equals("REAL") 
				|| dBType.equals(DB_BYTE_TYPE) 
				) 
				{
			if (scale==1) //DB_TYPE_ORACLE.equals(databaseType) && scale==1 )
				return "Boolean";
		}
		if (dBType.equals("NUMERIC"))
			return  "Integer";		
		if (dBType.equals("BLOB"))
			return  "String";	
		if (dBType.equals("BINARY"))
			return  "String";	
		if (dBType.equals("CLOB"))
			return  "String";			
		return getJavaTypeFromDBTypeOnly (dBType, scale);
	}
	
	public static String getJavaTypeFromDBType (String dBType) {
		//return StringUtils.
		// TODO from getJavaTypeFromDBFullType
		//String retStr=null;
		if (dBType==null) return "String";
		if (dBType.equals("BOOLEAN"))
			return  "Boolean";					
		if (dBType.equals("BIGINT"))
			return  "Long";	
		if (dBType.equals("DOUBLE"))
			return  "java.math.BigDecimal";	
		if (dBType.equals("FLOAT"))
			return  "java.math.BigDecimal";			
		if (dBType.equals("INT"))
			return  "Integer";		
		if (dBType.equals(DB_TIME_TYPE))
			return  JAVA_TIME_TYPE;
		if (dBType.equals("DECIMAL"))
			return  "java.math.BigDecimal";
		if (dBType.equals("SMALLINT"))
			return  "Integer";	
		if (dBType.equals(DB_TINYINT_TYPE))
			return  "Short";	
		if (dBType.equals("VARCHAR"))
			return  "String";	
		if (dBType.equals("LONGVARCHAR"))
			return  "String";
		if (dBType.equals("VARCHAR2"))
			return  "String";
		if (dBType.equals("VARGRAPHIC"))
			return  "String";
		if (dBType.equals("CHAR"))
			return  "String";		
		if (dBType.equals("INTEGER"))
			return  "Integer";	
		if (dBType.equals("NUMBER"))
			return  "java.lang.Long";
		if (dBType.equals("NUMERIC"))
			return  "java.lang.Integer";		
		if (dBType.equals("DATE"))
			return  "Date";
		if (dBType.equals("TIMESTAMP") || DB_TIMESTAMPZ_TYPE.equals(dBType))
			return  "Timestamp";	
		if (dBType.equals("BLOB"))
			return  "Blob";	
		if (dBType.equals("BINARY"))
			return  "java.sql.Blob";	
		if (dBType.equals("CLOB"))
			return  "Clob";	
//		if (dBType.equals("BIT"))
//			return  "Long";	
		if (dBType.equals("NVARCHAR2"))
			return  "String";	
		if (dBType.equals("NVARCHAR"))
			return  "String";	
		
		// to re implement when externalizing the mapping
		if (dBType.equals("OTHER"))
			return  "String";
		if (dBType.equals("BIT")) //mysql
			return  "Boolean";		
		return "String";		
	}	

	public static String getJavaTypeFromDBType (Column column) {
		return getJavaTypeFromDBType(column.getTypeAlias(),//column.getType(),
				column.getScale(), getDatabaseType(column));
	}

	public static String getJavaTypeFromDBTypeOnly (String dBType, int scale) {
		String retStr=getJavaTypeFromDBType (dBType);	
		if (dBType==null) return retStr;
		if (dBType.equals("DECIMAL") || dBType.equals("NUMERIC") ) {
			if (scale==0)
				return "Long";
			else
				return  "BigDecimal";
		}	
		return retStr;		
	}
	
	public static String getJavaTypeFromDBType (String dBType, int scale, String databaseType) {
		String retStr=getJavaTypeFromDBType (dBType);	
		if (dBType==null) return retStr;
		if (dBType.equals("DECIMAL")) {
			if (scale==0)
				return "Long";
			else
				return  "java.math.BigDecimal";
		}	
		if (dBType.equals("NUMBER")  
				|| dBType.equals("REAL") 
				|| dBType.equals(DB_BYTE_TYPE))
			{
			if (scale==1 )//DB_TYPE_ORACLE.equals(databaseType) && scale==1 )
				return "Boolean";
		}		
		return retStr;		
	}
	
	public static String getJavaType (String input) {
		input = StringUtils.upperCase(input);
		if (input.endsWith("STRING")){
			return "String";
		}
		return "no type found";
	}

	public static boolean isStringType (String dBType) {
		return (getJavaTypeFromDBType(dBType).equals("String"))?true:false;
	}
	
	public static boolean isDateType (String dBType) {
		return (FieldType.DATE.toString().equals(dBType) ||
                FieldType.TIMESTAMP.toString().equals(dBType) ||
                FieldType.TIME.toString().equals(dBType))?true:false;
	}	

	public static boolean isNumberType (String dBType) {
		return (FieldType.BIGINT.toString().equals(dBType) ||
                FieldType.DECIMAL.toString().equals(dBType) ||
                FieldType.INTEGER.toString().equals(dBType))?true:false;
	}
	
	public static boolean isBooleanType (String dBType) {
		return (FieldType.BOOLEAN.toString().equals(dBType)||
                FieldType.BIT.toString().equals(dBType))?true:false;
	}
}
