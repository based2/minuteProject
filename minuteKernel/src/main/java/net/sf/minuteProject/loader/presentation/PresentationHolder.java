package net.sf.minuteProject.loader.presentation;

import net.sf.minuteProject.loader.presentation.node.Dictionary;

public class PresentationHolder {

   private static Dictionary _dictionary;

   public PresentationHolder() {
   }

   public static Dictionary getDictionary() {
      return _dictionary;
   }
	
   public static void setDictionary (Dictionary _dictionary2) {
      _dictionary = _dictionary2;
   }
   
}

