package net.sf.minuteProject.loader.mapping.node; //schema bean-map

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.mapping.MappingHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class BeanMappingProperties extends AbstractConfigurationLoader{

    private String _packageName;
   private BeanMap _beanMap;
   private String _name;
   private String _id;
   private List<BeanMappingProperty> _mapPropertys;

   public BeanMappingProperties() {
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
   
   
   public List<BeanMappingProperty> getMapPropertys() {
      if (_mapPropertys == null){
         _mapPropertys = new ArrayList<BeanMappingProperty>();
      }
      return _mapPropertys;
   }
   
   public BeanMappingProperty[] getMapPropertysArray() {
      return (BeanMappingProperty[])getMapPropertys().toArray(new BeanMappingProperty[getMapPropertys().size()]);
   }
      
   public void setMapPropertys (List<BeanMappingProperty> _mapPropertys) {
      this._mapPropertys = _mapPropertys;
   }
 
   public void setMapProperty (BeanMappingProperty _mapProperty) {
      addMapProperty(_mapProperty);
   }

   public void addMapProperty (BeanMappingProperty _mapProperty) {
      getMapPropertys().add(_mapProperty);
   }
   
   public BeanMappingProperty getFirstBeanMappingPropertyFromMapPropertyByName (String name) {
      if (name==null)
         return null;
      for (BeanMappingProperty _mapProperty : getMapPropertys()) {
         if (_mapProperty.getName().equals(name))
            return _mapProperty;
      }
      return null;
   } 


}

