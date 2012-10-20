package net.sf.minuteProject.configuration.bean.system;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;

public class Property extends AbstractConfiguration{
	
	public String value;
	public String tag;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTag() {
		if (tag==null) tag="";
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
}
