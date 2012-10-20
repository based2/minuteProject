package net.sf.minuteProject.loader.catalog.databasecatalog.node; //schema database-catalog

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.catalog.databasecatalog.DatabasecatalogHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class PrimaryKeyPolicy extends AbstractConfigurationLoader{

    private String _packageName;
   private DatabaseCatalog _databaseCatalog;
   private String _type;
   private String _suffix;
   private String _prefix;

   public PrimaryKeyPolicy() {
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
	
   public String getType() {
	  if (_type == null)
	     _type = new String();
	      return _type;
   }
	
   public void setType (String _type) {
      this._type = _type;
   }
   
   
   public String getSuffix() {
	  if (_suffix == null)
	     _suffix = new String();
	      return _suffix;
   }
	
   public void setSuffix (String _suffix) {
      this._suffix = _suffix;
   }
   
   
   public String getPrefix() {
	  if (_prefix == null)
	     _prefix = new String();
	      return _prefix;
   }
	
   public void setPrefix (String _prefix) {
      this._prefix = _prefix;
   }
   
   

}

