package net.sf.minuteProject.loader.mapping.node; //schema bean-map

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.mapping.MappingHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class Validations extends AbstractConfigurationLoader{

    private String _packageName;
   private BeanMap _beanMap;
   private String _type;
   private String _name;
   private String _alias;
   private String _id;
   private List<Validation> _validations;

   public Validations() {
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
   
   
   public List<Validation> getValidations() {
      if (_validations == null){
         _validations = new ArrayList<Validation>();
      }
      return _validations;
   }
   
   public Validation[] getValidationsArray() {
      return (Validation[])getValidations().toArray(new Validation[getValidations().size()]);
   }
      
   public void setValidations (List<Validation> _validations) {
      this._validations = _validations;
   }
 
   public void setValidation (Validation _validation) {
      addValidation(_validation);
   }

   public void addValidation (Validation _validation) {
      getValidations().add(_validation);
   }
   
   public Validation getFirstValidationFromValidationByName (String name) {
      if (name==null)
         return null;
      for (Validation _validation : getValidations()) {
         if (_validation.getName().equals(name))
            return _validation;
      }
      return null;
   } 


}

