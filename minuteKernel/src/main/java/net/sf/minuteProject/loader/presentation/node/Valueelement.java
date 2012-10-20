package net.sf.minuteProject.loader.presentation.node; //schema dictionary

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.presentation.PresentationHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.Template;

public class Valueelement extends AbstractConfiguration{

   private Dictionary _dictionary;
   private String _name;
   private String _value;

   public Valueelement() {
   }

   public String getTechnicalPackage(Template template) {
      return template.getTechnicalPackage();
   }
   
   public Dictionary getDictionary() {
      return PresentationHolder.getDictionary(); 
   }
	
   public String getName() {
      return _name;
   }
	
   public void setName (String _name) {
      this._name = _name;
   }
   
   
   public String getValue() {
      return _value;
   }
	
   public void setValue (String _value) {
      this._value = _value;
   }
   
   

}

