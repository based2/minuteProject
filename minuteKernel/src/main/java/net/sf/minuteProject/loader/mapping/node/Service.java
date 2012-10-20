package net.sf.minuteProject.loader.mapping.node; //schema bean-map

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.mapping.MappingHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class Service extends AbstractConfigurationLoader{

    private String _packageName;
   private BeanMap _beanMap;
   private Boolean _isInPackage;
   private String _type;
   private String _name;
   private String _alias;
   private String _id;
   private String _rootPackageName;
   private String _technicalPackageName;
   private String _businessPackageName;
   private Functions _functions;

   public Service() {
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
   
   
   public String getRootPackageName() {
	  if (_rootPackageName == null)
	     _rootPackageName = new String();
	      return _rootPackageName;
   }
	
   public void setRootPackageName (String _rootPackageName) {
      this._rootPackageName = _rootPackageName;
   }
   
   
   public String getTechnicalPackageName() {
	  if (_technicalPackageName == null)
	     _technicalPackageName = new String();
	      return _technicalPackageName;
   }
	
   public void setTechnicalPackageName (String _technicalPackageName) {
      this._technicalPackageName = _technicalPackageName;
   }
   
   
   public String getBusinessPackageName() {
	  if (_businessPackageName == null)
	     _businessPackageName = new String();
	      return _businessPackageName;
   }
	
   public void setBusinessPackageName (String _businessPackageName) {
      this._businessPackageName = _businessPackageName;
   }
   
   
   public Functions getFunctions() {
	  if (_functions == null)
	     _functions = new Functions();
	      return _functions;
   }
	
   public void setFunctions (Functions _functions) {
      this._functions = _functions;
   }
   
   

}

