package net.sf.minuteProject.loader.mapping.node; //schema bean-map

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.mapping.MappingHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class BeanMap extends AbstractConfigurationLoader{

    private String _packageName;
   private String _name;
   private Imports _imports;
   private String _id;
   private BeanMappings _mappings;
   private Beans _beans;
   private Validations _validations;
   private Flows _flows;
   private Services _services;

   public BeanMap() {
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
   
   public String getName() {
	  if (_name == null)
	     _name = new String();
	      return _name;
   }
	
   public void setName (String _name) {
      this._name = _name;
   }
   
   
   public Imports getImports() {
	  if (_imports == null)
	     _imports = new Imports();
	      return _imports;
   }
	
   public void setImports (Imports _imports) {
      this._imports = _imports;
   }
   
   
   public String getId() {
	  if (_id == null)
	     _id = new String();
	      return _id;
   }
	
   public void setId (String _id) {
      this._id = _id;
   }
   
   
   public BeanMappings getMappings() {
	  if (_mappings == null)
	     _mappings = new BeanMappings();
	      return _mappings;
   }
	
   public void setMappings (BeanMappings _mappings) {
      this._mappings = _mappings;
   }
   
   
   public Beans getBeans() {
	  if (_beans == null)
	     _beans = new Beans();
	      return _beans;
   }
	
   public void setBeans (Beans _beans) {
	  if (this._beans!=null)
		  return;
      this._beans = _beans;
   }
   
   
   public Validations getValidations() {
	  if (_validations == null)
	     _validations = new Validations();
	      return _validations;
   }
	
   public void setValidations (Validations _validations) {
      this._validations = _validations;
   }
   
   
   public Flows getFlows() {
	  if (_flows == null)
	     _flows = new Flows();
	      return _flows;
   }
	
   public void setFlows (Flows _flows) {
      this._flows = _flows;
   }
   
   
   public Services getServices() {
	  if (_services == null)
	     _services = new Services();
	      return _services;
   }
	
   public void setServices (Services _services) {
      this._services = _services;
   }
   
   

}

