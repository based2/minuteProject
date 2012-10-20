package net.sf.minuteProject.configuration.bean.model.data.impl.DDLUtils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Component;
import net.sf.minuteProject.configuration.bean.model.data.ForeignKey;
import net.sf.minuteProject.configuration.bean.model.data.Reference;
import net.sf.minuteProject.configuration.bean.model.data.View;
import net.sf.minuteProject.utils.ForeignKeyUtils;

public class ViewDDLUtils extends TableDDLUtils implements View{

	private Logger log = Logger.getLogger(this.getClass());
	private ArrayList<Column> realPrimaryKeys;
	private ArrayList<Column> virtualPrimaryKeys;
	private ArrayList<Column> noVirtualPrimaryKeyColumns;
	private ArrayList<Reference> parents;
	private ArrayList<Component> components;
	
	
	public ViewDDLUtils (org.apache.ddlutils.model.Table table) {
		super(table);
	}

	public Column[] getRealPrimaryKeys() {
		if (realPrimaryKeys==null)
			realPrimaryKeys = new ArrayList<Column>();
		return (Column[])realPrimaryKeys.toArray();
	}

	public Column[] getVirtualPrimaryKeys() {
		if (virtualPrimaryKeys==null)
			virtualPrimaryKeys = new ArrayList<Column>();
		return (Column[])virtualPrimaryKeys.toArray(new Column[virtualPrimaryKeys.size()]);
	}

	public void setVirtualPrimaryKeys(Column[] virtualPks) {
		for (Column column : virtualPks) {
			addVirtualPrimaryKey(column);
		}
	}

	public void setRealPrimaryKeys(Column[] realPrimaryKeys) {
		for (Column column : realPrimaryKeys) {
			addRealPrimaryKey(column);
		}
	}

	public void addRealPrimaryKey(Column realPrimaryKey) {
		if (realPrimaryKeys==null)
			realPrimaryKeys = new ArrayList<Column>();
		realPrimaryKeys.add(realPrimaryKey);
	}

	public void addVirtualPrimaryKey(Column virtualPrimaryKey) {
		if (virtualPrimaryKeys==null)
			virtualPrimaryKeys = new ArrayList<Column>();
		virtualPrimaryKeys.add(virtualPrimaryKey);	
		for (org.apache.ddlutils.model.Column column : table.getColumns()) {
			if (column.getName().equals(virtualPrimaryKey.getName()))
				column.setPrimaryKey(true);
		}	
	}
	
	public boolean hasPrimaryKey () {
		return !virtualPrimaryKeys.isEmpty();
	}
	
	public Column[] getPrimaryKeyColumns() {
		return getVirtualPrimaryKeys();
	}
	
	public Column [] getAttributes () {
		return getNoVirtualPrimaryKeyColumns();
	}
	
	private Column [] getNoVirtualPrimaryKeyColumns() {
		if (noVirtualPrimaryKeyColumns==null || !isCacheEnabled) {
			noVirtualPrimaryKeyColumns = new ArrayList<Column>();
			boolean present;
			for (Column column : super.getAttributes()) {
				present=false;
				for (Column column2 : getVirtualPrimaryKeys()) {
					if (column2.getName().equals(column.getName())) {
						present=true;
						break;
					}
				}
				if (!present) {
					for (Reference parent: getParents()) {
						if (parent.getLocalColumnName().equals(column.getName())) {
							present=true;
							break;
						}
					}					
				}
				if (!present)
					noVirtualPrimaryKeyColumns.add(column);
			}
		}
		return (Column[])noVirtualPrimaryKeyColumns.toArray(new Column[noVirtualPrimaryKeyColumns.size()]);
	}
	
	public Column [] getNoPrimaryKeyNoForeignKeyColumns() {
		return getAttributes();
	}

	public Component[] getComponents() {
		// if nothing => the view itself is a component
		if (components==null) {
			components = new ArrayList<Component>();
		}
		return (Component[])components.toArray(new Component[components.size()]);
	}
	
	public void setComponents(List<Component> components) {
		this.components = new ArrayList<Component>(components);
	}

	public void setForeignKey(ForeignKey foreignKey) {
		getForeignKeyList().add(foreignKey);
	}
	
	public Reference [] getParents() {
//		return super.getParents();
		return getParentsWithLocalForeignKey();
	}
	
    protected Reference [] getParentsWithLocalForeignKey() {
    	if (parents == null) {
    		parents = new ArrayList<Reference>();
    		for (int i = 0; i < getForeignKeys().length; i++) {
    			ForeignKey foreignKey = getForeignKeys()[i];
//    			Reference reference = foreignKey.getFirstReference();
//    			//reverse reference view: reference comes from enrichment
//    			Reference ref = new ReferenceDDLUtils (new org.apache.ddlutils.model.Reference());
//    			ref.setForeignColumn(reference.getLocalColumn());
//    			ref.setForeignColumnName(reference.getLocalColumnName());
//    			ref.setForeignTable(reference.getLocalTable());
//    			ref.setForeignTableName(reference.getLocalTableName());
//    			ref.setLocalColumn(reference.getForeignColumn());
//    			ref.setLocalColumnName(reference.getForeignColumnName());
//    			ref.setLocalTable(reference.getForeignTable());
//    			ref.setLocalTableName(reference.getForeignTableName());
    			Reference ref = ReferenceDDLUtils.clone(foreignKey.getFirstReference());

//    			ref.setForeignColumn(reference.getForeignColumn());
//    			ref.setForeignColumnName(reference.getForeignColumnName());
//    			ref.setForeignTable(reference.getForeignTable());
//    			ref.setForeignTableName(reference.getForeignTableName());
//    			ref.setLocalColumn(reference.getLocalColumn());
//    			ref.setLocalColumnName(reference.getLocalColumnName());
//    			ref.setLocalTable(reference.getLocalTable());
//    			ref.setLocalTableName(reference.getLocalTableName());    			
//    			addReference(parents, reference);
//    			log.info("reference "+reference);
//    			log.info("ref       "+ref);
////${localColumnVariable} ${linkedTableVariable} ${linkedColumnVariable}
//    			log.info("localColumnVariable = "+reference.getLocalColumnName());
//    			log.info("linkedTableVariable = "+reference.getLocalTableName());
//    			log.info("linkedColumnVariable = "+reference.getForeignColumnName());
    			addReference(parents, ref);
			}
    	}
//    	log.info("object "+getName()+"- parents : "+parents);
    	return (Reference[])parents.toArray(new Reference[parents.size()]);	
    }	
}
