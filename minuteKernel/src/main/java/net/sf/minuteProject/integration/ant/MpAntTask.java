package net.sf.minuteProject.integration.ant;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;

public class MpAntTask extends Task {

	private String schema, 
		driver, 
		url, 
		username, 
		password, 
		primaryKeyPolicy, 
		target, 
		database, 
		rootpackage,
		businesspackage,
		version,
		configuration;
	
    public void execute() {
	   log("MinuteProject generator", Project.MSG_INFO);
	   //load DB matrix for default
	   //load target matrix (ex: openxava, granity...)
	   //
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrimaryKeyPolicy() {
		return primaryKeyPolicy;
	}

	public void setPrimaryKeyPolicy(String primaryKeyPolicy) {
		this.primaryKeyPolicy = primaryKeyPolicy;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getConfiguration() {
		return configuration;
	}

	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}

	public String getRootpackage() {
		return rootpackage;
	}

	public void setRootpackage(String rootpackage) {
		this.rootpackage = rootpackage;
	}

	public String getBusinesspackage() {
		return businesspackage;
	}

	public void setBusinesspackage(String businesspackage) {
		this.businesspackage = businesspackage;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	
}
