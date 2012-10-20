package net.sf.minuteProject.loader.mapping.node; //schema bean-map

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.mapping.MappingHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class Function extends AbstractConfigurationLoader{

    private String _packageName;
   private BeanMap _beanMap;
   private String _type;
   private String _name;
   private String _alias;
   private String _id;
   private String _comment;
   private String _return;
   private String _returnVariableName;
   private String _returnIfError;
   private Boolean _isExposed;
   private Boolean _isReturnPartOfInput;
   private Boolean _isReturnWrappedInHolder;
   private Boolean _isCollectionReturned;
   private Boolean _isLastCallReturned;
   private String _holder;
   private String _input;
   private Transaction _transaction;
   private Calls _calls;
   private Variables _variables;
   private Conditions _conditions;

   public Function() {
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
   
   
   public String getComment() {
	  if (_comment == null)
	     _comment = new String();
	      return _comment;
   }
	
   public void setComment (String _comment) {
      this._comment = _comment;
   }
   
   
   public String getReturn() {
	  if (_return == null)
	     _return = new String();
	      return _return;
   }
	
   public void setReturn (String _return) {
      this._return = _return;
   }
   
   
   public String getReturnVariableName() {
	  if (_returnVariableName == null)
	     _returnVariableName = new String();
	      return _returnVariableName;
   }
	
   public void setReturnVariableName (String _returnVariableName) {
      this._returnVariableName = _returnVariableName;
   }
   
   
   public String getReturnIfError() {
	  if (_returnIfError == null)
	     _returnIfError = new String();
	      return _returnIfError;
   }
	
   public void setReturnIfError (String _returnIfError) {
      this._returnIfError = _returnIfError;
   }
   
   
   public Boolean isExposed() {
      return getIsExposed();
   }
   
   public Boolean getIsExposed() {
	  if (_isExposed == null)
	     _isExposed = new Boolean(true);
	      return _isExposed;
   }
	
   public void setIsExposed (Boolean _isExposed) {
      this._isExposed = _isExposed;
   }
   
   
   public Boolean isReturnPartOfInput() {
      return getIsReturnPartOfInput();
   }
   
   public Boolean getIsReturnPartOfInput() {
	  if (_isReturnPartOfInput == null)
	     _isReturnPartOfInput = new Boolean(true);
	      return _isReturnPartOfInput;
   }
	
   public void setIsReturnPartOfInput (Boolean _isReturnPartOfInput) {
      this._isReturnPartOfInput = _isReturnPartOfInput;
   }
   
   
   public Boolean isReturnWrappedInHolder() {
      return getIsReturnWrappedInHolder();
   }
   
   public Boolean getIsReturnWrappedInHolder() {
	  if (_isReturnWrappedInHolder == null)
	     _isReturnWrappedInHolder = new Boolean(false);
	      return _isReturnWrappedInHolder;
   }
	
   public void setIsReturnWrappedInHolder (Boolean _isReturnWrappedInHolder) {
      this._isReturnWrappedInHolder = _isReturnWrappedInHolder;
   }
   
   
   public Boolean isCollectionReturned() {
      return getIsCollectionReturned();
   }
   
   public Boolean getIsCollectionReturned() {
	  if (_isCollectionReturned == null)
	     _isCollectionReturned = new Boolean(false);
	      return _isCollectionReturned;
   }
	
   public void setIsCollectionReturned (Boolean _isCollectionReturned) {
      this._isCollectionReturned = _isCollectionReturned;
   }
   
   
   public Boolean isLastCallReturned() {
      return getIsLastCallReturned();
   }
   
   public Boolean getIsLastCallReturned() {
	  if (_isLastCallReturned == null)
	     _isLastCallReturned = new Boolean(true);
	      return _isLastCallReturned;
   }
	
   public void setIsLastCallReturned (Boolean _isLastCallReturned) {
      this._isLastCallReturned = _isLastCallReturned;
   }
   
   
   public String getHolder() {
	  if (_holder == null)
	     _holder = new String();
	      return _holder;
   }
	
   public void setHolder (String _holder) {
      this._holder = _holder;
   }
   
   
   public String getInput() {
	  if (_input == null)
	     _input = new String();
	      return _input;
   }
	
   public void setInput (String _input) {
      this._input = _input;
   }
   
   
   public Transaction getTransaction() {
	  if (_transaction == null)
	     _transaction = new Transaction();
	      return _transaction;
   }
	
   public void setTransaction (Transaction _transaction) {
      this._transaction = _transaction;
   }
   
   
   public Calls getCalls() {
	  if (_calls == null)
	     _calls = new Calls();
	      return _calls;
   }
	
   public void setCalls (Calls _calls) {
      this._calls = _calls;
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
   
   

}

