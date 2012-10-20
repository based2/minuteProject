package net.sf.minuteProject.loader.mapping;

import net.sf.minuteProject.loader.mapping.node.BeanMap;

public class MappingHolder {

   private static BeanMap _beanMap;

   public MappingHolder() {
   }

   public static BeanMap getBeanMap() {
      return _beanMap;
   }
	
   public static void setBeanMap (BeanMap _beanMap2) {
	   if (_beanMap!=null) return;
      _beanMap = _beanMap2;
   }
   
}

