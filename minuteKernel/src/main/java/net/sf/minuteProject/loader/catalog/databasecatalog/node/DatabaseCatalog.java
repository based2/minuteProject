package net.sf.minuteProject.loader.catalog.databasecatalog.node; //schema database-catalog

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.catalog.databasecatalog.DatabasecatalogHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class DatabaseCatalog extends AbstractConfigurationLoader{

    private String _packageName;
   private String _name;
   private Databases _databases;

   public DatabaseCatalog() {
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
   
   public String getName() {
	  if (_name == null)
	     _name = new String();
	      return _name;
   }
	
   public void setName (String _name) {
      this._name = _name;
   }
   
   
   public Databases getDatabases() {
	  if (_databases == null)
	     _databases = new Databases();
	      return _databases;
   }
	
   public void setDatabases (Databases _databases) {
      this._databases = _databases;
   }
   
   

}

