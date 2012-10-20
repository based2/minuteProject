package net.sf.minuteProject.utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.ForeignKey;
import net.sf.minuteProject.configuration.bean.model.data.Reference;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.system.Property;
import net.sf.minuteProject.utils.property.PropertyUtils;

public class ColumnUtils {
	
	public static String CHECK_CONSTRAINT_PROPERTY_TAG = "checkconstraint";
	
	public static boolean hasDefaultValue (Column column) {
		return (column.getDefaultValue()!=null)?true:false;
	}
	
	public static String getDefaultValue (Column column) {
		return (ColumnUtils.isNumeric(column))?column.getDefaultValue():"\""+column.getDefaultValue()+"\"";
	}
	
	public static List<String> getColumnNames(Table table) {
		List<String> columnNames = new ArrayList<String>();
		for (Column column : table.getColumns()) {
			columnNames.add(column.getName());
		}
		return columnNames;
	}
	public static Column getColumn(Table table, String columnName) {
		if (table!=null && columnName!=null) {
			columnName = columnName.toUpperCase();
			int maxColumn = table.getColumns().length;
			for (int i = 0; i < maxColumn; i++) {
				Column column = table.getColumns()[i];
				if (column.getName().toUpperCase().equals(columnName))
					return column;
			}
		}
		return null;		
	}
	
	public static String getPrimaryKeyClassName (Table table, String columnName) {
		Column column = getPrimaryKeyColumn(table, columnName);
		if (column==null) return "GET PRIMARY KEY COLUMN should not be null";
		return FormatUtils.getJavaName(column.getAlias());
	}
	
	public static Column getPrimaryKeyColumn(Table table, String columnName) {
		if (table!=null && columnName!=null) {
			columnName = columnName.toUpperCase();
			int maxColumn = table.getPrimaryKeyColumns().length;
			for (int i = 0; i < maxColumn; i++) {
				Column column = table.getPrimaryKeyColumns()[i];
				if (column.getName().toUpperCase().equals(columnName))
					return column;
			}
		}
		return null;		
	}
	
	public static boolean isNaturalPk(Column column) {
		if (column!=null && column.isPrimaryKey() && 
		   !(
			column.getType().equals("INT") || 
			column.getType().equals("BIGINT") || 
			column.getType().equals("INTEGER") ||
			column.getType().equals("NUMBER") ||
			column.getType().equals("DECIMAL") ||
			column.getType().equals("SHORT") ||
			column.getType().equals("SMALLINT") ||
			column.getType().equals("REAL") ||
			column.getType().equals("VARBINARY") ||
			column.getType().equals("DOUBLE")
			)
			)
			return true;
		return false;
	}
	
	public static boolean isNumeric(Column column) {
		if (column!=null && 
				(
						column.getType().equals("INT") || 
						column.getType().equals("BIGINT") || 
						column.getType().equals("INTEGER") ||
						column.getType().equals("NUMBER") ||
						column.getType().equals("DECIMAL") ||
						column.getType().equals("SHORT") ||
						column.getType().equals("SMALLINT") ||
						column.getType().equals("REAL") ||
//						column.getType().equals("VARBINARY") ||
						column.getType().equals("DOUBLE")
						)
				)
			return true;
		return false;
	}
	
	public static boolean isPkUserProvided (Column column) {
		return isNaturalPk(column);
	}
	
	public static boolean isUnique (Column column) {
		return TableUtils.isUnique(column.getTable(), column);
	}
	
	public static boolean isForeignKey (Column column) {
		Table table = column.getTable();
		return isForeignKey(column, table);
	}

	public static boolean isForeignKey (Column column, Table table) {
		Reference[] reference = table.getParents();
		for (int i = 0; i < reference.length; i++) {
			if (reference[i].getLocalColumnName().equals(column.getName()))
				return true;
		}
		return false;
	}
	
	public static Table getForeignTable (Column column) {
		Reference reference = getReference(column);
		return (reference!=null)? reference.getForeignTable():null;
	}

	private static Reference getReference (Column column) {
		return ReferenceUtils.getReference(column);
	}
	
	public static boolean isLengthPrecisionColumn(Column column) {
		if (column==null || column.getType()==null) return false;
		if (
			column.getType().equals("CHAR") || 
			column.getType().equals("CHAR2") ||
			column.getType().equals("VARCHAR") ||
			column.getType().equals("VARCHAR2") ||
			column.getType().equals("VARGRAPHIC") ||
			column.getType().equals("VARGRAPHIC2") ||
			column.getType().equals("CLOB")
			)
			return true;
		return false;
	}
	
	public static String getMethodInputParameters (Column columns[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < columns.length; i++) {
			if (i!=0)
				sb.append(", ");
			sb.append(CommonUtils.getFullType2(columns[i]));
			sb.append(" ");
			sb.append(CommonUtils.getJavaVariableName(columns[i].getName()));
		}
		return sb.toString();
	}
	
	public static String getDefaultStuffingForColumn (Column column) {
		if (column.getType().equals("CHAR") || 
			column.getType().equals("CHAR2") ||
			column.getType().equals("VARCHAR") ||
			column.getType().equals("VARCHAR2") ||
			column.getType().equals("VARGRAPHIC") ||
			column.getType().equals("VARGRAPHIC2") ||
			column.getType().equals("CLOB")
				)
			return "\"\"";
		if (column.getType().equals("INT") ||
			column.getType().equals("INTEGER") )
			return "Integer.valueOf(\"-1\")";
		if (column.getType().equals("BIGINT") || 
			column.getType().equals("LONG")   ||
			column.getType().equals("NUMBER") ||
			column.getType().equals("DECIMAL") )
			return "Long.valueOf(\"-1\")";
		if (column.getType().equals("FLOAT"))
			return "java.math.BigDecimal.valueOf(-1)";		
		return "\"\"";
	}
	

	public static boolean isTimeColumn (Column column) {
		if (column.getType().equals("DATE") || 
			column.getType().equals("TIME") || 
			column.getType().equals("TIMESTAMP"))
			return true;		
		return false;
	}
	
	public static Property getCheckConstraintProperty (Column column) {
		return column.getPropertyByTag(CHECK_CONSTRAINT_PROPERTY_TAG);
	}

	public static List<Property> getCheckConstraintValues (Column column) {
		Property checkConstraint = getCheckConstraintProperty(column);
		if (checkConstraint!=null)
			return checkConstraint.getProperties();
		return null;
	}
	
	public static boolean hasCheckConstraint (Column column) {
		if (getCheckConstraintProperty(column)!=null)
			return true;
		return false;
	}

	public static boolean hasFormulaStereotype (Column column) {
		if (hasStereotype(column) && column.getStereotype().getFormula()!=null)
			return true;
		return false;
	}
	
	public static boolean hasStereotype (Column column) {
		if (column.getStereotype()!=null)
			return true;
		return false;		
	}

	public static boolean hasTrigger(Column column) {
//		return (hasTriggerProperty(column) || (column.getTriggers()!=null && column.getTriggers().size()>0));
		return ((column.getTriggers()!=null && column.getTriggers().size()>0));
	}

//	private static boolean hasTriggerProperty(Column column) {
//		for (Property property : column.getProperties()) {
//			if (isTrigger(property))
//				return true;
//		}
//		return false;
//	}
//
//	private static boolean isTrigger(Property property) {
//		return PropertyUtils.isTriggerTag(property);
//	}

	public static boolean belongsToCompositePrimaryKeyNotMany2Many(Column column) {
		return TableUtils.isCompositePrimaryKeyNotMany2Many(column.getTable()) &&
			 isPartOfCompositePrimaryKey(column);
	}
	
	private static boolean isPartOfCompositePrimaryKey(Column column) {
		for (Column col : column.getTable().getPrimaryKeyColumns()) {
			if (col.getName().toLowerCase().equals(column.getName().toLowerCase()))
				return true;
		}
		return false;
	}

	public static boolean isForeignKeyAndNotPartOfCompositeForeignKey(Column column) {
		return (isForeignKey(column) && !isPartOfCompositeForeignKey(column));
	}

	private static boolean isPartOfCompositeForeignKey(Column column) {
		for (ForeignKey fk : column.getTable().getForeignKeys()) {
			return ForeignKeyUtils.containsLocalColumn(fk, column);
		}
		return false;
	}

	public static boolean isEnumColumn(Column column) {
		// TODO implement for string && int column
		return true;
	}

	public static boolean isUsingDefaultAlias(Column column) {
		// TODO Auto-generated method stub
		return (column.getName().toLowerCase().equals(column.getAlias().toLowerCase()));
	}

	public static String getJavaVariableColumnAlias(Column column) {
		return (column!=null)?FormatUtils.getJavaNameVariable(column.getAlias()):"ERROR_NULL_COLUMN_CANNOT_FORMAT_ALIAS";
	}

	public static String asNameStringList(List<Column> beans) {
		StringBuffer sb = new StringBuffer();
		int cpt=0;
		int size = beans.size();
		for (GeneratorBean bean : beans) {
			sb.append(bean.getName());
			if (cpt<size) {
				sb.append(",");
				cpt++;
			}
		}
		return sb.toString();
	}
	
}
