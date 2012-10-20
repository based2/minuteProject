package net.sf.minuteProject.utils.technology;

import org.apache.commons.lang.StringUtils;

import net.sf.minuteProject.loader.catalog.technologycatalog.node.Technology;

public class TechnologyUtils {

	public static String getTechnologyTemplateDir (Technology technology, String rootDir){
		if (rootDir==null)
			return technology.getTemplateDir();
		return StringUtils.replace(technology.getTemplateDir(), "@templateRoot@", rootDir);
	}
}
