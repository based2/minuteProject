package net.sf.minuteProject.loader.mapping.node; //schema bean-map

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.mapping.MappingHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class BeanMappingProperty extends AbstractConfigurationLoader{

    private String _packageName;
   private BeanMap _beanMap;
   private String _type;
   private String _name;
   private String _id;
   private String _what;
   private String _to;
   private String _function;
   private String _value;
   private String _callMapping;
   private String _isLoop;
   private DepencyCondition _depencyCondition;

   public BeanMappingProperty() {
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
   
   
   public String getId() {
	  if (_id == null)
	     _id = new String();
	      return _id;
   }
	
   public void setId (String _id) {
      this._id = _id;
   }
   
   
   public String getWhat() {
	  if (_what == null)
	     _what = new String();
	      return _what;
   }
	
   public void setWhat (String _what) {
      this._what = _what;
   }
   
   
   public String getTo() {
	  if (_to == null)
	     _to = new String();
	      return _to;
   }
	
   public void setTo (String _to) {
      this._to = _to;
   }
   
   
   public String getFunction() {
	  if (_function == null)
	     _function = new String();
	      return _function;
   }
	
   public void setFunction (String _function) {
      this._function = _function;
   }
   
   
   public String getValue() {
	  if (_value == null)
	     _value = new String();
	      return _value;
   }
	
   public void setValue (String _value) {
      this._value = _value;
   }
   
   
   public String getCallMapping() {
	  if (_callMapping == null)
	     _callMapping = new String();
	      return _callMapping;
   }
	
   public void setCallMapping (String _callMapping) {
      this._callMapping = _callMapping;
   }
   
   
   public String getIsLoop() {
	  if (_isLoop == null)
	     _isLoop = new String();
	      return _isLoop;
   }
	
   public void setIsLoop (String _isLoop) {
      this._isLoop = _isLoop;
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

