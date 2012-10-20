package net.sf.minuteProject.configuration.bean.enrichment;

import org.apache.commons.lang.StringUtils;

import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.enrichment.rule.Rule;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.utils.FormatUtils;

public class Action extends Rule<Table> {

	public String getTechnicalPackage(Template template) {
		if (getParent()!=null)
			return getParent().getPackage().getTechnicalPackage(template)+"."+StringUtils.lowerCase(FormatUtils.getJavaName(getParent().getName()));
		return super.getTechnicalPackage(template);
	}
	
}
