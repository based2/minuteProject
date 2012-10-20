package net.sf.minuteProject.loader.mapping.node; //schema bean-map

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.mapping.MappingHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class If extends AbstractConfigurationLoader{

    private String _packageName;
   private BeanMap _beanMap;
   private String _type;
   private String _name;
   private String _alias;
   private String _id;
   private String _what;
   private String _condition;
   private String _value;
   private String _expression;
   private List<Case> _cases;

   public If() {
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
   
   
   public String getWhat() {
	  if (_what == null)
	     _what = new String();
	      return _what;
   }
	
   public void setWhat (String _what) {
      this._what = _what;
   }
   
   
   public String getCondition() {
	  if (_condition == null)
	     _condition = new String();
	      return _condition;
   }
	
   public void setCondition (String _condition) {
      this._condition = _condition;
   }
   
   
   public String getValue() {
	  if (_value == null)
	     _value = new String();
	      return _value;
   }
	
   public void setValue (String _value) {
      this._value = _value;
   }
   
   
   public String getExpression() {
	  if (_expression == null)
	     _expression = new String();
	      return _expression;
   }
	
   public void setExpression (String _expression) {
      this._expression = _expression;
   }
   
   
   public List<Case> getCases() {
      if (_cases == null){
         _cases = new ArrayList<Case>();
      }
      return _cases;
   }
   
   public Case[] getCasesArray() {
      return (Case[])getCases().toArray(new Case[getCases().size()]);
   }
      
   public void setCases (List<Case> _cases) {
      this._cases = _cases;
   }
 
   public void setCase (Case _case) {
      addCase(_case);
   }

   public void addCase (Case _case) {
      getCases().add(_case);
   }
   
   public Case getFirstCaseFromCaseByName (String name) {
      if (name==null)
         return null;
      for (Case _case : getCases()) {
         if (_case.getName().equals(name))
            return _case;
      }
      return null;
   } 


}

