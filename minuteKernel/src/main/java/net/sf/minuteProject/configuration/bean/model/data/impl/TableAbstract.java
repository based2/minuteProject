package net.sf.minuteProject.configuration.bean.model.data.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.enrichment.Action;
import net.sf.minuteProject.configuration.bean.enrichment.SemanticReference;
import net.sf.minuteProject.configuration.bean.enrichment.group.FieldGroup;
import net.sf.minuteProject.configuration.bean.enrichment.rule.Constraint;
import net.sf.minuteProject.configuration.bean.enrichment.security.EntitySecuredAccess;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Database;
import net.sf.minuteProject.configuration.bean.model.data.ForeignKey;
import net.sf.minuteProject.configuration.bean.model.data.Reference;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.model.data.Index;
import net.sf.minuteProject.utils.ColumnUtils;

/**
 * @author Florian Adler
 *
 */
public abstract class TableAbstract extends AbstractConfiguration implements Table{
	
	private Table table;
//	private String alias;
	private Reference [] distinctChildrenRef;
	private SemanticReference semanticReference;
	private String contentType;
	private boolean isLinkEntity, isSearchable;
	private EntitySecuredAccess entitySecuredAccess;
	private List<List<Column>> fieldGroupsList;
	private List<FieldGroup> fieldGroups;
	private List<Action> actions;
	private List<Constraint> constraints;
	private Boolean hasVersion;
	
	public TableAbstract () {
	}
	
	public TableAbstract (Table table) {
		setTable (table);
		this.setAlias(table.getAlias());
		this.setProperties(table.getProperties());
		this.setContentType(table.getContentType());
		this.setSemanticReference(table.getSemanticReference());
		this.setLinkEntity(table.isLinkEntity());
		this.setFieldGroups(table.getFieldGroups());
		this.setActions(table.getActions());
		this.setSearchable(table.isSearchable());
		this.setComment(table.getComment());
		this.setDescription(table.getDescription());		
		this.setDatabase(table.getDatabase());
		this.setConstraints(table.getConstraints());
	}

	public String getName () {
		return table.getName();
	}
	
	public net.sf.minuteProject.configuration.bean.Package getPackage() {
		return table.getPackage();
	}

	public void setPackage(net.sf.minuteProject.configuration.bean.Package pack) {
		this.table.setPackage(pack);
	}

	public Database getDatabase() {
		return table.getDatabase();
	}

	public void setDatabase(Database database) {
		this.table.setDatabase(database);
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Column findColumn(String name) {
		return table.findColumn(name);
	}

	public Column findColumn(String name, boolean caseSensitive) {
		return table.findColumn(name, caseSensitive);
	}

	public Index findIndex(String name) {
		return table.findIndex(name);
	}

	public Index findIndex(String name, boolean caseSensitive) {
		return table.findIndex(name, caseSensitive);
	}

	public String getCatalog() {
		return table.getCatalog();
	}

	public int getColumnCount() {
		return table.getColumnCount();
	}

	public Column[] getColumns() {
		return table.getColumns();	
	}

    /**
     * Returns the column at the specified position.
     * 
     * @param idx The column index
     * @return The column at this position
     */
    public Column getColumn(int idx) {
    	return table.getColumn(idx);
    }
    
	public ForeignKey getForeignKey(int idx) {
		return table.getForeignKey(idx);
	}

	public int getForeignKeyCount() {
		return table.getForeignKeyCount();
	}

	public ForeignKey[] getForeignKeys() {
		return table.getForeignKeys();
	}

	public Index getIndex(int idx) {
		return table.getIndex(idx);
	}

	public int getIndexCount() {
		return table.getIndexCount();
	}

	public Index[] getIndices() {
		return table.getIndices();
	}

	public Index[] getNonUniqueIndices() {
		return table.getNonUniqueIndices();
	}

	public Column[] getPrimaryKeyColumns() {
		return table.getPrimaryKeyColumns();
	}

	public String getSchema() {
		return table.getSchema();
	}

	public Index[] getUniqueIndices() {
		return table.getNonUniqueIndices();
	}

	public boolean hasPrimaryKey() {
		return table.hasPrimaryKey();
	}
	
	public String getTechnicalPackage(Template template) {
		net.sf.minuteProject.configuration.bean.Package p = getPackage();
		if(p==null) 
			return "ERROR_PACKAGE_IS_NULL";
		return p.getTechnicalPackage(template);
	}

	public Column[] getNoPrimaryKeyNoForeignKeyColumns() {
		return table.getNoPrimaryKeyNoForeignKeyColumns();
	}

	public String toVerboseString() {
		return table.toVerboseString();
	}

	public String getType() {
		return table.getType();
	}

	public Column [] getAttributes () {
		return getColumns();
	}
	
    /**
     * Get the array of parents 
     * @return Reference
     */
    public Reference [] getParents() {
    	return table.getParents();
    }
    
    /**
     * Get the associated children
     * @return Reference
     */
    public Reference [] getChildren() {
    	return table.getChildren();
    }
    
    /**
     * Indicates if it is a many to many table
     * A table is many to many if it contains 2 columns only and those colums are Foreign keys.
     * @return boolean
     */
    public boolean isManyToMany() {
     	return (getPrimaryKeyColumns().length == 2) 
       && (getParents().length == 2)
       && (getColumnCount()==2);
    }
    
    public boolean equals (Object object) {
    	if (!(object instanceof Table))
    		return false;
    	return this.getName().equals(((Table)object).getName());
    }
	
	public boolean isManyToManyRecursive() {
		if (isManyToMany()) {
		   Reference [] parents = getParents();
		   return parents[0].getForeignTableName().equals(parents[1].getForeignTableName());
		}
		return false;
	}
	
	public Reference [] getDistinctChildrenType() {
		if (distinctChildrenRef==null)
			distinctChildrenRef = getDistinctChildrenTypeArray();
		return distinctChildrenRef;
	}
	
	public Reference [] getDistinctChildrenTypeArray() {
		List<Reference> distinctTypes = new ArrayList<Reference>();

		Reference[] references = getChildren();
		for (int i = 0; i < references.length; i++) {
			boolean toAdd = true;
			for (Reference reference : distinctTypes) {
				if (   reference.getForeignTableName().equals(references[i].getForeignTableName())  
					&& reference.getLocalTableName().equals(references[i].getLocalTableName())	
					) {
					toAdd = false;
					break;
				}
			}	
			if (toAdd)
				distinctTypes.add(references[i]);
		}
		return (Reference []) distinctTypes.toArray(new Reference[distinctTypes.size()]);
	}

	public void setFieldGroups(List<FieldGroup> fieldGroups) {
		this.fieldGroups = fieldGroups;
	}
	
	public List<List<Column>> getFieldGroupsList () {
		//TODO add order with Hashtable
		if (fieldGroupsList==null) {
			fieldGroupsList = new ArrayList<List<Column>>();
			List<Column> cols = new ArrayList<Column>();
			for (Column column : getColumns()) {
				if (!contains(cols, column)) {
					List<Column> columnsFromFieldGroup = getColumnsFromFieldGroup(column);
					fieldGroupsList.add(columnsFromFieldGroup);
					// add group column in cols
					cols.addAll(columnsFromFieldGroup);
				}
			}
		}
		return fieldGroupsList;
	}

	private boolean contains(List<Column> cols, Column column) {
		for (Column col : cols) {
			if (col.getName().equals(column.getName()))
				return true;
		}
		return false;
	}

	private List<Column> getColumnsFromFieldGroup(Column column) {
		List<Column> columns = new ArrayList<Column>();
		for (FieldGroup fieldGroup : getFieldGroups()) {
			if (fieldGroupContainsColumn(fieldGroup, column)) {
				return convertFieldGroup(fieldGroup);
			}
		}
		columns.add(column);
		return columns;
	}

	private List<Column> convertFieldGroup(FieldGroup fieldGroup) {
		List<Column> columns = new ArrayList<Column>();
		for (String element : fieldGroup.getFieldList()) {
			Column column = ColumnUtils.getColumn(table, element);
			if (column!=null)
				columns.add(column);
		}
		return columns;
	}

	private boolean fieldGroupContainsColumn(FieldGroup fieldGroup, Column column) {
		for (String element : fieldGroup.getFieldList()) {
			if (element.equals(column.getName())) {
				return true;
			}
		}
		return false;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public SemanticReference getSemanticReference() {		
		return semanticReference;
	}

	public void setSemanticReference(SemanticReference semanticReference) {
		this.semanticReference = semanticReference;
	}
	
	public boolean isLinkEntity() {
		return isLinkEntity;
	}

	public void setLinkEntity(boolean isLinkEntity) {
		this.isLinkEntity = isLinkEntity;
	}
	
	public boolean hasVersion() {
		if (hasVersion==null)
			hasVersion = getHasVersion();
		return hasVersion;
	}
	
	private Boolean getHasVersion() {
		for(Column column : getColumns()) {
			if (column.isVersion()) return true;
		}
		return false;
	}

	public boolean hasAttribute () {
		return (getAttributes()!=null && getAttributes().length > 0)?true:false;
	}

	public EntitySecuredAccess getEntitySecuredAccess() {
		return entitySecuredAccess;
	}

	public void setEntitySecuredAccess(EntitySecuredAccess entitySecuredAccess) {
		this.entitySecuredAccess = entitySecuredAccess;
	}

	public List<FieldGroup> getFieldGroups() {
		if (fieldGroups==null) fieldGroups = new ArrayList<FieldGroup>();
		return fieldGroups;
	}

	public void addFieldGroup (FieldGroup fieldGroup) {
		getFieldGroups().add(fieldGroup);
	}
	
	public List<Action> getActions() {
		if (actions==null) actions = new ArrayList<Action>();
		return actions;
	}
	
	public void addAction (Action action) {
		getActions().add(action);
	}	
	
	public void setActions(List<Action> actions) {
		this.actions = actions;
	}
	
	public boolean isSearchable() {
		return isSearchable;
	}

	public void setSearchable(boolean isSearchable) {
		this.isSearchable = isSearchable;
	}

	public List<Constraint> getConstraints() {
		return constraints;
	}

	public void setConstraints(List<Constraint> constraints) {
		this.constraints = constraints;
	}
	
}
