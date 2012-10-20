package net.sf.minuteProject.loader.implicitstructure;

import net.sf.minuteProject.loader.implicitstructure.node.BaseStructure;

public class ImplicitstructureHolder {

   private static BaseStructure _baseStructure;

   public ImplicitstructureHolder() {
   }

   public static BaseStructure getBaseStructure() {
      return _baseStructure;
   }
	
   public static void setBaseStructure (BaseStructure _baseStructure2) {
      _baseStructure = _baseStructure2;
   }
   
}

