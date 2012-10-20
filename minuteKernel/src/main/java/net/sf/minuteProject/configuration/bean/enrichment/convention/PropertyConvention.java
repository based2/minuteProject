package net.sf.minuteProject.configuration.bean.enrichment.convention;

import org.apache.commons.lang.StringUtils;

import net.sf.minuteProject.configuration.bean.BusinessModel;
import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.system.Property;
import net.sf.minuteProject.utils.parser.ParserUtils;

public class PropertyConvention extends ModelConvention {

	private static final String ENTITY = "entity";
	private static final String FIELD = "field";
	private static final String PACKAGE = "package";
	private static final String MODEL = "model";
	private static final String APPLICATION = "application";
	
	private String scope, tag, property, value, pattern;
	
	@Override
	public void apply(BusinessModel model) {
		if (isValid()) {
			if (model.getBusinessPackage()!=null) {
				for (Table table : model.getBusinessPackage().getEntities()) {
					apply (table);
				}
			}
		}
	}

	private boolean isValid() {
		if (pattern==null)
			return false;
		if (!ENTITY.equals(scope) &&
			!FIELD.equals(scope) &&
			!PACKAGE.equals(scope) )
			return false;
		return true;
	}

	private void apply(Table table) {
		if (ENTITY.equals(scope)) {
			addProperty (table);
			return;
		}
		if (FIELD.equals(scope)) {
			for (Column column : table.getColumns()) {
				addProperty(column);
			}
			return;
		}
	}

	private void addProperty(GeneratorBean bean) {
		String beanName = bean.getName();
		if (beanName==null)
			return;
		if (matchPattern(beanName)) {
			for (String t: ParserUtils.getList(tag)){
				bean.getProperties().add(getTagProperty(t));
			}
			for (String p: ParserUtils.getList(property)){
				bean.getProperties().add(getProperty(p));
			}
		}
	}

	private boolean matchPattern(String beanName) {
		String beanUpperCase = StringUtils.upperCase(beanName);
		String patternUpperCase = StringUtils.upperCase(pattern);
		if ("start-with".equals(type)) {
			return beanUpperCase.startsWith(patternUpperCase);
		}
		if ("end-with".equals(type)) {
			return beanUpperCase.endsWith(patternUpperCase);
		}
		return beanUpperCase.equals(patternUpperCase);
	}

	private Property getProperty(String p) {
		Property property = new Property();
		property.setValue(value);
		property.setName(p);
		return property;
	}

	private Property getTagProperty(String t) {
		Property property = new Property();
		property.setTag(t);
		return property;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
}
