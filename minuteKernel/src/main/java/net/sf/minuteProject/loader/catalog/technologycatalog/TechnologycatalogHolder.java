package net.sf.minuteProject.loader.catalog.technologycatalog;

import net.sf.minuteProject.loader.catalog.technologycatalog.node.TechnologyCatalog;

public class TechnologycatalogHolder {

   private static TechnologyCatalog _technologyCatalog;

   public TechnologycatalogHolder() {
   }

   public static TechnologyCatalog getTechnologyCatalog() {
      return _technologyCatalog;
   }
	
   public static void setTechnologyCatalog (TechnologyCatalog _technologyCatalog2) {
      _technologyCatalog = _technologyCatalog2;
   }
   
}

