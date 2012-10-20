package net.sf.minuteProject.loader.catalog.databasecatalog;

import net.sf.minuteProject.loader.catalog.databasecatalog.node.DatabaseCatalog;

public class DatabasecatalogHolder {

   private static DatabaseCatalog _databaseCatalog;

   public DatabasecatalogHolder() {
   }

   public static DatabaseCatalog getDatabaseCatalog() {
      return _databaseCatalog;
   }
	
   public static void setDatabaseCatalog (DatabaseCatalog _databaseCatalog2) {
      _databaseCatalog = _databaseCatalog2;
   }
   
}

