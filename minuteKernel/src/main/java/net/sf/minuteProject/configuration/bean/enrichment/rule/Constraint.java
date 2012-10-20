package net.sf.minuteProject.configuration.bean.enrichment.rule;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.model.data.Table;

public class Constraint extends Rule<Table>{
	public String getValue() {
		if (value==null)
			value = "@Constraint";
		return value;
	}
}
