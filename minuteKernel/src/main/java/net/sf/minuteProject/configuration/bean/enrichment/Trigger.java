package net.sf.minuteProject.configuration.bean.enrichment;

import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.enumeration.CRUDEnum;
import net.sf.minuteProject.configuration.bean.model.data.Column;

public class Trigger extends AbstractConfiguration  {

	public static final String UPDATE = "UPDATE";
	public static final String INSERT = "INSERT";
	public static final String CURRENT_TIME = "current-time";
	public static final String CURRENT_DATE = "current-date";
	
	public String action, className, value;
	public List<CRUDEnum> cruds;
	public Column column;
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public boolean isOfType(CRUDEnum crud) {
		for (CRUDEnum c : cruds) {
			if (c.equals(crud))
				return true;
		}
		return false;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<CRUDEnum> getCruds() {
		return cruds;
	}

	public void setCruds(List<CRUDEnum> cruds) {
		this.cruds = cruds;
	}

	public Column getColumn() {
		return column;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	
}
