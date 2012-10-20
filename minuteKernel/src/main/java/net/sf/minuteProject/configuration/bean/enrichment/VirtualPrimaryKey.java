package net.sf.minuteProject.configuration.bean.enrichment;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.system.Property;

public class VirtualPrimaryKey extends AbstractConfiguration{

	private boolean isRealPrimaryKey;
	private List<Property> properties;
	private String columnName;
	
	public void setProperty (Property property) {
		addProperty(property);
	}
	public void addProperty (Property property) {
		if (properties==null)
			properties = new ArrayList<Property> ();
		properties.add(property);
	}

	public boolean isRealPrimaryKey() {
		return isRealPrimaryKey;
	}

	public void setRealPrimaryKey(boolean isRealPrimaryKey) {
		this.isRealPrimaryKey = isRealPrimaryKey;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}	
	
	
	
}
