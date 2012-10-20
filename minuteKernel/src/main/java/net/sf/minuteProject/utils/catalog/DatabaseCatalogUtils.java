package net.sf.minuteProject.utils.catalog;

import java.util.List;

import net.sf.minuteProject.loader.catalog.databasecatalog.node.Database;
import net.sf.minuteProject.loader.catalog.technologycatalog.node.Technology;

public class DatabaseCatalogUtils extends CatalogUtils{

	private static List<Database> databases;
	
	public static List<Database> getPublishedDatabases(String catalogDir) {
		if (databases==null)
			databases = getPublishedDatabaseCatalogHolder(catalogDir).getDatabaseCatalog().getDatabases().getDatabases();
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
