package net.sf.minuteProject.loader.mapping.node; //schema bean-map

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.mapping.MappingHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class BeanMapping extends AbstractConfigurationLoader{

    private String _packageName;
   private BeanMap _beanMap;
   private String _name;
   private String _id;
   private String _entryPoint;
   private String _originBean;
   private String _targetBean;
   private Variables _variables;
   private Conditions _conditions;
   private BeanMappingProperties _mapProperties;

   public BeanMapping() {
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
   
   
   public String getEntryPoint() {
	  if (_entryPoint == null)
	     _entryPoint = new String();
	      return _entryPoint;
   }
	
   public void setEntryPoint (String _entryPoint) {
      this._entryPoint = _entryPoint;
   }
   
   
   public String getOriginBean() {
	  if (_originBean == null)
	     _originBean = new String();
	      return _originBean;
   }
	
   public void setOriginBean (String _originBean) {
      this._originBean = _originBean;
   }
   
   
   public String getTargetBean() {
	  if (_targetBean == null)
	     _targetBean = new String();
	      return _targetBean;
   }
	
   public void setTargetBean (String _targetBean) {
      this._targetBean = _targetBean;
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
   
   
   public BeanMappingProperties getMapProperties() {
	  if (_mapProperties == null)
	     _mapProperties = new BeanMappingProperties();
	      return _mapProperties;
   }
	
   public void setMapProperties (BeanMappingProperties _mapProperties) {
      this._mapProperties = _mapProperties;
   }
   
   

}

