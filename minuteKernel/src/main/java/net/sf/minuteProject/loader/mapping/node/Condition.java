package net.sf.minuteProject.loader.mapping.node; //schema bean-map

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.mapping.MappingHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class Condition extends AbstractConfigurationLoader{

    private String _packageName;
   private BeanMap _beanMap;
   private String _type;
   private String _name;
   private String _alias;
   private String _variableRef;
   private String _evaluator;
   private String _conditionListRef;
   private String _id;

   public Condition() {
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
   
   
   public String getVariableRef() {
	  if (_variableRef == null)
	     _variableRef = new String();
	      return _variableRef;
   }
	
   public void setVariableRef (String _variableRef) {
      this._variableRef = _variableRef;
   }
   
   
   public String getEvaluator() {
	  if (_evaluator == null)
	     _evaluator = new String();
	      return _evaluator;
   }
	
   public void setEvaluator (String _evaluator) {
      this._evaluator = _evaluator;
   }
   
   
   public String getConditionListRef() {
	  if (_conditionListRef == null)
	     _conditionListRef = new String();
	      return _conditionListRef;
   }
	
   public void setConditionListRef (String _conditionListRef) {
      this._conditionListRef = _conditionListRef;
   }
   
   
   public String getId() {
	  if (_id == null)
	     _id = new String();
	      return _id;
   }
	
   public void setId (String _id) {
      this._id = _id;
   }
   
   

}

