package net.sf.minuteProject.loader.init;

import net.sf.minuteProject.loader.init.node.Configuration;

public class InitHolder {

   private Configuration _configuration;

   public InitHolder() {
   }

   public Configuration getConfiguration() {
      return _configuration;
   }
	
   public void setConfiguration (Configuration _configuration) {
      this._configuration = _configuration;
   }
   
}

