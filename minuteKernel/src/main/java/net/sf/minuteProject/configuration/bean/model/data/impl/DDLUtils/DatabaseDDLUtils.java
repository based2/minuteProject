package net.sf.minuteProject.configuration.bean.model.data.impl.DDLUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.minuteProject.configuration.bean.DataModel;
import net.sf.minuteProject.configuration.bean.FileSource;
import net.sf.minuteProject.configuration.bean.Model;
import net.sf.minuteProject.configuration.bean.model.data.Database;
import net.sf.minuteProject.configuration.bean.model.data.Function;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.model.data.View;
import net.sf.minuteProject.utils.FunctionUtils;

import org.apache.ddlutils.Platform;
import org.apache.ddlutils.PlatformFactory;
import org.apache.ddlutils.io.DatabaseIO;
import org.apache.ddlutils.platform.cloudscape.CloudscapePlatform;
import org.apache.ddlutils.platform.db2.Db2Platform;
import org.apache.ddlutils.platform.hsqldb.HsqlDbPlatform;
import org.apache.ddlutils.platform.mssql.MSSqlPlatform;
import org.apache.ddlutils.platform.mysql.MySqlPlatform;
import org.apache.ddlutils.platform.oracle.Oracle8Platform;
import org.apache.ddlutils.platform.postgresql.PostgreSqlPlatform;
import org.apache.ddlutils.platform.sapdb.SapDbPlatform;
import org.apache.ddlutils.platform.sqlite.Sqlite3Platform;
import org.apache.ddlutils.platform.sybase.SybasePlatform;
import org.apache.log4j.Logger;

/**
 * Inspired by DDLUtils Database class
 * Represents the database model, ie. the tables in the database. It also
 * contains the corresponding dyna classes for creating dyna beans for the
 * objects stored in the tables.
 *
 * @author Florian Adler
 */
public class DatabaseDDLUtils implements Database
{
	private static Logger logger = Logger.getLogger(DatabaseDDLUtils.class);

	private org.apache.ddlutils.model.Database database;
	private String type;
	private List<Table> tables;
	private List<View> views;
	private DataModel dataModel;
	private List<Table> entities;
	private List<Function> functions;
	
	public DatabaseDDLUtils(org.apache.ddlutils.model.Database database) {
		this.database = database;
	}

	public DatabaseDDLUtils(DataModel dataModel) {
		this.dataModel = dataModel;
		loadDatabase(dataModel);
		if (dataModel.getModel().hasFunctionModel()){
			loadFunction(dataModel);
		}
	}
	
	private void loadFunction (DataModel dataModel) {
		functions = FunctionUtils.getFunctions(dataModel, this);
	}
	
	public Database loadDatabase(DataModel dataModel) {
		/*if (isDatabaseOnFile(dataModel))
			database = new DatabaseIO().read(getFileSourceName(dataModel));		
		else 
		{*/				
	    Platform platform = PlatformFactory.createNewPlatformInstance(dataModel.getBasicDataSource());
	    platform.getModelReader().setDefaultSchemaPattern(dataModel.getSchema());
	    setType(platform);
	    database = platform.readModelFromDatabase("TEST"); 
	    writeDatabase(database, dataModel);
		//}
	    return this;
	}
	
	private boolean isDatabaseOnFile(DataModel dataModel) {
	    String filename = getFileSourceName(dataModel);
	    if (filename!= null) {
	    	File file = new File (filename);
	    	if (file.exists())
	    		return true;
	    }
	    return false;
	}
	
	private void writeDatabase (org.apache.ddlutils.model.Database database, DataModel dataModel) {
	    String filename = getFileSourceName(dataModel);
	    if (filename!= null)
	    	new DatabaseIO().write(database, filename);
	}
	
	private String getFileSourceName(DataModel dataModel) {
		FileSource fileSource = dataModel.getFileSource();
		Model model = dataModel.getModel();
	    String filename = null;
	    String filedir = null;
	    String version = dataModel.getModel().getVersion();
	    String defaultName = model.getName();
	    if (version==null)
	    	defaultName = defaultName+"-"+version;
	    if (fileSource!=null) {
	    	filename = fileSource.getName();
	    	filedir = fileSource.getDir();
	    	new File (filedir.toString()).mkdirs();
	    	if (filename!=null)
	    		filename = filedir+"/"+filename+".xml";
	    	else {
	    		filename = filedir+"/"+defaultName+".xml";
	    	}
	    }
	    return filename;
	}	
	
	
	
    /**
     * Returns the name of this database model.
     * 
     * @return The name
     */
    public String getName()
    {
        return database.getName();
    }

    /**
     * Sets the name of this database model.
     * 
     * @param name The name
     */
    public void setName(String name)
    {
    	database.setName(name);
    }

    /**
     * Returns the version of this database model.
     * 
     * @return The version
     */
    public String getVersion()
    {
        return database.getVersion();
    }

    /**
     * Sets the version of this database model.
     * 
     * @param version The version
     */
    public void setVersion(String version)
    {
    	database.setVersion(version);
    }

    /**
     * Returns the method for generating primary key values.
     * 
     * @return The method
     */
    public String getIdMethod()
    {
        return database.getIdMethod();
    }

    /**
     * Sets the method for generating primary key values. Note that this
     * value is ignored by DdlUtils and only for compatibility with Torque.
     * 
     * @param idMethod The method
     */
    public void setIdMethod(String idMethod)
    {
    	database.setIdMethod(idMethod);
    }

    /**
     * Returns the number of tables in this model.
     * 
     * @return The number of tables
     
    public int getTableCount()
    {
        return database.getTableCount();
    }
*/
    /**
     * Returns the tables in this model.
     * 
     * @return The tables
     */
    public Table[] getTables()
    {
    	if (tables == null) {
    		tables = new ArrayList<Table>();
    		//for (int i = 0; i < database.getTableCount(); i++) {
    		/*for (int i = 0; i < getTableCount(); i++) {
    			Table table = new TableDDLUtils (database.getTable(i));
    			tables.add(table);
    		}*/
    		org.apache.ddlutils.model.Table tablez[] = database.getTables();
    		for (int i = 0; i < tablez.length; i++) {
    			if (tablez[i].getType().equals("TABLE") || 
    				 tablez[i].getType().equals("ALIAS") 
    					) {
        			Table table = new TableDDLUtils (tablez[i]);
        			tables.add(table);
    			}
    		}
    	}
    	return (Table[])tables.toArray(new Table[tables.size()]);
    }

    /**
     * Returns the table at the specified position.
     * 
     * @param idx The index of the table
     * @return The table
     */
    public Table getTable(int idx)
    {
        return (Table)tables.get(idx);
    }

    /**
     * Adds a table.
     * 
     * @param table The table to add
     */
    public void addTable(Table table)
    {
        if (table != null)
        {
            tables.add(table);
        }
    }

    /**
     * Adds a table at the specified position.
     * 
     * @param idx   The index where to insert the table
     * @param table The table to add
     */
    public void addTable(int idx, Table table)
    {
        if (table != null)
        {
            tables.add(idx, table);
        }
    }

    /**
     * Adds the given tables.
     * 
     * @param tables The tables to add
     */
    public void addTables(Collection tables)
    {
    	database.addTables(tables);
    }

    /**
     * Removes the given table.
     * 
     * @param table The table to remove
     */
    public void removeTable(Table table)
    {
        if (table != null)
        {
            tables.remove(table);
        }
    }

    /**
     * Removes the indicated table.
     * 
     * @param idx The index of the table to remove
     */
    public void removeTable(int idx)
    {
        tables.remove(idx);
    }

    /**
     * Finds the table with the specified name, using case insensitive matching.
     * Note that this method is not called getTable to avoid introspection
     * problems.
     * 
     * @param name The name of the table to find
     * @return The table or <code>null</code> if there is no such table
     */
    public Table findTable(String name)
    {
        return findTable(name, false);
    }

    /**
     * Finds the table with the specified name, using case insensitive matching.
     * Note that this method is not called getTable) to avoid introspection
     * problems.
     * 
     * @param name          The name of the table to find
     * @param caseSensitive Whether case matters for the names
     * @return The table or <code>null</code> if there is no such table
     */
    public Table findTable(String name, boolean caseSensitive)
    {
    	return new TableDDLUtils (database.findTable(name, caseSensitive));
    }


    /**
     * {@inheritDoc}
     
    public Object clone() throws CloneNotSupportedException
    {
    	return database.clone();
    }*/

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object obj)
    {
    	return database.equals(obj);
    }

    /**
     * {@inheritDoc}
     */
    public int hashCode()
    {
        return database.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    public String toString()
    {
    	return database.toString();
    }

    /**
     * Returns a verbose string representation of this database.
     * 
     * @return The string representation
     */
    public String toVerboseString()
    {
    	return database.toVerboseString();
    }

	public DataModel getDataModel() {
		return dataModel;
	}

	/**
	 * get the type (Oracle, DB2, Sybase, Mysql ...) of the database
	 * @return String
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * get the type (Oracle, DB2, Sybase, Mysql ...) of the database
	 * @return String
	 */
	private void setType(String type) {
		this.type = type;
	}
	
	private void setType (Platform platform) {
		if (platform instanceof Db2Platform) 
			setType("DB2");
		else if (platform instanceof Oracle8Platform)
			setType("ORACLE");
		else if (platform instanceof MySqlPlatform)
			setType("MYSQL");
		else if (platform instanceof SybasePlatform)
			setType("SYBASE");
		else if (platform instanceof PostgreSqlPlatform)
			setType("POSTGRESQL");
		else if (platform instanceof MSSqlPlatform)
			setType("MSSQL");
		else if (platform instanceof HsqlDbPlatform)
			setType("HSQLDB");		
		else if (platform instanceof SapDbPlatform)
			setType("SAPDB");	
		else if (platform instanceof CloudscapePlatform)
			setType("CLOUDSCAPE");		
		else if (platform instanceof Sqlite3Platform)
			setType("SQLITE");			
	}

	public void addView(View view) {
    	if (views == null) {
    		views = new ArrayList<View>();
    	}
		views.add(view);
	}

	public View[] getViews() {
    	if (views == null) {
    		views = new ArrayList<View>();
    		org.apache.ddlutils.model.Table tablez[] = database.getTables();
    		for (int i = 0; i < tablez.length; i++) {
    			if (tablez[i].getType().equals("VIEW")) {
        			View view = new ViewDDLUtils (tablez[i]);
        			addView(view);
    			}
    		}
    	}
    	return (View[])views.toArray(new View[views.size()]);
	}
	
    /**
     * Returns the number of tables in this model.
     * 
     * @return The number of tables
     */
	public int getTableCount() {
		org.apache.ddlutils.model.Table tables[] = database.getTables();
		int j=0;
		for (int i = 0; i < tables.length; i++) {
			if (tables[i].getType().equals("TABLE"))
				j++;
		}
		return j;
	}
	
	public int getViewCount() {
		org.apache.ddlutils.model.Table tables[] = database.getTables();
		int j=0;
		for (int i = 0; i < tables.length; i++) {
			if (tables[i].getType().equals("TABLE"))
				j++;
		}
		return j;
	}

	public Table[] getEntities() {
    	if (entities == null) {
    		entities = new ArrayList<Table>();
    		if (tables!=null)
    			entities.addAll(tables);
    		if (views!=null)
    			entities.addAll(views);
    	}
    	return (Table[])entities.toArray(new Table[entities.size()]);
	}

	public List<Function> getFunctionArray() {
		if (functions==null) functions = new ArrayList<Function>();
		return functions;
	}

	public Function[] getFunctions() {
		return (Function[]) getFunctionArray().toArray(new Function[getFunctionArray().size()]);
	}

	@Override
	public View addView(Table table) {
		View view = null;
		table.setType(Table.VIEW);
		org.apache.ddlutils.model.Table tablez[] = database.getTables();
		for (int i = 0; i < tablez.length; i++) {
			if (tablez[i].getName().equals(table.getName())) {
				tablez[i].setType(Table.VIEW);
				view = new ViewDDLUtils (tablez[i]);
				//TODO remove form tables
    			//tables.add(table);
				//addView(view);
				tables.remove(table);
				moveToView (tablez[i]);
				return view;
			}
		}
		return view;
		
	}

	private void moveToView (org.apache.ddlutils.model.Table table) {
		org.apache.ddlutils.model.Table tablez[] = database.getTables();
		for (int i = 0; i < tablez.length; i++) {
			if (tablez[i].getName().equals(table.getName())) {
				tablez[i].setType(Table.VIEW);
				return;
			}
		}	
	}
	
}

