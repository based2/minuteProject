package net.sf.minuteProject.loader.mapping.node; //schema bean-map

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.mapping.MappingHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class Case extends AbstractConfigurationLoader{

    private String _packageName;
   private BeanMap _beanMap;
   private String _value;
   private ValidationProperties _validationProperties;

   public Case() {
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
	
   public String getValue() {
	  if (_value == null)
	     _value = new String();
	      return _value;
   }
	
   public void setValue (String _value) {
      this._value = _value;
   }
   
   
   public ValidationProperties getValidationProperties() {
	  if (_validationProperties == null)
	     _validationProperties = new ValidationProperties();
	      return _validationProperties;
   }
	
   public void setValidationProperties (ValidationProperties _validationProperties) {
      this._validationProperties = _validationProperties;
   }
   
   

}

