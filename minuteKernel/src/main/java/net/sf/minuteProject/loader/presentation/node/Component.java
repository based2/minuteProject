package net.sf.minuteProject.loader.presentation.node; //schema dictionary

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.presentation.PresentationHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.Template;

public class Component extends AbstractConfiguration{

   private Dictionary _dictionary;
   private String _name;
   private String _id;
   private String _type;
   private String _length;
   private String _height;
   private String _helper;
   private String _validation;
   private Boolean _isMandatory;
   private Valuelist _valueList;
   private String _valueListRef;
   private Valuelist _refvalueList;
   private String _label;
   private String _description;
   private String _editable;
   private String _visible;
   private Boolean _isActionForm;
   private String _action;
   private String _defaultValue;
   private String _exampleValue;
   private String _positionX;
   private String _positionY;

   public Component() {
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
   
   
   public String getId() {
      return _id;
   }
	
   public void setId (String _id) {
      this._id = _id;
   }
   
   
   public String getType() {
      return _type;
   }
	
   public void setType (String _type) {
      this._type = _type;
   }
   
   
   public String getLength() {
      return _length;
   }
	
   public void setLength (String _length) {
      this._length = _length;
   }
   
   
   public String getHeight() {
      return _height;
   }
	
   public void setHeight (String _height) {
      this._height = _height;
   }
   
   
   public String getHelper() {
      return _helper;
   }
	
   public void setHelper (String _helper) {
      this._helper = _helper;
   }
   
   
   public String getValidation() {
      return _validation;
   }
	
   public void setValidation (String _validation) {
      this._validation = _validation;
   }
   
   
   public Boolean getIsmandatory() {
      return _isMandatory;
   }
	
   public void setIsmandatory (Boolean _isMandatory) {
      this._isMandatory = _isMandatory;
   }
   
   
   public Valuelist getValuelist() {
      return _valueList;
   }
	
   public void setValuelist (Valuelist _valueList) {
      this._valueList = _valueList;
   }
   
   
   public String getValuelistref() {
      return _valueListRef;
   }
	
   public void setValuelistref (String _valueListRef) {
      this._valueListRef = _valueListRef;
   }
   
   public Valuelist getValuelistByRef() {
      if (_refvalueList==null) {
         List <Valuelist> valueLists = getDictionary().getValuelists();
         for (Valuelist valueList : valueLists) {
            if (valueList.getId().equals(getValuelistref()))
               _refvalueList = valueList;
         }
      }
      return _refvalueList;
   }
   
   
   public String getLabel() {
      return _label;
   }
	
   public void setLabel (String _label) {
      this._label = _label;
   }
   
   
   public String getDescription() {
      return _description;
   }
	
   public void setDescription (String _description) {
      this._description = _description;
   }
   
   
   public String getEditable() {
      return _editable;
   }
	
   public void setEditable (String _editable) {
      this._editable = _editable;
   }
   
   
   public String getVisible() {
      return _visible;
   }
	
   public void setVisible (String _visible) {
      this._visible = _visible;
   }
   
   
   public Boolean getIsactionform() {
      return _isActionForm;
   }
	
   public void setIsactionform (Boolean _isActionForm) {
      this._isActionForm = _isActionForm;
   }
   
   
   public String getAction() {
      return _action;
   }
	
   public void setAction (String _action) {
      this._action = _action;
   }
   
   
   public String getDefaultvalue() {
      return _defaultValue;
   }
	
   public void setDefaultvalue (String _defaultValue) {
      this._defaultValue = _defaultValue;
   }
   
   
   public String getExamplevalue() {
      return _exampleValue;
   }
	
   public void setExamplevalue (String _exampleValue) {
      this._exampleValue = _exampleValue;
   }
   
   
   public String getPositionx() {
      return _positionX;
   }
	
   public void setPositionx (String _positionX) {
      this._positionX = _positionX;
   }
   
   
   public String getPositiony() {
      return _positionY;
   }
	
   public void setPositiony (String _positionY) {
      this._positionY = _positionY;
   }
   
   

}

