package net.sf.minuteProject.loader.catalog.databasecatalog.node; //schema database-catalog

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.catalog.databasecatalog.DatabasecatalogHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class Databases extends AbstractConfigurationLoader{

    private String _packageName;
   private DatabaseCatalog _databaseCatalog;
   private String _name;
   private String _id;
   private List<Database> _databases;

   public Databases() {
   }

   public String getTechnicalPackage(Template template) {
      return getPackageName();
   }

   public String getPackageName() {
      if (_packageName == null)
         _packageName = new String();
      return _packageName;
   }

   public void setPackageName(String _packageName) {
      this._packageName = _packageName;
   }
   
   public DatabaseCatalog getDatabaseCatalog() {
      return DatabasecatalogHolder.getDatabaseCatalog(); 
   }
	
   public String getName() {
	  if (_name == null)
	     _name = new String();
	      return _name;
   }
	
   public void setName (String _name) {
      this._name = _name;
   }
   
   
   public String getId() {
	  if (_id == null)
	     _id = new String();
	      return _id;
   }
	
   public void setId (String _id) {
      this._id = _id;
   }
   
   
   public List<Database> getDatabases() {
      if (_databases == null){
         _databases = new ArrayList<Database>();
      }
      return _databases;
   }
   
   public Database[] getDatabasesArray() {
      return (Database[])getDatabases().toArray(new Database[getDatabases().size()]);
   }
      
   public void setDatabases (List<Database> _databases) {
      this._databases = _databases;
   }
 
   public void setDatabase (Database _database) {
      addDatabase(_database);
   }

   public void addDatabase (Database _database) {
      getDatabases().add(_database);
   }
   
   public Database getFirstDatabaseFromDatabaseByName (String name) {
      if (name==null)
         return null;
      for (Database _database : getDatabases()) {
         if (_database.getName().equals(name))
            return _database;
      }
      return null;
   } 


}

