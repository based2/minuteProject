package net.sf.minuteProject.loader.init.node;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfigurationRoot;

public class Configuration extends AbstractConfigurationRoot{

   private String _name;
   private Definition _definition;
   private Targetlocation _targetLocation;

   public Configuration() {
   }

   public String getName() {
      return _name;
   }
	
   public void setName (String _name) {
      this._name = _name;
   }
   
   public Definition getDefinition() {
      return _definition;
   }
	
   public void setDefinition (Definition _definition) {
      this._definition = _definition;
   }
   
   public Targetlocation getTargetlocation() {
      return _targetLocation;
   }
	
   public void setTargetlocation (Targetlocation _targetLocation) {
      this._targetLocation = _targetLocation;
   }
   

}

