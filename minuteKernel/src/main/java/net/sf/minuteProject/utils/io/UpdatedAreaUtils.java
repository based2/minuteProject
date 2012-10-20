package net.sf.minuteProject.utils.io;

import java.util.Map;

import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import static net.sf.minuteProject.utils.TemplateUtils.commentLine;

public class UpdatedAreaUtils {

	public static final String MP_MANAGED_STOP_GENERATING = "MP-MANAGED-STOP-GENERATING";
	public static final String MP_MANAGED_ADDED_AREA = "MP-MANAGED-ADDED-AREA";
	public static final String MP_MANAGED_ADDED_AREA_BEGIN_APPENDIX = "-BEGINNING";
	public static final String MP_MANAGED_ADDED_AREA_END_APPENDIX = "-ENDING";
	public static final String MP_MANAGED_REFERENCE_MARKER = "@";
	
	public static final String MP_MANAGED_ADDED_AREA_BEGINNING = MP_MANAGED_ADDED_AREA+MP_MANAGED_ADDED_AREA_BEGIN_APPENDIX;
	public static final String MP_MANAGED_ADDED_AREA_ENDING = MP_MANAGED_ADDED_AREA+MP_MANAGED_ADDED_AREA_END_APPENDIX;

	public static final String MP_MANAGED_UPDATABLE_BEGINNING = "MP-MANAGED-UPDATABLE-BEGINNING";
	public static final String MP_MANAGED_UPDATABLE_ENDING = "MP-MANAGED-UPDATABLE-ENDING";

	public static final String STATUS_DISABLE_APPENDIX = "-DISABLE";
	public static final String STATUS_ENABLE_APPENDIX = "-ENABLE";

	public static final String MP_MANAGED_UPDATABLE_BEGINNING_ENABLE = MP_MANAGED_UPDATABLE_BEGINNING+STATUS_ENABLE_APPENDIX;
	public static final String MP_MANAGED_UPDATABLE_BEGINNING_DISABLE = MP_MANAGED_UPDATABLE_BEGINNING+STATUS_DISABLE_APPENDIX;
	public static final String IMPORT = "import";
	public static final String IMPLEMENTATION = "implementation";
	public static final String CLASS_ANNOTATION = "class-annotation";
	public static final String FIELD_ANNOTATION = "field-annotation";
	public static final String I18N_BEGIN = "i18n-begin";
	public static final String I18N_END   = "i18n-end";
	public static final String CONNECTOR = "-";
	public static final String GETTER_SETTER = "GETTER-SETTER";
	public static final String ATTRIBUTE = "ATTRIBUTE";
	public static final String CONSTRUCTOR_WITH_FIELDS = "CONSTRUCTOR-WITH-FIELDS";

	public static String getFieldAnnotationSnippet (Template template, Column column, Map<String, String> updatedAreas) {
		return getAddedAreaSnippet(template, updatedAreas, getColumnAnnotation(column));
	}
	
	private static String getColumnAnnotation(Column column) {
		return column.getName()+CONNECTOR+FIELD_ANNOTATION;
	}

	public static String getClassAnnotationSnippet (Template template, Map<String, String> updatedAreas) {
		return getAddedAreaSnippet(template, updatedAreas, CLASS_ANNOTATION);
	}
	
	public static String getImplementationSnippet (Template template, Map<String, String> updatedAreas) {
		return getAddedAreaSnippet(template, updatedAreas, IMPLEMENTATION);
	}
	
	public static String getImportSnippet (Template template, Map<String, String> updatedAreas) {
		return getAddedAreaSnippet(template, updatedAreas, IMPORT);
	}

	public static String getAddedAreaSnippet (Template template, Map<String, String> updatedAreas, String key) {
		if (!template.isUpdatable()) return null;
		String s = getSnippet (template, updatedAreas, key);
		return getAddedAreaSnippet (template, key, s).getContent();
	}
	
	public static String getSnippet (Template template, Map<String, String> updatedAreas, String key) {
		return getChunk (updatedAreas, key);
	}
	
	private static String getChunk(Map<String, String> updatedAreas, String key) {
		if (updatedAreas==null || key==null) return null;
		return updatedAreas.get(key);
	}

	public static UpdatedAreaHolder getAddedAreaSnippet(Template template, String key, String snippet) {
		UpdatedAreaHolder updatedAreaHolder = new UpdatedAreaHolder();
		updatedAreaHolder.setBeginSnippet(commentLine(template, MP_MANAGED_ADDED_AREA_BEGINNING+ " "+MP_MANAGED_REFERENCE_MARKER+key+MP_MANAGED_REFERENCE_MARKER));
		updatedAreaHolder.setSnippet(snippet);
		updatedAreaHolder.setEndSnippet(commentLine(template, MP_MANAGED_ADDED_AREA_ENDING+ " "+MP_MANAGED_REFERENCE_MARKER+key+MP_MANAGED_REFERENCE_MARKER));
		return updatedAreaHolder;
	}

	public static String getUpdatedAreaBeginSnippet(Template template, String s, boolean isUpdated) {
		String directive = (isUpdated)?MP_MANAGED_UPDATABLE_BEGINNING_ENABLE:MP_MANAGED_UPDATABLE_BEGINNING_DISABLE;
		return getManagedUpdatedAreaCommentSnippet(template, directive, s);
	}
	
	public static String getManagedUpdatedAreaCommentSnippet(Template template, String directive, String key) {
		if (!template.isUpdatable()) return "";
		StringBuffer sb = new StringBuffer();	
		sb.append(directive+ " "+MP_MANAGED_REFERENCE_MARKER+key+MP_MANAGED_REFERENCE_MARKER);
		return commentLine(template, sb.toString());
	}
	


	public static String getUpdatedAreaEndSnippet(Template template, String s) {
		if (!template.isUpdatable()) return "";
		StringBuffer sb = new StringBuffer();
		sb.append(MP_MANAGED_UPDATABLE_ENDING);
		return commentLine(template, sb.toString());
	}	

	public static UpdatedAreaHolder getConstructorWithFieldSnippet(Template template, Map<String, String> updatedAreas) {
		return getUpdatedAreaHolder (template, getConstructorWithFieldSnippet(), updatedAreas);
	}
	
	private static String getConstructorWithFieldSnippet() {
		return CONSTRUCTOR_WITH_FIELDS;
	}

	public static UpdatedAreaHolder getColumnAttributeBeginSnippet(Template template, Column column, Map<String, String> updatedAreas) {
		return getUpdatedAreaHolder (template, getColumnAttribute(column), updatedAreas);
	}
	
	public static UpdatedAreaHolder getColumnGetterSetterBeginSnippet(Template template, Column column, Map<String, String> updatedAreas) {
		return getUpdatedAreaHolder (template, getColumnGetterSetter(column), updatedAreas);
	}

	public static UpdatedAreaHolder getColumnSnippet(Template template, Column column, Map<String, String> updatedAreas, String area) {
		return getUpdatableSnippet (template, column, updatedAreas, area);
	}
	
	public static UpdatedAreaHolder getUpdatableSnippet(Template template, GeneratorBean bean, Map<String, String> updatedAreas, String area) {
		return getUpdatedAreaHolder (template, getUpdatableAreaKey(bean, area), updatedAreas);
	}	
	
	private static String getColumnAttribute(Column column) {
		return getUpdatableAreaKey(column, ATTRIBUTE);
	}
	
	private static String getColumnGetterSetter(Column column) {
		return getUpdatableAreaKey(column, GETTER_SETTER);
	}
	
	private static String getUpdatableAreaKey(GeneratorBean bean, String area) {
		if (bean!=null)
			return area+CONNECTOR+bean.getName();
		return area+CONNECTOR;
	}

	public static UpdatedAreaHolder getUpdatedAreaHolder(Template template, String key, Map<String, String> updatedAreas) {
		UpdatedAreaHolder updatedAreaHolder = new UpdatedAreaHolder();
		String s = getChunk (updatedAreas, key);
		if (s!=null) {
			updatedAreaHolder.setUpdated(true);
			updatedAreaHolder.setSnippet(s);
			updatedAreaHolder.setBeginSnippet(getUpdatedAreaBeginSnippet(template, key, true));
		} else {
			updatedAreaHolder.setUpdated(false);
			updatedAreaHolder.setBeginSnippet(getUpdatedAreaBeginSnippet(template, key, false));
//			updatedAreaHolder.setBeginSnippet(getUpdatedAreaBeginSnippet(template, key));
		}
		
		updatedAreaHolder.setEndSnippet(getUpdatedAreaEndSnippet(template, key));
		return updatedAreaHolder;
	}

	public static String getI18nBegin(Template template, Map<String, String> updatedAreas) {
		return getAddedAreaSnippet(template, updatedAreas, I18N_BEGIN);
	}

	public static String getI18nEnd(Template template, Map<String, String> updatedAreas) {
		return getAddedAreaSnippet(template, updatedAreas, I18N_END);
	}

}
