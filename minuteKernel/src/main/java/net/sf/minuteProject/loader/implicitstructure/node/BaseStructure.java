package net.sf.minuteProject.loader.implicitstructure.node; //schema base-structure

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.implicitstructure.ImplicitstructureHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class BaseStructure extends AbstractConfigurationLoader{

    private String _packageName;
   private String _name;
   private String _id;
   private String _structureType;
   private String _separator;
   private String _location;
   private Lines _lines;

   public BaseStructure() {
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
   
   
   public String getId() {
	  if (_id == null)
	     _id = new String();
	      return _id;
   }
	
   public void setId (String _id) {
      this._id = _id;
   }
   
   
   public String getStructureType() {
	  if (_structureType == null)
	     _structureType = new String();
	      return _structureType;
   }
	
   public void setStructureType (String _structureType) {
      this._structureType = _structureType;
   }
   
   
   public String getSeparator() {
	  if (_separator == null)
	     _separator = new String();
	      return _separator;
   }
	
   public void setSeparator (String _separator) {
      this._separator = _separator;
   }
   
   
   public String getLocation() {
	  if (_location == null)
	     _location = new String();
	      return _location;
   }
	
   public void setLocation (String _location) {
      this._location = _location;
   }
   
   
   public Lines getLines() {
	  if (_lines == null)
	     _lines = new Lines();
	      return _lines;
   }
	
   public void setLines (Lines _lines) {
      this._lines = _lines;
   }
   
   

}

