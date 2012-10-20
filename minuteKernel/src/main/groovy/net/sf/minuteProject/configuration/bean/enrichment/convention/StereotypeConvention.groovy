package net.sf.minuteProject.configuration.bean.enrichment.convention

import org.apache.log4j.Logger;

import net.sf.minuteProject.configuration.bean.BusinessModel;
import net.sf.minuteProject.configuration.bean.enrichment.SemanticReference;
import net.sf.minuteProject.configuration.bean.enrichment.Stereotype;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.utils.StringUtils;
import net.sf.minuteProject.utils.parser.ParserUtils;

class StereotypeConvention extends ModelConvention{

	String fieldPattern, fieldPatternType, fieldType, stereotype
	List<String> fieldPatternList 
	Boolean override=true
	private Logger logger = Logger.getLogger(StereotypeConvention.class);
	
	@Override
	public void apply(BusinessModel model) {
		if (isValid()) {
			if (model.getBusinessPackage()!=null) {
				for (Table table : model.getBusinessPackage().getEntities()) {
					apply (table);
				}
			}
		} else
			logger.error("StereotypeConvention not valid")
	}

	public void apply(Table table) {
		for (Column column : table.getColumns()) {
			if (match(column)) {
				logger.debug("applying stereotype "+stereotype+" to column "+column.getName())
				column.setStereotype(new Stereotype(stereotype: stereotype))
			}
		}
	}
	
	private boolean match(Column column) {
		boolean matchFieldType = false
		boolean matchFieldPattern = false
		if (hasFieldType()) {
			matchFieldType = column.getType().toLowerCase().equals(fieldType.toLowerCase())
		} else
			matchFieldType=true
		if (hasFieldPatternType() && hasFieldPattern()) {
			for (String fp : getFieldPatternList()) {
				//println "${column} and ${stereotype} and ${fp}"
				matchFieldPattern = StringUtils.checkExpression(column.getName(), fieldPatternType, fp)
				if (matchFieldPattern)
					break;
			}
		} else
			matchFieldPattern = true
		//println "${column} and ${stereotype} and ${matchFieldType} && ${matchFieldPattern}"
		return matchFieldType && matchFieldPattern
	}
	
	private List<String> getFieldPatternList() {
		if (fieldPatternList==null) {
			fieldPatternList = ParserUtils.getList(fieldPattern)
		}
		fieldPatternList
	}
	
	private boolean isValid() {
		hasStereotype() && (hasFieldType() || (hasFieldPatternType() && hasFieldPattern())) ;
	}
	
	private boolean hasFieldType() {
		fieldType!=null
	}
	
	private boolean hasFieldPatternType() {
		fieldPatternType!=null
	}
	
	private boolean hasFieldPattern() {
		fieldPattern!=null
	}
	
	private boolean hasStereotype() {
		stereotype!=null
	}
}
