package net.sf.minuteProject.configuration.bean.enrichment.convention;

import net.sf.minuteProject.configuration.bean.BusinessModel;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.utils.parser.ParserUtils;
import static net.sf.minuteProject.utils.TableUtils.*;

public class EntityContentTypeConvention extends ModelConvention {

	public static final String APPLY_CONTENT_TYPE_TO_ENTITY_STARTING_WITH = "apply-content-type-to-entity-starting-with";
	public static final String APPLY_CONTENT_TYPE_TO_ENTITY_ENDING_WITH   = "apply-content-type-to-entity-ending-with";
	
	private String pattern, contentType;
	
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

	private void apply(Table table) {
		if (isMatch (table))
			table.setContentType(getContentType());
	}

	private boolean isMatch(Table table) {
		if (APPLY_CONTENT_TYPE_TO_ENTITY_ENDING_WITH.equals(type))
			return isEndingMatch(table);
		return isBeginningMatch(table);
	}

	private boolean isBeginningMatch(Table table) {
		for (String s : ParserUtils.getList(pattern.toLowerCase())) {
			if (table.getName().toLowerCase().startsWith(s))
				return true;
		}
		return false;
	}
	
	private boolean isEndingMatch(Table table) {
		for (String s : ParserUtils.getList(pattern.toLowerCase())) {
			if (table.getName().toLowerCase().endsWith(s))
				return true;
		}
		return false;
	}

	private boolean isValid () {
		if ((APPLY_CONTENT_TYPE_TO_ENTITY_ENDING_WITH.equals(type) 
			|| APPLY_CONTENT_TYPE_TO_ENTITY_STARTING_WITH.equals(type)) 
			&& getPattern()!=null 
			&& (   pseudoStaticDataContentType.equals(getContentType())
			    || masterDataContentType.equals(getContentType())
			    || liveBusinessDataContentType.equals(getContentType())
			    || referenceDataContentType.equals(getContentType())
			    )
			)
			return true;
		return false;
	}
	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

}
