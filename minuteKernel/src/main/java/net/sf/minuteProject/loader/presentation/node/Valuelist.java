package net.sf.minuteProject.loader.presentation.node; //schema dictionary

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.presentation.PresentationHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.Template;

public class Valuelist extends AbstractConfiguration{

   private Dictionary _dictionary;
   private String _type;
   private String _name;
   private String _id;
   private List<Valueelement> _valueElements;

   public Valuelist() {
   }

   public String getTechnicalPackage(Template template) {
      return template.getTechnicalPackage();
   }
   
   public Dictionary getDictionary() {
      return PresentationHolder.getDictionary(); 
   }
	
   public String getType() {
      return _type;
   }
	
   public void setType (String _type) {
      this._type = _type;
   }
   
   
   public String getName() {
      return _name;
   }
	
   public void setName (String _name) {
      this._name = _name;
   }
   
   
   public String getId() {
      return _id;
   }
	
   public void setId (String _id) {
      this._id = _id;
   }
   
   
   public List<Valueelement> getValueelements() {
      if (_valueElements == null){
         _valueElements = new ArrayList<Valueelement>();
      }
      return _valueElements;
   }
   
   public Valueelement[] getValueelementsArray() {
      return (Valueelement[])getValueelements().toArray(new Valueelement[getValueelements().size()]);
   }
      
   public void setValueelements (List<Valueelement> _valueElements) {
      this._valueElements = _valueElements;
   }
 
   public void setValueelement (Valueelement _valueElement) {
      addValueelement(_valueElement);
   }

   public void addValueelement (Valueelement _valueElement) {
      getValueelements().add(_valueElement);
   }


}

