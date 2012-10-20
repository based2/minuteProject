package net.sf.minuteProject.configuration.bean.model.data;

import java.util.List;

import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.configuration.bean.enrichment.Action;
import net.sf.minuteProject.configuration.bean.enrichment.SemanticReference;
import net.sf.minuteProject.configuration.bean.enrichment.group.FieldGroup;
import net.sf.minuteProject.configuration.bean.enrichment.rule.Constraint;
import net.sf.minuteProject.configuration.bean.enrichment.security.EntitySecuredAccess;


public interface Table extends GeneratorBean{
	
	public static final String TABLE = "TABLE";
	public static final String VIEW  = "VIEW";
	
	/**
	 * Get the column that are not part primary key or foreign key 
	 * @return
	 */
	public Column[] getNoPrimaryKeyNoForeignKeyColumns();
	
	
	
	// SQL standard method
    /**
     * Returns the catalog of this table as read from the database.
     * 
     * @return The catalog
     */
    public String getCatalog();

    /**
     * Returns the schema of this table as read from the database.
     * 
     * @return The schema
     */
    public String getSchema();

    public String getName();
    /**
     * Sets the name of the table.
     * 
     * @param name The name
     */
    public void setName(String name);
    
    /**
     * Returns the description of the table.
     *
     * @return The description
     */
    public void setDescription(String description);
    
    /**
     * Returns the number of columns in this table.
     * 
     * @return The number of columns
     */
    public int getColumnCount();

    /**
     * Returns the columns in this table.
     * 
     * @return The columns
     */
    public Column[] getColumns();
    
    /**
     * Returns the number of foreign keys.
     * 
     * @return The number of foreign keys
     */
    public int getForeignKeyCount();
    
    /**
     * Returns the foreign key at the given position.
     * 
     * @param idx The foreign key index
     * @return The foreign key
     */
    public ForeignKey getForeignKey(int idx);
    
    /**
     * Returns the foreign keys of this table.
     * 
     * @return The foreign keys
     */
    public ForeignKey[] getForeignKeys();

    /**
     * Returns the number of indices.
     * 
     * @return The number of indices
     */
    public int getIndexCount();

    /**
     * Returns the index at the specified position.
     * 
     * @param idx The position
     * @return The index
     */
    public Index getIndex(int idx);
    
    /**
     * Returns the indices of this table.
     * 
     * @return The indices
     */
    public Index[] getIndices();

    /**
     * Gets a list of non-unique indices on this table.
     * 
     * @return The unique indices
     */
    public Index[] getNonUniqueIndices();
    
    /**
     * Gets a list of unique indices on this table.
     * 
     * @return The unique indices
     */
    public Index[] getUniqueIndices();

    // Helper methods
    //-------------------------------------------------------------------------

    /**
     * Determines whether there is at least one primary key column on this table.
     * 
     * @return <code>true</code> if there are one or more primary key columns
     */
    public boolean hasPrimaryKey();
    
    /**
     * Finds the column with the specified name, using case insensitive matching.
     * Note that this method is not called getColumn(String) to avoid introspection
     * problems.
     * 
     * @param name The name of the column
     * @return The column or <code>null</code> if there is no such column
     */
    public Column findColumn(String name);

    /**
     * Finds the column with the specified name, using case insensitive matching.
     * Note that this method is not called getColumn(String) to avoid introspection
     * problems.
     * 
     * @param name          The name of the column
     * @param caseSensitive Whether case matters for the names
     * @return The column or <code>null</code> if there is no such column
     */
    public Column findColumn(String name, boolean caseSensitive);
    
    /**
     * Returns the column at the specified position.
     * 
     * @param idx The column index
     * @return The column at this position
     */
    public Column getColumn(int idx);
    
    /**
     * Finds the index with the specified name, using case insensitive matching.
     * Note that this method is not called getIndex to avoid introspection
     * problems.
     * 
     * @param name The name of the index
     * @return The index or <code>null</code> if there is no such index
     */
    
    public Index findIndex(String name);

    /**
     * Finds the index with the specified name, using case insensitive matching.
     * Note that this method is not called getIndex to avoid introspection
     * problems.
     * 
     * @param name          The name of the index
     * @param caseSensitive Whether case matters for the names
     * @return The index or <code>null</code> if there is no such index
     */
    public Index findIndex(String name, boolean caseSensitive);
    
    /**
     * Returns the primary key columns of this table.
     * 
     * @return The primary key columns
     */
    public Column[] getPrimaryKeyColumns();
    
    /**
     * The column that are not Primary Key nor Foreign Key
     * @return The column that are not Primary Key nor Foreign Key
     */
   
    public String toVerboseString();
    
    public String getType();
    
    public void setDatabase (Database database);
    
    // Methods added
    /**
     * Returns the attribute
     * @return Column
     */
    public Column [] getAttributes ();
    
    /**
     * Get the Database for bidirection browsing
     * @return Database
     */
    public Database getDatabase();
    
    /**
     * Get the array of parents 
     * @return Reference
     */
    public Reference [] getParents();
    
    /**
     * Get the associated children
     * @return Reference
     */
    public Reference [] getChildren();
    
    public Reference [] getDistinctChildrenType();
    
    /**
     * Indicates if it is a many to many table
     * @return boolean
     */
    public boolean isManyToMany();
    
    public boolean hasLob();
    
    public boolean hasUniqueIndex();
	
	public String getAlias ();
	
	public void setAlias(String alias);
	
	public boolean isManyToManyRecursive();
	
	public void setContentType(String contentType);
	
	public String getContentType();
	
	public void setSemanticReference (SemanticReference semanticReference);
	
	public SemanticReference getSemanticReference();
	
	public boolean hasVersion();
	
	public boolean isLinkEntity();

	public void setLinkEntity(boolean isLinkEntity);
	
	public boolean hasAttribute ();
	
	public void setEntitySecuredAccess (EntitySecuredAccess entitySecuredAccess);
	
	public EntitySecuredAccess getEntitySecuredAccess();
	
	public void setType (String type);

	public void setPrimaryKeys(Column[] virtualPrimaryKey);

	public void setFieldGroups(List<FieldGroup> fieldGroups);
	
	public List<FieldGroup> getFieldGroups();
	
	public List<List<Column>> getFieldGroupsList ();
	
	public List<Action> getActions();

	public void setActions(List<Action> actions);
	
	public List<Constraint> getConstraints();
	
	public void setConstraints(List<Constraint> constraints);
		
	public void setSearchable(boolean isSearchable);
	
	public boolean isSearchable();
	
	public void setForeignKey(ForeignKey foreignKey);

}
