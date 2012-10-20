package net.sf.minuteProject.loader.catalog.databasecatalog.node; //schema database-catalog

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.catalog.databasecatalog.DatabasecatalogHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class Database extends AbstractConfigurationLoader{

    private String _packageName;
   private DatabaseCatalog _databaseCatalog;
   private String _name;
   private PrimaryKeyPolicy _primaryKeyPolicy;
   private MavenArtifact _mavenArtifact;
   private String _version;
   private String _entityNameMaxLength;
   private String _hibernateDialect;
   private Boolean _useSchema;
   private String _driverclassname;
   private String _defaultUrlStructure;

   public Database() {
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
	
   public String getName() {
	  if (_name == null)
	     _name = new String();
	      return _name;
   }
	
   public void setName (String _name) {
      this._name = _name;
   }
   
   
   public PrimaryKeyPolicy getPrimaryKeyPolicy() {
	  if (_primaryKeyPolicy == null)
	     _primaryKeyPolicy = new PrimaryKeyPolicy();
	      return _primaryKeyPolicy;
   }
	
   public void setPrimaryKeyPolicy (PrimaryKeyPolicy _primaryKeyPolicy) {
      this._primaryKeyPolicy = _primaryKeyPolicy;
   }
   
   
   public MavenArtifact getMavenArtifact() {
	  if (_mavenArtifact == null)
	     _mavenArtifact = new MavenArtifact();
	      return _mavenArtifact;
   }
	
   public void setMavenArtifact (MavenArtifact _mavenArtifact) {
      this._mavenArtifact = _mavenArtifact;
   }
   
   
   public String getVersion() {
	  if (_version == null)
	     _version = new String();
	      return _version;
   }
	
   public void setVersion (String _version) {
      this._version = _version;
   }
   
   
   public String getEntityNameMaxLength() {
	  if (_entityNameMaxLength == null)
	     _entityNameMaxLength = new String();
	      return _entityNameMaxLength;
   }
	
   public void setEntityNameMaxLength (String _entityNameMaxLength) {
      this._entityNameMaxLength = _entityNameMaxLength;
   }
   
   
   public String getHibernateDialect() {
	  if (_hibernateDialect == null)
	     _hibernateDialect = new String();
	      return _hibernateDialect;
   }
	
   public void setHibernateDialect (String _hibernateDialect) {
      this._hibernateDialect = _hibernateDialect;
   }
   
   
   public Boolean useSchema() {
      return getUseSchema();
   }
   
   public Boolean getUseSchema() {
	  if (_useSchema == null)
	     _useSchema = new Boolean(true);
	      return _useSchema;
   }
	
   public void setUseSchema (Boolean _useSchema) {
      this._useSchema = _useSchema;
   }
   
   
   public String getDriverclassname() {
	  if (_driverclassname == null)
	     _driverclassname = new String();
	      return _driverclassname;
   }
	
   public void setDriverclassname (String _driverclassname) {
      this._driverclassname = _driverclassname;
   }
   
   
   public String getDefaultUrlStructure() {
	  if (_defaultUrlStructure == null)
	     _defaultUrlStructure = new String();
	      return _defaultUrlStructure;
   }
	
   public void setDefaultUrlStructure (String _defaultUrlStructure) {
      this._defaultUrlStructure = _defaultUrlStructure;
   }
   
   

}

