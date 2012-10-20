package net.sf.minuteProject.configuration.bean.limitation;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.BusinessModel;

public abstract class Limitation extends AbstractConfiguration {

	public abstract void apply(BusinessModel model) ;
}
