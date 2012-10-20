package net.sf.minuteProject.loader.mapping.node; //schema bean-map

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.mapping.MappingHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class ValidationProperty extends AbstractConfigurationLoader{

    private String _packageName;
   private BeanMap _beanMap;
   private String _what;
   private String _maxLength;
   private String _lengthType;
   private String _mandatory;
   private String _function;
   private String _error;
   private String _format;
   private String _type;
   private String _name;
   private String _alias;
   private String _id;
   private String _validator;
   private DepencyCondition _depencyCondition;

   public ValidationProperty() {
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
	
   public String getWhat() {
	  if (_what == null)
	     _what = new String();
	      return _what;
   }
	
   public void setWhat (String _what) {
      this._what = _what;
   }
   
   
   public String getMaxLength() {
	  if (_maxLength == null)
	     _maxLength = new String();
	      return _maxLength;
   }
	
   public void setMaxLength (String _maxLength) {
      this._maxLength = _maxLength;
   }
   
   
   public String getLengthType() {
	  if (_lengthType == null)
	     _lengthType = new String();
	      return _lengthType;
   }
	
   public void setLengthType (String _lengthType) {
      this._lengthType = _lengthType;
   }
   
   
   public String getMandatory() {
	  if (_mandatory == null)
	     _mandatory = new String();
	      return _mandatory;
   }
	
   public void setMandatory (String _mandatory) {
      this._mandatory = _mandatory;
   }
   
   
   public String getFunction() {
	  if (_function == null)
	     _function = new String();
	      return _function;
   }
	
   public void setFunction (String _function) {
      this._function = _function;
   }
   
   
   public String getError() {
	  if (_error == null)
	     _error = new String();
	      return _error;
   }
	
   public void setError (String _error) {
      this._error = _error;
   }
   
   
   public String getFormat() {
	  if (_format == null)
	     _format = new String();
	      return _format;
   }
	
   public void setFormat (String _format) {
      this._format = _format;
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
   
   
   public String getValidator() {
	  if (_validator == null)
	     _validator = new String();
	      return _validator;
   }
	
   public void setValidator (String _validator) {
      this._validator = _validator;
   }
   
   
   public DepencyCondition getDepencyCondition() {
	  if (_depencyCondition == null)
	     _depencyCondition = new DepencyCondition();
	      return _depencyCondition;
   }
	
   public void setDepencyCondition (DepencyCondition _depencyCondition) {
      this._depencyCondition = _depencyCondition;
   }
   
   

}

