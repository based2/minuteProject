package net.sf.minuteProject.plugin.server.wls

import org.apache.commons.lang.StringUtils;

import net.sf.minuteProject.configuration.bean.Model;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.utils.FormatUtils;

class WeblogicUtils {

	public String getContextRoot(Template template, Model model) {
		String context = template.getPropertyValue("context-root")
		if (StringUtils.isEmpty(context)) {
			context = model.getName()
		}
		context
	}

}
