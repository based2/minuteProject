package net.sf.minuteProject.loader.implicitstructure.node; //schema base-structure

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.implicitstructure.ImplicitstructureHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class Line extends AbstractConfigurationLoader{

    private String _packageName;
   private BaseStructure _baseStructure;
   private String _name;
   private String _id;
   private String _launchExecutable;

   public Line() {
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
   
   public BaseStructure getBaseStructure() {
      return ImplicitstructureHolder.getBaseStructure(); 
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
   
   
   public String getLaunchExecutable() {
	  if (_launchExecutable == null)
	     _launchExecutable = new String();
	      return _launchExecutable;
   }
	
   public void setLaunchExecutable (String _launchExecutable) {
      this._launchExecutable = _launchExecutable;
   }
   
   

}

