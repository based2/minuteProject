package net.sf.minuteProject.loader.mapping.node; //schema bean-map

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.mapping.MappingHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class Call extends AbstractConfigurationLoader{

    private String _packageName;
   private BeanMap _beanMap;
   private String _type;
   private String _name;
   private String _alias;
   private String _id;
   private String _service;
   private String _function;
   private Boolean _isPreviousCallReturnAsInput;
   private String _assign;
   private String _validate;
   private String _map;
   private String _input;
   private String _returnToVariable;
   private CallSequence _sequence;
   private DepencyCondition _depencyCondition;

   public Call() {
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
   
   
   public String getService() {
	  if (_service == null)
	     _service = new String();
	      return _service;
   }
	
   public void setService (String _service) {
      this._service = _service;
   }
   
   
   public String getFunction() {
	  if (_function == null)
	     _function = new String();
	      return _function;
   }
	
   public void setFunction (String _function) {
      this._function = _function;
   }
   
   
   public Boolean isPreviousCallReturnAsInput() {
      return getIsPreviousCallReturnAsInput();
   }
   
   public Boolean getIsPreviousCallReturnAsInput() {
	  if (_isPreviousCallReturnAsInput == null)
	     _isPreviousCallReturnAsInput = new Boolean(true);
	      return _isPreviousCallReturnAsInput;
   }
	
   public void setIsPreviousCallReturnAsInput (Boolean _isPreviousCallReturnAsInput) {
      this._isPreviousCallReturnAsInput = _isPreviousCallReturnAsInput;
   }
   
   
   public String getAssign() {
	  if (_assign == null)
	     _assign = new String();
	      return _assign;
   }
	
   public void setAssign (String _assign) {
      this._assign = _assign;
   }
   
   
   public String getValidate() {
	  if (_validate == null)
	     _validate = new String();
	      return _validate;
   }
	
   public void setValidate (String _validate) {
      this._validate = _validate;
   }
   
   
   public String getMap() {
	  if (_map == null)
	     _map = new String();
	      return _map;
   }
	
   public void setMap (String _map) {
      this._map = _map;
   }
   
   
   public String getInput() {
	  if (_input == null)
	     _input = new String();
	      return _input;
   }
	
   public void setInput (String _input) {
      this._input = _input;
   }
   
   
   public String getReturnToVariable() {
	  if (_returnToVariable == null)
	     _returnToVariable = new String();
	      return _returnToVariable;
   }
	
   public void setReturnToVariable (String _returnToVariable) {
      this._returnToVariable = _returnToVariable;
   }
   
   
   public CallSequence getSequence() {
	  if (_sequence == null)
	     _sequence = new CallSequence();
	      return _sequence;
   }
	
   public void setSequence (CallSequence _sequence) {
      this._sequence = _sequence;
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

