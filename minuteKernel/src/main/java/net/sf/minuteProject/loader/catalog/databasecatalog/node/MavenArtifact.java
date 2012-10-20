package net.sf.minuteProject.loader.catalog.databasecatalog.node; //schema database-catalog

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.catalog.databasecatalog.DatabasecatalogHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class MavenArtifact extends AbstractConfigurationLoader{

    private String _packageName;
   private DatabaseCatalog _databaseCatalog;
   private String _mvnArtifactId;
   private String _mvnGroupId;
   private String _mvnVersion;

   public MavenArtifact() {
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
   
   public DatabaseCatalog getDatabaseCatalog() {
      return DatabasecatalogHolder.getDatabaseCatalog(); 
   }
	
   public String getMvnArtifactId() {
	  if (_mvnArtifactId == null)
	     _mvnArtifactId = new String();
	      return _mvnArtifactId;
   }
	
   public void setMvnArtifactId (String _mvnArtifactId) {
      this._mvnArtifactId = _mvnArtifactId;
   }
   
   
   public String getMvnGroupId() {
	  if (_mvnGroupId == null)
	     _mvnGroupId = new String();
	      return _mvnGroupId;
   }
	
   public void setMvnGroupId (String _mvnGroupId) {
      this._mvnGroupId = _mvnGroupId;
   }
   
   
   public String getMvnVersion() {
	  if (_mvnVersion == null)
	     _mvnVersion = new String();
	      return _mvnVersion;
   }
	
   public void setMvnVersion (String _mvnVersion) {
      this._mvnVersion = _mvnVersion;
   }
   
   

}

