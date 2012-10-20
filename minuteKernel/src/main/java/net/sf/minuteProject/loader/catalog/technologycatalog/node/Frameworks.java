package net.sf.minuteProject.loader.catalog.technologycatalog.node; //schema technology-catalog

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.catalog.technologycatalog.TechnologycatalogHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class Frameworks extends AbstractConfigurationLoader{

    private String _packageName;
   private TechnologyCatalog _technologyCatalog;
   private String _name;
   private String _id;
   private List<Framework> _frameworks;

   public Frameworks() {
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
   
   
   public List<Framework> getFrameworks() {
      if (_frameworks == null){
         _frameworks = new ArrayList<Framework>();
      }
      return _frameworks;
   }
   
   public Framework[] getFrameworksArray() {
      return (Framework[])getFrameworks().toArray(new Framework[getFrameworks().size()]);
   }
      
   public void setFrameworks (List<Framework> _frameworks) {
      this._frameworks = _frameworks;
   }
 
   public void setFramework (Framework _framework) {
      addFramework(_framework);
   }

   public void addFramework (Framework _framework) {
      getFrameworks().add(_framework);
   }
   
   public Framework getFirstFrameworkFromFrameworkByName (String name) {
      if (name==null)
         return null;
      for (Framework _framework : getFrameworks()) {
         if (_framework.getName().equals(name))
            return _framework;
      }
      return null;
   } 


}

