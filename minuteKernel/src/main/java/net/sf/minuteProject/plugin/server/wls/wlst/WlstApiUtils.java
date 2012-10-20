package net.sf.minuteProject.plugin.server.wls.wlst;

import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.system.Property;
import net.sf.minuteProject.utils.parser.ParserUtils;

public class WlstApiUtils {

	public static String getWlstFileName (Template template, GeneratorBean bean) {
		String key = getKeyPrefix (template);
		return key + template.getName() +"."+template.getFileExtension();
	}

	private static String getKeyPrefix(Template template) {
		StringBuffer sb = new StringBuffer();
		Property property = template.getPropertyByName("key");
		for (String chunk : ParserUtils.getList(property.getValue())) {
			sb.append(chunk + "_");
		}
		return sb.toString();
	}
}
