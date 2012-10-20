package net.sf.minuteProject.configuration.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GenerationCondition extends GenerationConditionAdapter {
	
//	public static final String FILTER_FILE_TYPE_EXCLUDE = "exclude";
//	public static final String FILTER_FILE_TYPE_INCLUDE = "include";
//	
//	private String defaultType;
//	private List<Condition> conditions;
	private boolean excludeTables=false, excludeViews=false;
	
//	public void addCondition (Condition condition) {
//		if (conditions==null)
//			conditions = new ArrayList<Condition>();
//		conditions.add(condition);
//	}
//
//	public List<Condition> getConditions() {
//		if (conditions== null)
//			conditions = new ArrayList<Condition>();
//		return conditions;
//	}
//	
//	public boolean isAddable (String valueToTest) {
//		if (defaultType==null || !defaultType.equals(FILTER_FILE_TYPE_EXCLUDE))
//			return areConditionsTrueInclude(valueToTest);
//		else
//			return areConditionsTrueExclude(valueToTest);		
//	}
//	
//	public boolean areConditionsTrue(String valueToTest) {
//		if (defaultType==null || !defaultType.equals(FILTER_FILE_TYPE_EXCLUDE))
//			return areConditionsTrueInclude(valueToTest);
//		else
//			return areConditionsTrueExclude(valueToTest);
//	}
//	
//	public boolean areConditionsTrueInclude(String valueToTest) {
//		for (Iterator iter = getConditions().iterator(); iter.hasNext();){
//			Condition condition = (Condition)iter.next();
//			if (condition.getType().equals(FILTER_FILE_TYPE_EXCLUDE) && !condition.isConditionTrue(valueToTest))
//				return false;
//		}
//		return true;
//	}
//	
//	public boolean areConditionsTrueExclude(String valueToTest) {
//		for (Iterator iter = getConditions().iterator(); iter.hasNext();){
//			Condition condition = (Condition)iter.next();
//			if (condition.getType().equals(FILTER_FILE_TYPE_INCLUDE) && condition.isConditionTrue(valueToTest))
//				return true;
//		}
//		return false;
//	}


	public boolean isExcludeTables() {
		return excludeTables;
	}

	public void setExcludeTables(boolean excludeTables) {
		this.excludeTables = excludeTables;
	}

	public boolean isExcludeViews() {
		return excludeViews;
	}

	public void setExcludeViews(boolean excludeViews) {
		this.excludeViews = excludeViews;
	}

}
