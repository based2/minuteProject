package net.sf.minuteProject.loader.catalog.technologycatalog.node; //schema technology-catalog

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.catalog.technologycatalog.TechnologycatalogHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class Limitations extends AbstractConfigurationLoader{

    private String _packageName;
   private TechnologyCatalog _technologyCatalog;
   private String _name;
   private String _id;
   private List<Limitation> _limitations;

   public Limitations() {
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
   
   
   public List<Limitation> getLimitations() {
      if (_limitations == null){
         _limitations = new ArrayList<Limitation>();
      }
      return _limitations;
   }
   
   public Limitation[] getLimitationsArray() {
      return (Limitation[])getLimitations().toArray(new Limitation[getLimitations().size()]);
   }
      
   public void setLimitations (List<Limitation> _limitations) {
      this._limitations = _limitations;
   }
 
   public void setLimitation (Limitation _limitation) {
      addLimitation(_limitation);
   }

   public void addLimitation (Limitation _limitation) {
      getLimitations().add(_limitation);
   }
   
   public Limitation getFirstLimitationFromLimitationByName (String name) {
      if (name==null)
         return null;
      for (Limitation _limitation : getLimitations()) {
         if (_limitation.getName().equals(name))
            return _limitation;
      }
      return null;
   } 


}

