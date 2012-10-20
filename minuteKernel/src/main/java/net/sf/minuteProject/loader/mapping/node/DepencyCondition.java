package net.sf.minuteProject.loader.mapping.node; //schema bean-map

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.mapping.MappingHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class DepencyCondition extends AbstractConfigurationLoader{

    private String _packageName;
   private BeanMap _beanMap;
   private String _conditionRef;
   private String _isToStoreAsVariable;
   private Boolean _isSuccessfulOnTrueResult;

   public DepencyCondition() {
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
	
   public String getConditionRef() {
	  if (_conditionRef == null)
	     _conditionRef = new String();
	      return _conditionRef;
   }
	
   public void setConditionRef (String _conditionRef) {
      this._conditionRef = _conditionRef;
   }
   
   
   public String getIsToStoreAsVariable() {
	  if (_isToStoreAsVariable == null)
	     _isToStoreAsVariable = new String();
	      return _isToStoreAsVariable;
   }
	
   public void setIsToStoreAsVariable (String _isToStoreAsVariable) {
      this._isToStoreAsVariable = _isToStoreAsVariable;
   }
   
   
   public Boolean isSuccessfulOnTrueResult() {
      return getIsSuccessfulOnTrueResult();
   }
   
   public Boolean getIsSuccessfulOnTrueResult() {
	  if (_isSuccessfulOnTrueResult == null)
	     _isSuccessfulOnTrueResult = new Boolean(true);
	      return _isSuccessfulOnTrueResult;
   }
	
   public void setIsSuccessfulOnTrueResult (Boolean _isSuccessfulOnTrueResult) {
      this._isSuccessfulOnTrueResult = _isSuccessfulOnTrueResult;
   }
   
   

}

