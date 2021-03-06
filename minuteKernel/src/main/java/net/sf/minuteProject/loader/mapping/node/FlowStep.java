package net.sf.minuteProject.loader.mapping.node; //schema bean-map

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.mapping.MappingHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class FlowStep extends AbstractConfigurationLoader{

    private String _packageName;
   private BeanMap _beanMap;
   private String _type;
   private String _name;
   private String _alias;
   private String _id;
   private String _technicalPackage;
   private String _businessPackage;
   private Calls _calls;

   public FlowStep() {
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
   
   
   public String getTechnicalPackage() {
	  if (_technicalPackage == null)
	     _technicalPackage = new String();
	      return _technicalPackage;
   }
	
   public void setTechnicalPackage (String _technicalPackage) {
      this._technicalPackage = _technicalPackage;
   }
   
   
   public String getBusinessPackage() {
	  if (_businessPackage == null)
	     _businessPackage = new String();
	      return _businessPackage;
   }
	
   public void setBusinessPackage (String _businessPackage) {
      this._businessPackage = _businessPackage;
   }
   
   
   public Calls getCalls() {
	  if (_calls == null)
	     _calls = new Calls();
	      return _calls;
   }
	
   public void setCalls (Calls _calls) {
      this._calls = _calls;
   }
   
   

}

