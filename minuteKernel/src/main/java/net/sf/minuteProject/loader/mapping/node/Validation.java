package net.sf.minuteProject.loader.mapping.node; //schema bean-map

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.mapping.MappingHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class Validation extends AbstractConfigurationLoader{

    private String _packageName;
   private BeanMap _beanMap;
   private String _type;
   private String _bean;
   private String _name;
   private String _alias;
   private String _id;
   private String _entryPoint;
   private Boolean _isInPackage;
   private Variables _variables;
   private Conditions _conditions;
   private ValidationProperties _validationProperties;
   private ValidationPropertySets _validationPropertySets;

   public Validation() {
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
   
   
   public String getBean() {
	  if (_bean == null)
	     _bean = new String();
	      return _bean;
   }
	
   public void setBean (String _bean) {
      this._bean = _bean;
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
   
   
   public String getEntryPoint() {
	  if (_entryPoint == null)
	     _entryPoint = new String();
	      return _entryPoint;
   }
	
   public void setEntryPoint (String _entryPoint) {
      this._entryPoint = _entryPoint;
   }
   
   
   public Boolean isInPackage() {
      return getIsInPackage();
   }
   
   public Boolean getIsInPackage() {
	  if (_isInPackage == null)
	     _isInPackage = new Boolean(false);
	      return _isInPackage;
   }
	
   public void setIsInPackage (Boolean _isInPackage) {
      this._isInPackage = _isInPackage;
   }
   
   
   public Variables getVariables() {
	  if (_variables == null)
	     _variables = new Variables();
	      return _variables;
   }
	
   public void setVariables (Variables _variables) {
      this._variables = _variables;
   }
   
   
   public Conditions getConditions() {
	  if (_conditions == null)
	     _conditions = new Conditions();
	      return _conditions;
   }
	
   public void setConditions (Conditions _conditions) {
      this._conditions = _conditions;
   }
   
   
   public ValidationProperties getValidationProperties() {
	  if (_validationProperties == null)
	     _validationProperties = new ValidationProperties();
	      return _validationProperties;
   }
	
   public void setValidationProperties (ValidationProperties _validationProperties) {
      this._validationProperties = _validationProperties;
   }
   
   
   public ValidationPropertySets getValidationPropertySets() {
	  if (_validationPropertySets == null)
	     _validationPropertySets = new ValidationPropertySets();
	      return _validationPropertySets;
   }
	
   public void setValidationPropertySets (ValidationPropertySets _validationPropertySets) {
      this._validationPropertySets = _validationPropertySets;
   }
   
   

}

