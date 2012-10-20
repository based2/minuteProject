package net.sf.minuteProject.plugin.persistence

import net.sf.minuteProject.configuration.bean.Model;

class PersistenceUtils {

	public static String getDatasourceName(Model model) {
		'jdbc/'+model.name+'DS'
	}
	
}
