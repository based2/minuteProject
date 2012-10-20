package net.sf.minuteProject.loader.catalog.technologycatalog.node; //schema technology-catalog

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.catalog.technologycatalog.TechnologycatalogHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class Technologies extends AbstractConfigurationLoader{

    private String _packageName;
   private TechnologyCatalog _technologyCatalog;
   private String _name;
   private String _id;
   private List<Technology> _technologys;

   public Technologies() {
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
   
   public TechnologyCatalog getTechnologyCatalog() {
      return TechnologycatalogHolder.getTechnologyCatalog(); 
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
   
   
   public List<Technology> getTechnologys() {
      if (_technologys == null){
         _technologys = new ArrayList<Technology>();
      }
      return _technologys;
   }
   
   public Technology[] getTechnologysArray() {
      return (Technology[])getTechnologys().toArray(new Technology[getTechnologys().size()]);
   }
      
   public void setTechnologys (List<Technology> _technologys) {
      this._technologys = _technologys;
   }
 
   public void setTechnology (Technology _technology) {
      addTechnology(_technology);
   }

   public void addTechnology (Technology _technology) {
      getTechnologys().add(_technology);
   }
   
   public Technology getFirstTechnologyFromTechnologyByName (String name) {
      if (name==null)
         return null;
      for (Technology _technology : getTechnologys()) {
         if (_technology.getName().equals(name))
            return _technology;
      }
      return null;
   } 


}

