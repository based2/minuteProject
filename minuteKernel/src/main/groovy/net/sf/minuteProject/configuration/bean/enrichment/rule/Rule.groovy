package net.sf.minuteProject.configuration.bean.enrichment.rule

import org.apache.commons.lang.StringUtils;
import org.apache.tools.ant.types.selectors.ExtendSelector;

import net.sf.minuteProject.configuration.bean.enrichment.Action;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.utils.FormatUtils;

class Rule <T extends GeneratorBean> extends AbstractConfiguration{

	String getName (T t) {
		if (StringUtils.isEmpty(getName()))
			FormatUtils.getJavaName t.getAlias()
		else {
			if (this instanceof Action) {
				getName()
			} else {
				FormatUtils.getJavaName getName()
			}
		}
	}
	
	protected String value, errorMessage
	
	boolean isToImplement = false
	
	boolean isToImplement() {
		isToImplement
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
