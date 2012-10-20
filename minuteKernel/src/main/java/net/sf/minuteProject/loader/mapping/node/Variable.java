package net.sf.minuteProject.loader.mapping.node; //schema bean-map

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.mapping.MappingHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class Variable extends AbstractConfigurationLoader{

    private String _packageName;
   private BeanMap _beanMap;
   private String _type;
   private String _bean;
   private String _name;
   private String _alias;
   private Boolean _isReturn;
   private String _id;
   private String _function;
   private String _functionParam;

   public Variable() {
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
   
   
   public Boolean isReturn() {
      return getIsReturn();
   }
   
   public Boolean getIsReturn() {
	  if (_isReturn == null)
	     _isReturn = new Boolean(false);
	      return _isReturn;
   }
	
   public void setIsReturn (Boolean _isReturn) {
      this._isReturn = _isReturn;
   }
   
   
   public String getId() {
	  if (_id == null)
	     _id = new String();
	      return _id;
   }
	
   public void setId (String _id) {
      this._id = _id;
   }
   
   
   public String getFunction() {
	  if (_function == null)
	     _function = new String();
	      return _function;
   }
	
   public void setFunction (String _function) {
      this._function = _function;
   }
   
   
   public String getFunctionParam() {
	  if (_functionParam == null)
	     _functionParam = new String();
	      return _functionParam;
   }
	
   public void setFunctionParam (String _functionParam) {
      this._functionParam = _functionParam;
   }
   
   

}

