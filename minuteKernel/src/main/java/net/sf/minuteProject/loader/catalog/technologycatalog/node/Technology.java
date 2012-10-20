package net.sf.minuteProject.loader.catalog.technologycatalog.node; //schema technology-catalog

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.catalog.technologycatalog.TechnologycatalogHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class Technology extends AbstractConfigurationLoader{

    private String _packageName;
   private TechnologyCatalog _technologyCatalog;
   private String _name;
   private String _version;
   private String _templateConfigFileName;
   private String _templateDir;
   private String _defaultOutputdir;
   private String _dependsOnTargets;
   private String _comment;
   private String _description;
   private String _helper;
   private String _status;
   private String _modelType;
   private String _targetName;
   private Boolean _isGenerable;
   private Frameworks _frameworks;
   private Limitations _limitations;
   private Conventions _conventions;

   public Technology() {
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
   
   public TechnologyCatalog getTechnologyCatalog() {
      return TechnologycatalogHolder.getTechnologyCatalog(); 
   }
	
   public String getName() {
	  if (_name == null)
	     _name = new String();
	      return _name;
   }
	
   public void setName (String _name) {
      this._name = _name;
   }
   
   
   public String getVersion() {
	  if (_version == null)
	     _version = new String();
	      return _version;
   }
	
   public void setVersion (String _version) {
      this._version = _version;
   }
   
   
   public String getTemplateConfigFileName() {
	  if (_templateConfigFileName == null)
	     _templateConfigFileName = new String();
	      return _templateConfigFileName;
   }
	
   public void setTemplateConfigFileName (String _templateConfigFileName) {
      this._templateConfigFileName = _templateConfigFileName;
   }
   
   
   public String getTemplateDir() {
	  if (_templateDir == null)
	     _templateDir = new String();
	      return _templateDir;
   }
	
   public void setTemplateDir (String _templateDir) {
      this._templateDir = _templateDir;
   }
   
   
   public String getDefaultOutputdir() {
	  if (_defaultOutputdir == null)
	     _defaultOutputdir = new String();
	      return _defaultOutputdir;
   }
	
   public void setDefaultOutputdir (String _defaultOutputdir) {
      this._defaultOutputdir = _defaultOutputdir;
   }
   
   
   public String getDependsOnTargets() {
	  if (_dependsOnTargets == null)
	     _dependsOnTargets = new String();
	      return _dependsOnTargets;
   }
	
   public void setDependsOnTargets (String _dependsOnTargets) {
      this._dependsOnTargets = _dependsOnTargets;
   }
   
   
   public String getComment() {
	  if (_comment == null)
	     _comment = new String();
	      return _comment;
   }
	
   public void setComment (String _comment) {
      this._comment = _comment;
   }
   
   
   public String getDescription() {
	  if (_description == null)
	     _description = new String();
	      return _description;
   }
	
   public void setDescription (String _description) {
      this._description = _description;
   }
   
   
   public String getHelper() {
	  if (_helper == null)
	     _helper = new String();
	      return _helper;
   }
	
   public void setHelper (String _helper) {
      this._helper = _helper;
   }
   
   
   public String getStatus() {
	  if (_status == null)
	     _status = new String();
	      return _status;
   }
	
   public void setStatus (String _status) {
      this._status = _status;
   }
   
   public String getModelType() {
		if (_modelType == null)
			_modelType = new String();
		return _modelType;
	}
		
   public void setModelType (String _modelType) {
      this._modelType = _modelType;
   }
	   
   public String getTargetName() {
	  if (_targetName == null)
	     _targetName = new String();
	      return _targetName;
   }
	
   public void setTargetName (String _targetName) {
      this._targetName = _targetName;
   }
   
   
   public Boolean isGenerable() {
      return getIsGenerable();
   }
   
   public Boolean getIsGenerable() {
	  if (_isGenerable == null)
	     _isGenerable = new Boolean(true);
	      return _isGenerable;
   }
	
   public void setIsGenerable (Boolean _isGenerable) {
      this._isGenerable = _isGenerable;
   }
   
   
   public Frameworks getFrameworks() {
	  if (_frameworks == null)
	     _frameworks = new Frameworks();
	      return _frameworks;
   }
	
   public void setFrameworks (Frameworks _frameworks) {
      this._frameworks = _frameworks;
   }
   
   
   public Limitations getLimitations() {
	  if (_limitations == null)
	     _limitations = new Limitations();
	      return _limitations;
   }
	
   public void setLimitations (Limitations _limitations) {
      this._limitations = _limitations;
   }
   
   
   public Conventions getConventions() {
	  if (_conventions == null)
	     _conventions = new Conventions();
	      return _conventions;
   }
	
   public void setConventions (Conventions _conventions) {
      this._conventions = _conventions;
   }
   
   

}

