package net.sf.minuteProject.utils.catalog;

import java.util.List;

import net.sf.minuteProject.loader.catalog.databasecatalog.node.Database;
import net.sf.minuteProject.loader.catalog.technologycatalog.node.Technology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseCatalogUtils extends CatalogUtils{

    private static Logger logger = LoggerFactory.getLogger(DatabaseCatalogUtils.class);

	private static List<Database> databases;
	
	public static List<Database> getPublishedDatabases(String catalogDir) {

		if (databases==null) {
            try {
			    databases = getPublishedDatabaseCatalogHolder(catalogDir).getDatabaseCatalog().getDatabases().getDatabases();
            } catch (NullPointerException e)  {
                 logger.error("Database Catalog file not found in "+catalogDir, e);
            }
        }
		return databases;		
	}
	
	public static Database getPublishedDatabase(String name, String catalogDir) {
		for (Database database : getPublishedDatabases (catalogDir)) {
			if (database.getName().equals(name))
				return database;
		}		
		return null;
	}	
}
