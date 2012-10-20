package net.sf.minuteProject.utils;

import java.util.StringTokenizer;

import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.ForeignKey;
import net.sf.minuteProject.configuration.bean.model.data.Table;

import org.apache.commons.lang.StringUtils;

public class DBTemplateUtils {
	
	
	public static String getDirFromPackage (String packageSt) {
		return StringUtils.replace(packageSt,".","//");
	}	
	
	public static String getJavaTypeFromDBType (String dBType) {
		String retStr=null;
		if (dBType.equals("BOOLEAN"))
			return  "Boolean";			
		if (dBType.equals("BIGINT"))
			return  "Long";	
		if (dBType.equals("DOUBLE"))
			return  "Double";			
		if (dBType.equals("INT"))
			return  "Integer";		
		if (dBType.equals("TIME"))
			return  "Timestamp";			
		if (dBType.equals("DECIMAL"))
			return  "java.math.BigDecimal";
		if (dBType.equals("SMALLINT"))
			return  "String";	
		if (dBType.equals("VARCHAR"))
			return  "String";	
		if (dBType.equals("CHAR"))
			return  "String";		
		if (dBType.equals("INTEGER"))
			return  "Integer";	
		if (dBType.equals("DATE"))
			return  "Date";
		if (dBType.equals("TIMESTAMP"))
			return  "Timestamp";	
		if (dBType.equals("BLOB"))
			return  "Blob";	
		if (dBType.equals("CLOB"))
			return  "Clob";	
		return retStr;		
	}	

	public static String getJavaTypeFromDBType (String dBType, int scale) {
		String retStr=getJavaTypeFromDBType (dBType);		
		if (dBType.equals("DECIMAL")) {
			if (scale==0)
				return "Long";
			else
				return  "java.math.BigDecimal";
		}	
		return retStr;		
	}
	
	public static boolean isToGenerate(Table table, String template) {
		return isToGenerate(table);
	}	

	public static boolean isToGenerate(Table table) {
		if (table.getName().startsWith("IMPORT"))
			return false;
		if (table.getName().toUpperCase().startsWith("B2B"))
			return false;
		if (table.getName().toUpperCase().startsWith("QRTZ"))
			return false;		
		//if (table.getName().startsWith("APPLIED"))
		//	return false;			
		return true;
	}		

	public static String firstUpperCaseOnly (String st) {
		return st.substring(0,1).toUpperCase() +st.substring(1,st.length()).toLowerCase();
	}
	public static String firstLowerCaseOnly (String st) {
		return st.substring(0,1).toLowerCase() +st.substring(1,st.length()).toLowerCase();
	}	
	public static String firstLowerCase (String st) {
		return st.substring(0,1).toLowerCase() +st.substring(1,st.length());
	}
	public static String getJavaName(String name) {
		if (name==null)
			return "JAVA_NAME_RETURNS_NULL";
		StringTokenizer st = new StringTokenizer (name,"_");
		StringBuffer sb = new StringBuffer();
		while (st.hasMoreTokens()){
			String token = firstUpperCaseOnly(st.nextToken());
			sb.append(token);
		}
		return sb.toString();
		//return name.substring(0,1).toUpperCase() +name.substring(1,name.length()).toLowerCase();
	}	
	
	public static String getJavaNameVariable(String name) {
		if (name==null || name.equals(""))
			return "ERROR_GET_JAVANAMEVARIABLE_WITH_NULL";
		String javaname = getJavaName(name);
		return firstLowerCase(javaname);
	}
	public static String getClassNameOnly(Table table) {
		return getClassNameOnly(table.getName());
	}	
	public static String getClassNameOnly(String str) {
		String name = getJavaName(str);
		return name.substring(0,1).toUpperCase() +name.substring(1,name.length());
	}
	public static String getClassName(Table table) {
		return getClassNameOnly(table)+table.getType();
	}	

	
//	public static List getParents (Database database, Table table) {
//		List list = new ArrayList();
//		//Table tableRef;
//		//table.getForeignKey(0).getReference(0).getLocalColumn();
//		String columnRef;
//		net.sf.minuteProject.configuration.bean.model.data.Reference ref;
//		Reference reference;
//		ForeignKey [] foreignKeys = table.getForeignKeys();
//		for (int i = 0; i < foreignKeys.length; i++) {
//			ref = foreignKeys[i].getFirstReference();
//			String tableName = foreignKeys[i].getForeignTableName();
//			String columnName = ref.getLocalColumnName();
//		    Table table2 = TableUtils.getTable(database,tableName);
//		    Column column2 = ColumnUtils.getColumn (table2, columnName);
//			reference = new Reference(table2, column2, tableName, columnName);
//			//reference.setTableName(tablename);
//			//reference.setColumnName(ref.getLocalColumnName());
//			//reference.setTable(TableUtils.getTable(database,tablename));
//			list.add(reference);				
//		}
//		return list;
//	}			
//	
//	public static List getReference (Database database, Table table) {
//		List list = new ArrayList();
//		//Table tableRef;
//		//table.getForeignKey(0).getReference(0).getLocalColumn();
//		String columnRef;
//		net.sf.minuteProject.configuration.bean.model.data.Reference ref;
//		Reference reference;
//		Table [] tables = database.getTables();
//    	for (int i = 0; i < tables.length; i++) {
//    		ForeignKey [] fk = tables[i].getForeignKeys();
//    		//fk[j].get
//        	for (int j = 0; j < fk.length; j++) {
//        		String tableName = fk[j].getForeignTableName();
//        		if (tableName!=null) {
//	        		if (tableName.equals(table.getName())) {
//	        			columnRef = new String();
//	        			ref = fk[j].getReference(0);
//	        			columnRef = ref.getLocalColumnName();
//	        			reference = new Reference(tables[i], ColumnUtils.getColumn(tables[i], columnRef),tables[i].getName(), columnRef);
//	        			//reference.setTableName(tables[i].getName());
//	        			//reference.setColumnName(columnRef);
//	        			//reference.setTable(tables[i]);
//	        			list.add(reference);
//	        		}
//        		}
//        	}    	
//        }		
//    	return list;
//	}
	
	public static boolean isPrimaryKey (Column column, Table table) {
		Column [] columns = table.getPrimaryKeyColumns();
		for (int i = 0; i < columns.length; i++) {
			if (column.getName().equals(columns[i].getName())) 
				return true;
		}
		return false;
	}

	public static String getForeignKeyTableName (Column column, Table table) {
		ForeignKey [] foreignKey = table.getForeignKeys();
    	for (int j = 0; j < foreignKey.length; j++) {
    		String fkName = foreignKey[j].getReferences()[0].getLocalColumnName();
    		if (fkName!=null) {
        		if (fkName.equals(column.getName())) {
        			return foreignKey[j].getForeignTableName();
        		}
    		}
    	}
		return "";
	}	
	
	public static int getColumLength(Column[] column) {
		return column.length;
	}
	
	/*public static Enumeration getPackageList (Database database) {
		Hashtable ht = new Hashtable();
		Table tables [] = database.getTables();
		for (int i = 0; i < tables.length; i++) {
			if (isToGenerate(tables[i])) {
				String packageName = getSubPackage(tables[i]);
				Package pack = (Package)ht.get(packageName);
				if (pack == null) {
					pack = new Package();
					pack.setName(packageName);
					ht.put(packageName, pack);
				}
				pack.addTable(tables[i]);
			}
		}
		return ht.elements();
		
	}*/
}
