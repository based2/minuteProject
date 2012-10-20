package net.sf.minuteProject.loader.mapping.node; //schema bean-map

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.mapping.MappingHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class ValidationPropertySets extends AbstractConfigurationLoader{

    private String _packageName;
   private BeanMap _beanMap;
   private String _type;
   private String _name;
   private String _alias;
   private String _id;
   private List<ValidationPropertySet> _validationPropertySets;

   public ValidationPropertySets() {
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
	
   public String getType() {
	  if (_type == null)
	     _type = new String();
	      return _type;
   }
	
   public void setType (String _type) {
      this._type = _type;
   }
   
   
   public String getName() {
	  if (_name == null)
	     _name = new String();
	      return _name;
   }
	
   public void setName (String _name) {
      this._name = _name;
   }
   
   
   public String getAlias() {
	  if (_alias == null)
	     _alias = new String();
	      return _alias;
   }
	
   public void setAlias (String _alias) {
      this._alias = _alias;
   }
   
   
   public String getId() {
	  if (_id == null)
	     _id = new String();
	      return _id;
   }
	
   public void setId (String _id) {
      this._id = _id;
   }
   
   
   public List<ValidationPropertySet> getValidationPropertySets() {
      if (_validationPropertySets == null){
         _validationPropertySets = new ArrayList<ValidationPropertySet>();
      }
      return _validationPropertySets;
   }
   
   public ValidationPropertySet[] getValidationPropertySetsArray() {
      return (ValidationPropertySet[])getValidationPropertySets().toArray(new ValidationPropertySet[getValidationPropertySets().size()]);
   }
      
   public void setValidationPropertySets (List<ValidationPropertySet> _validationPropertySets) {
      this._validationPropertySets = _validationPropertySets;
   }
 
   public void setValidationPropertySet (ValidationPropertySet _validationPropertySet) {
      addValidationPropertySet(_validationPropertySet);
   }

   public void addValidationPropertySet (ValidationPropertySet _validationPropertySet) {
      getValidationPropertySets().add(_validationPropertySet);
   }
   
   public ValidationPropertySet getFirstValidationPropertySetFromValidationPropertySetByName (String name) {
      if (name==null)
         return null;
      for (ValidationPropertySet _validationPropertySet : getValidationPropertySets()) {
         if (_validationPropertySet.getName().equals(name))
            return _validationPropertySet;
      }
      return null;
   } 


}

