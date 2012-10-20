package net.sf.minuteProject.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.minuteProject.configuration.bean.DataModel;
import net.sf.minuteProject.configuration.bean.model.data.Database;
import net.sf.minuteProject.configuration.bean.model.data.Function;
import net.sf.minuteProject.configuration.bean.model.data.FunctionColumn;
import net.sf.minuteProject.configuration.bean.model.data.constant.Direction;
import net.sf.minuteProject.configuration.bean.model.data.impl.DDLUtils.FunctionColumnDDLUtils;
import net.sf.minuteProject.configuration.bean.model.data.impl.DDLUtils.FunctionDDLUtils;

public class FunctionUtils {
	
	private static Logger log = Logger.getLogger(FunctionUtils.class);
	private static Map<String, Function> functions;
	
	public static List<Function> getFunctions(DataModel dataModel, Database database) {
		
		Connection connection = ConnectionUtils.getConnection(dataModel);
		if (connection!=null) {
			String schema = dataModel.getSchema();
			return getFunctions (connection, schema, database);
		}
		return new ArrayList<Function>();
	}
	
	public static List<Function> getFunctions(Connection connection, String schema, Database database) {
		functions = new HashMap<String, Function>();
		Function function = null;
		try {
			DatabaseMetaData dbMetaData = connection.getMetaData();
			String catalog = connection.getCatalog();
			ResultSet rs;
			if ("MYSQL".equals(database.getType()))
				rs = dbMetaData.getProcedureColumns(catalog, schema, "%", "%");
			else
				rs = dbMetaData.getProcedureColumns(catalog, schema, null, null);
	
//		    String previousProcedureCatalog = "";
//		    String previousProcedureName    = "";
		    while(rs.next()) {
		      // get stored procedure metadata
		      String procedureCatalog     = rs.getString(1);
//		      String procedureSchema      = rs.getString(2);
		      String procedureName        = rs.getString(3);
		      String columnName           = rs.getString(4);
		      short  columnReturn         = rs.getShort(5);
		      int    columnDataType       = rs.getInt(6);
		      String columnReturnTypeName = rs.getString(7);
		      int    columnPrecision      = rs.getInt(8);
		      int    columnByteLength     = rs.getInt(9);
		      short  columnScale          = rs.getShort(10);
		      short  columnRadix          = rs.getShort(11);
		      short  columnNullable       = rs.getShort(12);
		      String columnRemarks        = rs.getString(13);
   
		      function = getFunction (procedureCatalog, catalog, procedureName);
//		      if (procedureCatalog==null  || !procedureCatalog.equals(previousProcedureCatalog) || !procedureName.equals(previousProcedureName)) {
//		    	 if (function!=null)
//		    		 functions.add(function);
//		    	 function = new FunctionDDLUtils();
//		    	 if (procedureCatalog!=null)
//		    		 function.setCatalog(procedureCatalog);
//		    	 else
//		    		 function.setCatalog(catalog);
//		         function.setName(procedureName);
//		      }
//		      previousProcedureCatalog = (procedureCatalog!=null)?procedureCatalog:"";
//		      previousProcedureName    = (procedureName!=null)?procedureName:"";
//		      
		      FunctionColumn functionColumn = new FunctionColumnDDLUtils();
		      functionColumn.setName(columnName);
		      Direction dir = getDirection(columnReturn);
		      functionColumn.setDirection(dir);
		      functionColumn.setTypeCode(columnDataType);
		      functionColumn.setType(columnReturnTypeName);//getType(columnDataType));
		      functionColumn.setPrecisionRadix(columnRadix);
		      functionColumn.setSizeAndScale(columnByteLength, columnScale);
		      functionColumn.setSize(columnByteLength+"");
		      functionColumn.setPrecision(columnPrecision);
		      functionColumn.setRequired((columnNullable==0)?true:false);
		      functionColumn.setDescription(columnRemarks);
		      function.addColumn(functionColumn);
		      
		      function.setDatabase(database);
		      functions.put(getKey(procedureCatalog, catalog, procedureName), function);
		    }
		    
		    connection.close();
		    return new ArrayList<Function>(functions.values());
		} catch (Exception e) {
			log.error("Problem handling store procedures "+e.getMessage());
		}
		return new ArrayList<Function>();
	}
	
	private static Function getFunction(String procedureCatalog, String catalog,
			String procedureName) {
		Function function = functions.get(getKey(procedureCatalog, catalog, procedureName));
		if (function!=null) return function;
		function = new FunctionDDLUtils();
	   	 if (procedureCatalog!=null)
			 function.setCatalog(procedureCatalog);
		 else
			 function.setCatalog(catalog);
	   	function.setName(procedureName);
		return function;
	}

	private static String getKey(String procedureCatalog, String catalog,String procedureName) {
		return catalog+"-"+procedureCatalog+"-"+procedureName;
	}

	private static Direction getDirection(int direction) {
		if (direction==1)
			return Direction.IN;
		if (direction==4)
			return Direction.OUT;
		if (direction==5)
			return Direction.RETURN;
		return Direction.INOUT;
	}
	
}
