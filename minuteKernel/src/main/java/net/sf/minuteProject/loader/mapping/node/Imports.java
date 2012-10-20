package net.sf.minuteProject.loader.mapping.node; //schema bean-map

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.mapping.MappingHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class Imports extends AbstractConfigurationLoader{

    private String _packageName;
   private BeanMap _beanMap;
   private String _name;
   private String _id;
   private List<Import> _mappings;

   public Imports() {
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
   
   public BeanMap getBeanMap() {
      return MappingHolder.getBeanMap(); 
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
   
   
   public List<Import> getMappings() {
      if (_mappings == null){
         _mappings = new ArrayList<Import>();
      }
      return _mappings;
   }
   
   public Import[] getMappingsArray() {
      return (Import[])getMappings().toArray(new Import[getMappings().size()]);
   }
      
   public void setMappings (List<Import> _mappings) {
      this._mappings = _mappings;
   }
 
   public void setMapping (Import _mapping) {
      addMapping(_mapping);
   }

   public void addMapping (Import _mapping) {
      getMappings().add(_mapping);
   }
   
   public Import getFirstImportFromMappingByName (String name) {
      if (name==null)
         return null;
      for (Import _mapping : getMappings()) {
         if (_mapping.getName().equals(name))
            return _mapping;
      }
      return null;
   } 


}

