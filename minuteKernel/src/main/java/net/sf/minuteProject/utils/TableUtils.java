package net.sf.minuteProject.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.minuteProject.configuration.bean.BusinessModel;
import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.configuration.bean.Model;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.enrichment.Entity;
import net.sf.minuteProject.configuration.bean.enrichment.SemanticReference;
import net.sf.minuteProject.configuration.bean.enrichment.Trigger;
import net.sf.minuteProject.configuration.bean.enumeration.CRUDEnum;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Database;
import net.sf.minuteProject.configuration.bean.model.data.ForeignKey;
import net.sf.minuteProject.configuration.bean.model.data.Index;
import net.sf.minuteProject.configuration.bean.model.data.Reference;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.model.data.View;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class TableUtils {

	public static final String pseudoStaticDataContentType = "pseudo-static-data";
	public static final String referenceDataContentType = "reference-data";
	public static final String masterDataContentType = "master-data"; // immutable
																		// data
	public static final String liveBusinessDataContentType = "live-business-data";
	public static Logger log = Logger.getLogger(TableUtils.class);

	public boolean isTable(Template template, GeneratorBean bean) {
		if (bean instanceof View)
			return false;
		if (bean instanceof Table)
			return true;
		return false;
	}

	public static Column getPrimaryFirstColumn(Table table) {
		if (table == null)
			return null;
		Column primaryKeyColumn[] = table.getPrimaryKeyColumns();
		if (primaryKeyColumn.length < 1)
			return null; // ID is the default pk
		return primaryKeyColumn[0];
	}

	public static String getPrimaryKey(Table table) {
		Column primaryKeyColumn = getPrimaryFirstColumn(table);
		if (primaryKeyColumn != null)
			return primaryKeyColumn.getName();
		// check if there is a virtual primary key
		// String virtualPrimaryKey = getVirtualPrimaryKey(table);
		// if (virtualPrimaryKey!=null) {
		// return virtualPrimaryKey;
		// }
		return "";
	}

	public static String getPrimaryKeyType(Table table) {
		return getPrimaryFirstColumn(table).getType();
	}

	// public static String getUnique (Table table) {
	// Index indexes [] = table.getUniqueIndices();
	// if (indexes.length<1)
	// return ""; //ID is the default pk
	// // to change when the indexes will be used.
	// return indexes[0].getName();
	// //return
	// table.getForeignKey(0).getFirstReference().getForeignColumnName();
	// }

	public static Table getTable(Database database, String tablename) {
		return getEntity(database, tablename);
	}

	public static Table getTableFromAlias(Database database, String tablename) {
		return getEntityFromAlias(database, tablename);
	}

	public static Table getTransferEntity(Database database, String tablename) {
		for (Table table : database.getDataModel().getModel()
				.getBusinessModel().getBusinessPackage().getTransferEntities()) {
			if (tablename.equals(table.getName()))
				return table;
		}
		return null;
	}

	public static Table getEntityFromBusinessPackageRefresh(Database database,
			String tablename) {
		for (Table table : database.getDataModel().getModel()
				.getBusinessModel().getBusinessPackage().refreshEntities()) {
			if (tablename.toLowerCase().equals(table.getName().toLowerCase()))
				return table;
		}
		return null;
	}

	public static Table getTableOnly(Database database, String tablename) {
		int maxTable = database.getTables().length;
		for (int i = 0; i < maxTable; i++) {
			Table table = database.getTables()[i];
			if (table.getName() != null
					&& table.getType() != null
					&& tablename != null
					&& table.getName().toUpperCase()
							.equals(tablename.toUpperCase())
					&& table.getType().equals(Table.TABLE))
				return table;
		}
		return null;
	}

	public static Table getTableOnlyFromAlias(Database database,
			String tablename) {
		int maxTable = database.getTables().length;
		for (int i = 0; i < maxTable; i++) {
			Table table = database.getTables()[i];
			// System.out.println("table.getAlias()="+table.getAlias()+"-name:"+table.getName());
			if (table.getAlias() != null
					&& table.getType() != null
					&& tablename != null
					&& table.getAlias().toUpperCase()
							.equals(tablename.toUpperCase())
					&& table.getType().equals(Table.TABLE))
				return table;
		}
		return null;
	}

	public static String getVirtualPrimaryKey(Table table) {
		if (table instanceof View) {
			// Column virtualPrimaryKey =
			// getVirtualPrimaryKeyFirstColumn((View)table);
			return getVirtualPrimaryKeyFirstColumn((View) table).getName();
		}
		return null;
	}

	private static Column getVirtualPrimaryKeyFirstColumn(View view) {
		Column primaryKeyColumn[] = view.getVirtualPrimaryKeys();
		if (primaryKeyColumn.length < 1)
			return null; // ID is the default pk
		return primaryKeyColumn[0];
	}

	public static boolean hasOnlyOnePrimaryKey(Table table) {
		return (table.getPrimaryKeyColumns() != null && table
				.getPrimaryKeyColumns().length == 1) ? true : false;
	}

	public static boolean hasUniqueKey(Table table) {
		return (table.getUniqueIndices() != null && table.getUniqueIndices().length > 0) ? true
				: false;
	}

	public static boolean isUnique(Table table, Column column) {
		if (table == null)
			return false;
		if (column == null)
			return false;
		Index indexes[] = table.getUniqueIndices();
		if (indexes != null) {
			for (int i = 0; i < indexes.length; i++) {
				if (indexes[i] != null) {
					Column[] indexColumn = indexes[i].getColumns();
					if (indexColumn != null) {
						// for (int j = 0; j < indexColumn.length; j++) {
						if (indexColumn.length == 1
								&& indexColumn[0] != null
								&& indexColumn[0].getName() != null
								&& indexColumn[0].getName().equals(
										column.getName()))
							return true;
						// }
					}
				}
			}
		}
		return false;
	}

	public static boolean isMany2Many(Table table) {
		if (table == null)
			return false;
		return table.isManyToMany();
	}

	public static boolean isColumnPk(Column column, Table table) {
		Column[] pks = table.getPrimaryKeyColumns();
		for (int i = 0; i < pks.length; i++) {
			if (pks[i].getName().equals(column.getName()))
				return true;
		}
		return false;
	}

	public static View getView(Database database, String viewname) {
		int maxView = database.getViews().length;
		View view = null;
		for (int i = 0; i < maxView; i++) {
			view = database.getViews()[i];
			viewname = StringUtils.upperCase(viewname);
			String viewName = StringUtils.upperCase(view.getName());
			if (viewName.equals(viewname))
				return view;
		}
		return null;
		// return convertTableToView(database, getTable(database, viewname));
	}

	public static View getViewFromAlias(Database database, String viewname) {
		int maxView = database.getViews().length;
		View view = null;
		for (int i = 0; i < maxView; i++) {
			view = database.getViews()[i];
			viewname = StringUtils.upperCase(viewname);
			String viewAlias = StringUtils.upperCase(view.getAlias());
			if (viewAlias.equals(viewname))
				return view;
		}
		return null;
	}

	private static View convertTableToView(Database database, Table table) {
		return database.addView(table);
	}

	public static String getTargetType(Database database, Entity entity) {
		if (getTableOnly(database, entity.getName()) != null)
			return Table.TABLE;
		else if (getView(database, entity.getName()) != null)
			return Table.VIEW;
		return "NO_TYPE";
	}

	public static Table getEntity(Database database, String name) {
		Table table = getTableOnly(database, name);
		if (table == null)
			table = (Table) getView(database, name);
		return table;
	}

	public static Table getEntityFromAlias(Database database, String name) {
		Table table = getTableOnlyFromAlias(database, name);
		if (table == null)
			table = (Table) getViewFromAlias(database, name);
		return table;
	}

	public static boolean isView(Table table) {
		if (table instanceof View) {
			return true;
		}
		return false;
	}

	public static boolean isView(Database database, String viewname) {
		View view = getView(database, viewname);
		return (view == null) ? false : true;
	}

	public static boolean isTableOfContentType(Table table, String contentType) {
		if (table != null && contentType.equals(table.getContentType()))
			return true;
		return false;
	}

	public static boolean isReferenceDataContentType(Table table) {
		return isTableOfContentType(table, referenceDataContentType);
	}

	public static boolean isPseudoStaticDataContentType(Table table) {
		return isTableOfContentType(table, pseudoStaticDataContentType);
	}

	public static boolean isLiveBusinessDataContentType(Table table) {
		return isTableOfContentType(table, liveBusinessDataContentType);
	}

	public static boolean hasSemanticReference(Table table) {
		SemanticReference semanticReference = table.getSemanticReference();
		if (semanticReference != null) {
			if (!semanticReference.getSemanticReferenceBeanPath().isEmpty())
				return true;
		}
		return false;
	}

	public static List<Column> getSemanticReferenceColumns(Table table) {
		List<Column> columns = new ArrayList<Column>();
		SemanticReference semanticReference = table.getSemanticReference();
		if (semanticReference != null) {
			for (String sqlPath : semanticReference
					.getSemanticReferenceSqlPath()) {
				Column column = ColumnUtils.getColumn(table, sqlPath);
				if (column != null)
					columns.add(column);
			}
		}
		return columns;
	}

	public static List<Column> getLinkSemanticReferenceColumns(Table table) {
		List<Column> columns = new ArrayList<Column>();
		for (Table child : getParents(table)) {
			List<Column> list = getSemanticReferenceColumns(child);
			columns.addAll(list);
		}
		return columns;
	}

	public static List<Table> getChildren(Table table) {
		List<Table> children = new ArrayList<Table>();
		for (Reference reference : table.getChildren()) {
			children.add(reference.getForeignTable());
		}
		return children;
	}

	public static boolean hasChild(Table table) {
		return getChildren(table).size() > 0;
	}

	public static List<Table> getParents(Table table) {
		List<Table> parents = new ArrayList<Table>();
		for (Reference reference : table.getParents()) {
			parents.add(reference.getForeignTable());
		}
		return parents;
	}

	public static boolean hasParent(Table table) {
		return getParents(table).size() > 0;
	}

	public static Column getVersionColumn(Table table) {
		return getFirstVersionColumn(table);
	}

	private static Column getFirstVersionColumn(Table table) {
		for (Column column : table.getColumns()) {
			if (column.isVersion() && !column.isPrimaryKey()) {
				return column;
			}
		}
		return null;
	}

	public static String getEntityAfterRootPackage(Table table,
			Template template, String targetTemplateName) {
		Model model = table.getDatabase().getDataModel().getModel();
		String modelRootPackage = ModelUtils.getModelRootPackage(model);
		String packageName = CommonUtils.getEntityLevelTemplateFullPath(model,
				table, template, targetTemplateName);
		return StringUtils.removeStart(packageName, modelRootPackage + ".");
	}

	public static List<Reference> getParentOrderByReferenceData(Table table) {
		List<Reference> parentsOrder = new ArrayList<Reference>();
		List<Reference> parentsLeft = new ArrayList<Reference>();
		Reference[] parents = table.getParents();
		for (Reference reference : parents) {
			if (hasSemanticReference(reference.getForeignTable())) {
				parentsOrder.add(reference);
			} else {
				parentsLeft.add(reference);
			}
		}
		parentsOrder.addAll(parentsLeft);
		return parentsOrder;
	}

	public static List<List<Column>> extractFieldGroup(Column[] columns,
			Table table) {
		List<List<Column>> tableFg = table.getFieldGroupsList();
		List<List<Column>> fgs = new ArrayList<List<Column>>();
		for (Column column : columns) {
			List<Column> fg = extractFieldGroup(column, tableFg);
			if (fg != null && !fgs.contains(fg)) {
				fgs.add(fg);
			}
		}
		return fgs;
	}

	private static List<Column> extractFieldGroup(Column column,
			List<List<Column>> tableFg) {
		for (List<Column> cols : tableFg) {
			if (isColumnInFieldGroup(column, cols))
				return cols;
		}
		List<Column> retList = new ArrayList<Column>();
		retList.add(column);
		return retList;
	}

	private static boolean isColumnInFieldGroup(Column column, List<Column> cols) {
		for (Column col : cols) {
			if (col != null && col.getName() != null
					&& col.getName().equals(column.getName()))
				return true;
		}
		return false;
	}

	public static Column[] getDisplayableAttributes(Table table) {
		List<Column> columns = new ArrayList<Column>();
		for (Column column : table.getColumns()) {
			if (column.isPrimaryKey() && ColumnUtils.isPkUserProvided(column))
				columns.add(column);
			else if (!column.isLob()) {
				columns.add(column);
			}
		}
		return (Column[]) columns.toArray(new Column[columns.size()]);
	}

	public static boolean hasChild(Table table, String targetTableName) {
		for (Reference reference : table.getChildren()) {
			if (reference.getForeignTableName().equals(targetTableName))
				return true;
		}
		return false;
	}

	
	public static boolean hasTrigger(Table table, CRUDEnum crud) {
		return (getTriggers(table, crud).isEmpty())?false:true;
	}
	
	public static boolean hasInsertTrigger(Table table) {
		return hasTrigger(table, CRUDEnum.INSERT);
	}
	
	public static boolean hasUpdateTrigger(Table table) {
		return hasTrigger(table, CRUDEnum.UPDATE);
	}

	public static List<Trigger> getInsertTriggers(Table table) {
		return getTriggers(table, CRUDEnum.INSERT);
	}
	
	public static List<Trigger> getUpdateTriggers(Table table) {
		return getTriggers(table, CRUDEnum.UPDATE);
	}
	
	public static List<Trigger> getTriggers(Table table, CRUDEnum crud) {
		List<Trigger> triggers = new ArrayList<Trigger>();
		for (Column column : table.getColumns()) {
			List<Trigger> columnTriggers = column.getTriggers();
			for (Trigger t : columnTriggers) {
				if (t.isOfType(crud))
					triggers.add(t);
			}
		}
		return triggers;
	}
	
	public static boolean hasTrigger(Table table) {
		for (Column column : table.getColumns()) {
			if (ColumnUtils.hasTrigger(column))
				return true;
		}
		return false;
	}

	public static List<Column> getPrimaryKeyNotForeignKeyColumns(Table table) {
		List<Column> columns = new ArrayList<Column>();
		for (Column column : table.getPrimaryKeyColumns()) {
			if (!ColumnUtils.isForeignKey(column))
				columns.add(column);
		}
		return columns;
	}

	public static List<Column> getPrimaryKeyAndForeignKeyColumns(Table table) {
		List<Column> columns = new ArrayList<Column>();
		for (Column column : table.getPrimaryKeyColumns()) {
			if (ColumnUtils.isForeignKey(column))
				columns.add(column);
		}
		return columns;
	}

	public static List<Column> getPrimaryKeyAndForeignKeyColumnsAndNotPartOfCompositeForeignKey(
			Table table) {
		List<Column> columns = new ArrayList<Column>();
		for (Column column : table.getPrimaryKeyColumns()) {
			if (ColumnUtils.isForeignKeyAndNotPartOfCompositeForeignKey(column))
				columns.add(column);
		}
		return columns;
	}

	public static List<ForeignKey> getParentCompositeForeignInPrimaryKey(
			Table table) {
		List<ForeignKey> fks = new ArrayList<ForeignKey>();
		for (ForeignKey fk : table.getForeignKeys()) {
			if (fk.getReferenceCount() > 1) {
				log.error(">>>> composite FK pattern not supported for table "
						+ table.getName());
				for (Column column : table.getPrimaryKeyColumns()) {
					if (ForeignKeyUtils.containsLocalColumn(fk, column))
						fks.add(fk);
					// TODO
					// Take care we do not check if all columns in FK belongs PK
					log.error(">>>> getParentCompositeForeignInPrimaryKey pattern not supported for table "
							+ table.getName());
					break;
				}
			}

		}
		return fks;
	}

	public static boolean isCompositePrimaryKeyNotMany2Many(Table table) {
		if (!table.isManyToMany() && (table.getPrimaryKeyColumns().length > 1
		// )){
				|| getPrimaryKeyAndForeignKeyColumns(table).size() > 0)) {
			return true;
		}
		return false;
	}

	public static int getNumberOfPrimaryColumns(Table table) {
		return (table.getPrimaryKeyColumns() == null) ? 0 : table
				.getPrimaryKeyColumns().length;
	}

	public static Map<String, Table> getPrimaryKeyTableMap(BusinessModel model) {
		Map <String, Table> tables = new HashMap<String, Table>();
		for (Table table : model.getBusinessPackage().getEntities()) {
			if (table.hasPrimaryKey()) {
				Column col = getPrimaryFirstColumn(table);
				String pk = col.getName();
				tables.put(pk, table);
			}
		}
		return tables;
	}
}
