package net.sf.minuteProject.loader.mapping.node; //schema bean-map

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.mapping.MappingHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class BeanMappings extends AbstractConfigurationLoader{

    private String _packageName;
   private BeanMap _beanMap;
   private String _name;
   private String _id;
   private List<BeanMapping> _mappings;

   public BeanMappings() {
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
   
   
   public List<BeanMapping> getMappings() {
      if (_mappings == null){
         _mappings = new ArrayList<BeanMapping>();
      }
      return _mappings;
   }
   
   public BeanMapping[] getMappingsArray() {
      return (BeanMapping[])getMappings().toArray(new BeanMapping[getMappings().size()]);
   }
      
   public void setMappings (List<BeanMapping> _mappings) {
      this._mappings = _mappings;
   }
 
   public void setMapping (BeanMapping _mapping) {
      addMapping(_mapping);
   }

   public void addMapping (BeanMapping _mapping) {
      getMappings().add(_mapping);
   }
   
   public BeanMapping getFirstBeanMappingFromMappingByName (String name) {
      if (name==null)
         return null;
      for (BeanMapping _mapping : getMappings()) {
         if (_mapping.getName().equals(name))
            return _mapping;
      }
      return null;
   } 


}

