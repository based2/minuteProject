package net.sf.minuteProject.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import net.sf.minuteProject.configuration.bean.Reference;
import net.sf.minuteProject.configuration.bean.enrichment.Field;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Database;
import net.sf.minuteProject.configuration.bean.model.data.ForeignKey;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.model.data.impl.DDLUtils.ReferenceDDLUtils;
import net.sf.minuteProject.plugin.format.I18nUtils;

public class ReferenceUtils {
	public static Logger log = Logger.getLogger(ReferenceUtils.class);

//	public static String getLocalColumnClassName (Database database, Column column) {
//		Column c = getColumnFromPackage(database, column);
//		if (column==null) return "COLUMN should not be null!";
//		return FormatUtils.getJavaName(c.getAlias());
//	}
	
//	public static String getLocalColumnVariableName (Database database, Column column) {
//		Column c = getLocalColumnFromPackage(database, column);
//		if (column==null) return "COLUMN should not be null!";
//		return FormatUtils.getJavaNameVariable(c.getAlias());
//	}
	public static String getColumnClassNameForLinkTable(Database database, Column column) {
//		Column c = getPrimaryColumnForLinkTable(database, column);
//		if (c==null) return "COLUMN should not be null!";
		return FormatUtils.getJavaName(column.getAlias());
	}
	public static String getColumnClassAliasUpperCaseForLinkTable(Database database, Column column) {
		Column c = getPrimaryColumnForLinkTable(database, column);
		if (c==null) return "COLUMN should not be null!";
		return FormatUtils.upperCase(c.getAlias());
	}

	private static Column getPrimaryColumnForLinkTable(Database database, Column column) {
		if (column==null) return null;
		Table table = TableUtils.getEntityFromBusinessPackageRefresh(database, column.getTable().getName());
		if (table!=null) {
			return TableUtils.getPrimaryFirstColumn(table);
		}
		return null;
	}
//	public static boolean isMasterRelationship
//	    (Table table, net.sf.minuteProject.configuration.bean.model.data.Reference linkReference) {
//		
////		Table m2m = linkReference.getLocalTable();
////		if (!m2m.isManyToMany()) return false;
//		net.sf.minuteProject.configuration.bean.model.data.Reference ref =getFirstChildReference(linkReference.getLocalTableName(), linkReference.getLocalColumnName(), table);
//		if (ref!=null && ref.isMasterRelationship())
//			return true;		
////		for (ForeignKey fk : m2m.getForeignKeys()) {
////			net.sf.minuteProject.configuration.bean.model.data.Reference ref = fk.getFirstReference();
////			if (!ref.equals(linkReference) && ref.isMasterRelationship())
////				return true;
////		}
//		return false;
//	}
	
	public static String getChildrenListVariable(net.sf.minuteProject.configuration.bean.model.data.Reference reference) {
		if (reference==null)
			return "ERROR_REFERENCE_IS_NUL";
		return FormatUtils.getJavaNameVariable(reference.getAlias());
	}
	
	public static String getChildrenListClass(net.sf.minuteProject.configuration.bean.model.data.Reference reference) {
		if (reference==null)
			return "ERROR_REFERENCE_IS_NUL";
		return FormatUtils.getJavaName(reference.getAlias());
	}
	
	public static void setReferenceColumnAlias(Column column, String name, String newName) {
		Database database = column.getTable().getDatabase();
		Table table = TableUtils.getTable(database, column.getTable().getName());
		if (table==null) {
			log.error("> table : "+column.getTable().getName()+", column : "+column.getName()+" not found");
			return;
		}
		for (net.sf.minuteProject.configuration.bean.model.data.Reference ref : table.getParents()) {
			if (name.equals(ref.getLocalColumn().getName())) 
				ref.getLocalColumn().setAlias(newName);
		}
		for (net.sf.minuteProject.configuration.bean.model.data.Reference ref : table.getChildren()) {
			if (name.equals(ref.getLocalColumn().getName())) 
				ref.getLocalColumn().setAlias(newName);
		}
	}
	
	public static net.sf.minuteProject.configuration.bean.model.data.Reference getReference (Table table, String columnName) {
		Column column = ColumnUtils.getColumn(table, columnName);
		return getReference(column);
	}

	public static List<net.sf.minuteProject.configuration.bean.model.data.Reference> getAllOtherReferences(Table table, String masterRelationshipField) {
		List<net.sf.minuteProject.configuration.bean.model.data.Reference> references = new ArrayList<net.sf.minuteProject.configuration.bean.model.data.Reference>();
		for (net.sf.minuteProject.configuration.bean.model.data.Reference ref: table.getParents()) {
			if (!ref.getForeignColumnName().equals(masterRelationshipField)) {
				references.add(ref);
			}
		}
		return references;
	}
	
	public static net.sf.minuteProject.configuration.bean.model.data.Reference getReference (Column column) {
		if (column==null) return null;
		Table table = column.getTable();
		net.sf.minuteProject.configuration.bean.model.data.Reference[] reference = table.getParents();
		for (int i = 0; i < reference.length; i++) {
			if (reference[i].getLocalColumnName().equals(column.getName()))
				return reference[i];
		}
		return null;		
	}
	
	public Reference getReference(Table table, Column column) {
		Reference reference = new Reference();
		reference.setTableName(table.getName());
		reference.setColumnName(column.getName());
		reference.setTable(table);
		reference.setColumn(column);
		return reference;
	}
	
	public Reference getReference(Table table, Column column, String tableName, String columnName) {
		Reference reference = new Reference();
		reference.setTableName(tableName);
		reference.setColumnName(columnName);
		reference.setTable(table);
		reference.setColumn(column);
		return reference;
	}	
	
	public static String getParentLink (String foreignTableName, String localColumnName) {
		return FormatUtils.getJavaName(foreignTableName)+"_"+FormatUtils.getJavaName(localColumnName);
	}
	
	public static String getParentLinkUML (String foreignTableName, String localColumnName) {
		return FormatUtils.getJavaName(foreignTableName)+"_"+FormatUtils.getJavaName(localColumnName);
	}
	
	public static net.sf.minuteProject.configuration.bean.model.data.Reference getReference(Field field, Database database) {
		org.apache.ddlutils.model.Reference referenceDDLUtils = new org.apache.ddlutils.model.Reference();
//		referenceDDLUtils.setForeignColumn(foreignColu)
		net.sf.minuteProject.configuration.bean.model.data.Reference reference = new ReferenceDDLUtils(referenceDDLUtils);
		String tableName = field.getEntity().getName();
		String columnName = field.getName();
		String foreignTableName = field.getLinkToTargetEntity();

//		Database database = field.getEntity().getEnrichment().getBusinessModel().getModel().getDataModel().getDatabase();
		
		// is it a view ?
		Table table= TableUtils.getTable(database, tableName);
		if (table==null) 
			table = TableUtils.getView(database, tableName);
			
		Column column = ColumnUtils.getColumn(table, columnName);
		
		// is it a view
		Table foreignTable= TableUtils.getTable(database, foreignTableName);
		if (foreignTable==null) 
			foreignTable = TableUtils.getView(database, foreignTableName);
			
		String foreignColumnName = field.getLinkToTargetField();
		if (foreignColumnName==null)
			foreignColumnName = TableUtils.getPrimaryKey(foreignTable);
		Column foreignColumn = ColumnUtils.getColumn(foreignTable, foreignColumnName);
		
		if (table!=null && column != null && foreignTable!=null && foreignColumn!=null) {
			reference.setLocalTable(table);
			reference.setLocalColumn(column);
			reference.getLocalColumn().setAlias(reference.getLocalColumn().getAlias());
			reference.setForeignTable(foreignTable);
			reference.setForeignColumn(foreignColumn);
			reference.getForeignColumn().setAlias(reference.getForeignColumn().getAlias());
			if (!StringUtils.isEmpty(field.getLinkReferenceAlias())) {
				//id set
//				reference.getForeignColumn().setAlias(field.getLinkReferenceAlias());
				//
//				reference.getLocalColumn().setAlias(field.getLinkReferenceAlias());
//				reference.setAlias(field.getLinkReferenceAlias());
			}
			
			reference.setLocalColumnName(columnName);
			reference.setLocalTableName(tableName);
			reference.setForeignColumnName(foreignColumnName);
			reference.setForeignTableName(foreignTableName);
			return reference;
		}
		return null;
		
	}

	public static net.sf.minuteProject.configuration.bean.model.data.Reference getFirstChildReference 
	    (String originTable, String originField, Table target) {
		net.sf.minuteProject.configuration.bean.model.data.Reference [] refs = target.getChildren();
		for (int i = 0; i < refs.length; i++) {
			net.sf.minuteProject.configuration.bean.model.data.Reference ref = refs[i];
			if (ref.getForeignTableName().equals(originTable) && ref.getForeignColumnName().equals(originField))
				return ref;
		}
		return null;
	}
	
	public static net.sf.minuteProject.configuration.bean.model.data.Reference getReference (Table origin, Table target) {
		net.sf.minuteProject.configuration.bean.model.data.Reference [] refs = origin.getParents();
		for (int i = 0; i < refs.length; i++) {
			net.sf.minuteProject.configuration.bean.model.data.Reference ref = refs[i];
			if (ref.getForeignTable().equals(target))
				return ref;
		}
		return null;
	}

	public static boolean isSimilarReference(
			net.sf.minuteProject.configuration.bean.model.data.Reference reference,
			net.sf.minuteProject.configuration.bean.model.data.Reference otherReference) {
		if (reference!=null && otherReference!=null) {
			return (reference.getForeignTableName().equals(otherReference.getForeignTableName()) &&
					  reference.getForeignColumnName().equals(otherReference.getForeignColumnName()) && 
					  reference.getLocalTableName().equals(otherReference.getLocalTableName()) &&
					  reference.getLocalColumnName().equals(otherReference.getLocalColumnName()) 
					  )
					  ||
					  (reference.getForeignTableName().equals(otherReference.getLocalTableName()) &&
						reference.getForeignColumnName().equals(otherReference.getLocalColumnName()) && 
						reference.getForeignTableName().equals(otherReference.getLocalTableName()) &&
						reference.getForeignColumnName().equals(otherReference.getLocalColumnName()) 
							  
					  )
					  ;
		}
		return false;
	}
	
	public static boolean isReferenceInPrimaryKey (net.sf.minuteProject.configuration.bean.model.data.Reference reference) {
		for (Column column : reference.getLocalTable().getPrimaryKeyColumns()) {
			if (column.getName().equals(reference.getLocalColumnName()))
				return true;
		}
		return false;
	}

	public static boolean isUsingDefaultAlias(
			net.sf.minuteProject.configuration.bean.model.data.Reference reference) {
		return (reference.getAlias().equals(getDefaultAlias(reference)));
	}

	public static String getDefaultAlias(net.sf.minuteProject.configuration.bean.model.data.Reference ref) {
		//TODO add for recursive m2m //+"_"+ref.getLocalColumn().getAlias();
		return ref.getForeignTable().getAlias()+"_"+ref.getLocalTable().getAlias()+"_VIA_"+ref.getForeignColumn().getAlias();//+"_"+ref.getLocalColumn().getAlias();
	}

	public static String getColumnAlias(Table table, Column column) {
		Column column2 = ColumnUtils.getColumn(table, column.getName());
		if (column2!=null)
			return column2.getAlias();
		return "COLUMN_IS_NULL";
	}
}
