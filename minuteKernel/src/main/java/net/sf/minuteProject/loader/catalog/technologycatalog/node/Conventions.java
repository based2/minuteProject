package net.sf.minuteProject.loader.catalog.technologycatalog.node; //schema technology-catalog

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.catalog.technologycatalog.TechnologycatalogHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class Conventions extends AbstractConfigurationLoader{

    private String _packageName;
   private TechnologyCatalog _technologyCatalog;
   private String _name;
   private String _id;
   private List<Convention> _conventions;

   public Conventions() {
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
   
   
   public List<Convention> getConventions() {
      if (_conventions == null){
         _conventions = new ArrayList<Convention>();
      }
      return _conventions;
   }
   
   public Convention[] getConventionsArray() {
      return (Convention[])getConventions().toArray(new Convention[getConventions().size()]);
   }
      
   public void setConventions (List<Convention> _conventions) {
      this._conventions = _conventions;
   }
 
   public void setConvention (Convention _convention) {
      addConvention(_convention);
   }

   public void addConvention (Convention _convention) {
      getConventions().add(_convention);
   }
   
   public Convention getFirstConventionFromConventionByName (String name) {
      if (name==null)
         return null;
      for (Convention _convention : getConventions()) {
         if (_convention.getName().equals(name))
            return _convention;
      }
      return null;
   } 


}

