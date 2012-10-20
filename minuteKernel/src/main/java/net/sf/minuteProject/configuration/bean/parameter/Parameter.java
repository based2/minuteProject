package net.sf.minuteProject.configuration.bean.parameter;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;

/**
 * @author Florian Adler
 *
 */
public class Parameter extends AbstractConfiguration{

	private String value;
	private String type;
	private String mandatory;
	private LogicalLink logicalLink;
	
	public LogicalLink getLogicalLink() {
		return logicalLink;
	}

	public void setLogicalLink(LogicalLink logicalLink) {
		this.logicalLink = logicalLink;
	}

	public String getMandatory() {
		return mandatory;
	}

	public void setMandatory(String mandatory) {
		this.mandatory = mandatory;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	public boolean hasLogicalLink() {
		return logicalLink!=null;
	}
	
}
