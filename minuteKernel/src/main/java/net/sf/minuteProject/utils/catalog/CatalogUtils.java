package net.sf.minuteProject.utils.catalog;

import net.sf.minuteProject.loader.catalog.databasecatalog.Databasecatalog;
import net.sf.minuteProject.loader.catalog.databasecatalog.DatabasecatalogHolder;
import net.sf.minuteProject.loader.catalog.technologycatalog.Technologycatalog;
import net.sf.minuteProject.loader.catalog.technologycatalog.TechnologycatalogHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatalogUtils {

    private static Logger logger = LoggerFactory.getLogger(CatalogUtils.class);

	private static TechnologycatalogHolder technologycatalogHolder;
	private static DatabasecatalogHolder databasecatalogHolder;

	public static void resetTechnologycatalogHolder () {
		technologycatalogHolder = null;
	}
	
	public static void resetDatabasecatalogHolder () {
		databasecatalogHolder = null;
	}
	
	public static TechnologycatalogHolder getPublishedTechnologyCatalogHolder(String catalogDir) {
		if (catalogDir==null)
			catalogDir=getDefaultCatalogDir();		
		if (technologycatalogHolder==null)
			technologycatalogHolder = loadTechnologyCatalogHolder(catalogDir+"/technology-catalog.xml");
		return technologycatalogHolder;
	}
	
	public static TechnologycatalogHolder loadTechnologyCatalogHolder(String name) {
		try {
			return getTechnologyCatalogLoader(name).load();
		} catch (Exception e) {
            logger.info("Failed to load with path:" + name + ", retrying...", e);
			return new TechnologycatalogHolder();
		}
	}

	private static Technologycatalog getTechnologyCatalogLoader(String name) {
		return new Technologycatalog(name);
	}
	
	public static DatabasecatalogHolder getPublishedDatabaseCatalogHolder(String catalogDir) {
		if (catalogDir==null)
			catalogDir=getDefaultCatalogDir();
		if (databasecatalogHolder==null)
			databasecatalogHolder = loadDatabaseCatalogHolder(catalogDir+"/database-catalog.xml");
		return databasecatalogHolder;
	}
	
	public static DatabasecatalogHolder loadDatabaseCatalogHolder(String name) {
		try {
			return getDatabaseCatalogLoader(name).load();
		} catch (Exception e) {
			logger.error("Failed to load with path:" + name + ", retrying...", e);
			return new DatabasecatalogHolder();
		}
	}

	private static Databasecatalog getDatabaseCatalogLoader(String name) {
		return new Databasecatalog(name);
	}

	public static String getDefaultCatalogDir () {
		return "catalog";
	}

}
