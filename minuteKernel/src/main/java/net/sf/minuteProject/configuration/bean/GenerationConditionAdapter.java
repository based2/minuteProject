package net.sf.minuteProject.configuration.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GenerationConditionAdapter extends AbstractConfiguration {
	
	public static final String FILTER_FILE_TYPE_EXCLUDE = "exclude";
	public static final String FILTER_FILE_TYPE_INCLUDE = "include";
	
	private String defaultType;
	private List<Condition> conditions;
	
	public void addCondition (Condition condition) {
		if (conditions==null)
			conditions = new ArrayList<Condition>();
		conditions.add(condition);
	}

	public List<Condition> getConditions() {
		if (conditions== null)
			conditions = new ArrayList<Condition>();
		return conditions;
	}
	
	public boolean isAddable (String valueToTest) {
		if (defaultType==null || defaultType.equals(FILTER_FILE_TYPE_INCLUDE))
			return includeOtherwiseExpressInExcludeExpression(valueToTest);
		else
			return excludeOtherwiseExpressInIncludeExpression(valueToTest);		
	}
	
	public boolean areConditionsTrue(String valueToTest) {
		if (defaultType==null || defaultType.equals(FILTER_FILE_TYPE_INCLUDE)) //default set to include
			return includeOtherwiseExpressInExcludeExpression(valueToTest);
		else
			return excludeOtherwiseExpressInIncludeExpression(valueToTest);
	}
	
	public boolean includeOtherwiseExpressInExcludeExpression(String valueToTest) {
		for (Condition condition : getConditions()){
			if (condition.getType().equals(FILTER_FILE_TYPE_EXCLUDE)) {
				if (condition.isConditionTrue(valueToTest))
					return false;
			}
		}
		return true;
	}
	
	public boolean excludeOtherwiseExpressInIncludeExpression(String valueToTest) {
		for (Condition condition : getConditions()){
			if (condition.getType().equals(FILTER_FILE_TYPE_INCLUDE) && condition.isConditionTrue(valueToTest))
				return true;
		}
		return false;
	}

	public String getDefaultType() {
		return defaultType;
	}

	public void setDefaultType(String defaultType) {
		this.defaultType = defaultType;
	}
	
	
}
