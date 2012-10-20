package net.sf.minuteProject.plugin.jpa

import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.model.data.Reference;
import net.sf.minuteProject.utils.TableUtils;

class Jpa2Utils {

	public static final String JPA2_IMPLEMENTATION_HIBERNATE = "hibernate"
	public static final String JPA2_IMPLEMENTATION_ECLIPSELINK = "eclipselink"

	static final String FETCH_TYPE = "fetch-type"

	static final String EAGER_WHEN_PARENT_CONTENT_IS_REFERENCE_DATA = "eager-when-parent-content-is-reference-data"

	static final String EAGER = "EAGER"

	static final String LAZY = "LAZY"

	static final String ADD_JPA2_IMPLEMENTATION = "add-jpa2-implementation"
	
	public static String getMetamodelVolatileObjects (Table table) {
		""
	}
	
	public static String getJpa2Implementation (Template template) {
		String s = template.getPropertyValue(ADD_JPA2_IMPLEMENTATION)
		if (s==null) return JPA2_IMPLEMENTATION_HIBERNATE
		s.toLowerCase();
	}
	
	public static String getFetchType(Template template, Reference reference) {
		boolean isEagerWithReferenceDataParent = template.hasPropertyValue(FETCH_TYPE,EAGER_WHEN_PARENT_CONTENT_IS_REFERENCE_DATA)
		if (isEagerWithReferenceDataParent && TableUtils.isReferenceDataContentType(reference.getForeignTable()))
			return EAGER
		return LAZY
	}
}
