package net.sf.minuteProject.configuration.bean.model.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import net.sf.minuteProject.configuration.bean.DataModel;
/**
 * It is inspired by the DDLUtils Database Class
 * Represents the database model, ie. the tables in the database. It also
 * contains the corresponding dyna classes for creating dyna beans for the
 * objects stored in the tables.
 *
 * @author Florian Adler
 */
public interface Database extends Serializable, Cloneable
{
	// Minute project standard method
	/**
	 * Load the database
	 */
	public Database loadDatabase (DataModel dataModel) ;
	
	/**
	 * get the type (Oracle, DB2, Sybase, Mysql ...) of the database
	 * @return String
	 */
	public String getType();
	
	// Standard sql methods
    /**
     * Returns the name of this database model.
     * 
     * @return The name
     */
    public String getName();
    /**
     * Sets the name of this database model.
     * 
     * @param name The name
     */
    public void setName(String name);

    /**
     * Returns the version of this database model.
     * 
     * @return The version
     */
    public String getVersion();

    /**
     * Sets the version of this database model.
     * 
     * @param version The version
     */
    public void setVersion(String version);

    /**
     * Returns the method for generating primary key values.
     * 
     * @return The method
     */
    public String getIdMethod();

    /**
     * Sets the method for generating primary key values. Note that this
     * value is ignored by DdlUtils and only for compatibility with Torque.
     * 
     * @param idMethod The method
     */
    public void setIdMethod(String idMethod);

    /**
     * Returns the number of tables in this model.
     * 
     * @return The number of tables
     */
    
    public int getViewCount();

    /**
     * Returns the views in this model.
     * 
     * @return The tables
     */
    public View[] getViews();


    /**
     * Adds a table.
     * 
     * @param table The table to add
     */
    public void addView(View view);

    /**
     * Returns the number of tables in this model.
     * 
     * @return The number of tables
     */
    public int getTableCount();

    /**
     * Returns the tables in this model.
     * 
     * @return The tables
     */
    public Table[] getTables();
    /**
     * Returns the table at the specified position.
     * 
     * @param idx The index of the table
     * @return The table
     */
    public Table getTable(int idx);

    /**
     * Adds a table.
     * 
     * @param table The table to add
     */
    public void addTable(Table table);

    /**
     * Adds a table at the specified position.
     * 
     * @param idx   The index where to insert the table
     * @param table The table to add
     */
    public void addTable(int idx, Table table);

    /**
     * Adds the given tables.
     * 
     * @param tables The tables to add
     */
    public void addTables(Collection tables);

    /**
     * Removes the given table.
     * 
     * @param table The table to remove
     */
    public void removeTable(Table table);

    /**
     * Removes the indicated table.
     * 
     * @param idx The index of the table to remove
     */
    public void removeTable(int idx);
    // Helper methods


    /**
     * Finds the table with the specified name, using case insensitive matching.
     * Note that this method is not called getTable to avoid introspection
     * problems.
     * 
     * @param name The name of the table to find
     * @return The table or <code>null</code> if there is no such table
     */
    public Table findTable(String name);
    
    /**
     * Finds the table with the specified name, using case insensitive matching.
     * Note that this method is not called getTable) to avoid introspection
     * problems.
     * 
     * @param name          The name of the table to find
     * @param caseSensitive Whether case matters for the names
     * @return The table or <code>null</code> if there is no such table
     */
    public Table findTable(String name, boolean caseSensitive);
    
    public String toVerboseString();
    
    //Added method
    public DataModel getDataModel ();
    
    public Table[] getEntities();
    
    public Function[] getFunctions();

    public View addView(Table table);
}
