package net.sf.minuteProject.configuration.bean;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import net.sf.minuteProject.utils.FormatUtils;

public abstract class AbstractConfigurationLoader extends AbstractConfiguration{

	public String getGeneratedBeanName() {
		return getName();
	}

	public String toString () {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
	}
}
