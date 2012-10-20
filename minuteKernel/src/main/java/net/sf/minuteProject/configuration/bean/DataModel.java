package net.sf.minuteProject.configuration.bean;

import java.io.File;

import javax.sql.DataSource;

import net.sf.minuteProject.configuration.bean.connection.Driver;
import net.sf.minuteProject.configuration.bean.model.data.DataModelFactory;
import net.sf.minuteProject.configuration.bean.model.data.Database;
import net.sf.minuteProject.configuration.bean.strategy.datamodel.PrimaryKeyPolicy;

import org.apache.ddlutils.Platform;
import org.apache.ddlutils.PlatformFactory;
import org.apache.ddlutils.io.DatabaseIO;

//import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;

public class DataModel {
	
	private Model model;
	private Database database;
	private DataSource datasource;
	private Driver driver;
	private FileSource fileSource;
	private String schema;
	private PrimaryKeyPolicy primaryKeyPolicy;
	
	private BasicDataSource basicDataSource;
	
	public BasicDataSource getBasicDataSource() {
		return basicDataSource;
	}

	public void setBasicDataSource(BasicDataSource basicDataSource) {
		this.basicDataSource = basicDataSource;
	}

	public DataSource getDatasource() {
		return datasource;
	}

	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}
	
	public void loadDatabase () {
		database = DataModelFactory.getInstance().getDatabase(this);
	}	

	public Database getDatabase() {
		return database;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public FileSource getFileSource() {
		return fileSource;
	}

	public void setFileSource(FileSource filesource) {
		this.fileSource = filesource;
	}

	public String getSchema() {
//		if (schema==null) {
//			BasicDataSource bds = (org.apache.commons.dbcp.BasicDataSource)getDatasource();
//			setSchema(bds.getUsername());
//		}
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = StringUtils.upperCase(schema);
	}

	public PrimaryKeyPolicy getPrimaryKeyPolicy() {
		return primaryKeyPolicy;
	}

	public void setPrimaryKeyPolicy(PrimaryKeyPolicy primaryKeyPolicy) {
		this.primaryKeyPolicy = primaryKeyPolicy;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	public boolean hasSchema () {
		return (getSchema()!=null && !"".equals(getSchema()))?true:false;
	}
	
}
