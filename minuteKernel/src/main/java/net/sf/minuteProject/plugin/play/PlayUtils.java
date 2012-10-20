package net.sf.minuteProject.plugin.play;

import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.enrichment.Stereotype;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.plugin.format.I18nUtils;
import net.sf.minuteProject.plugin.grails.GrailsUtils;
import net.sf.minuteProject.plugin.rest.RestUtils;
import net.sf.minuteProject.utils.FormatUtils;

public class PlayUtils {

	public boolean hasStereotype (Column column) {
		if (column!=null) {
			Stereotype stereotype = column.getStereotype();
			if (isPlayStereotype(stereotype))
				return true;
		}
		return false;
	}

	private boolean isPlayStereotype(Stereotype stereotype) {
		if (stereotype!=null) {
			String stereo = stereotype.getStereotype();
			if (getStereotype(stereo)!=null)
				return true;
		}
		return false;
	}

	public String getStereotype(Column column) {
		Stereotype stereotype = column.getStereotype();
		if (isPlayStereotype(stereotype)) {
			return getStereotype(stereotype.getStereotype());
		}
		return null;
	}
	public String getStereotype(String stereo) {
		stereo = stereo.toLowerCase();
		if ("url".equals(stereo)) return "URL";
		if ("email".equals(stereo)) return "Email";
		return null;
	}

	public static String getToString (Table table) {
		return getToString(table, null);
	}
	
	public static String getToString (Table table, String def) {
		return GrailsUtils.getToString(table, def)+"+\"\"";
	}

	public static String getControllerName (Template template, GeneratorBean bean) {
		return RestUtils.getControllerName (template, bean);
	}
	
	public static String getRenderingPackageName (Template template, GeneratorBean bean) {
		return RestUtils.getRenderingPackageName(template, bean);
	}
	
}
