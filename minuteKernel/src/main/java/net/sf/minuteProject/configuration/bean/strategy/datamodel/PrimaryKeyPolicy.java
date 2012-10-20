package net.sf.minuteProject.configuration.bean.strategy.datamodel;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.DataModel;

public class PrimaryKeyPolicy extends AbstractConfiguration {

	private DataModel dataModel;
	private boolean oneGlobal;
	private boolean oneForEachTable;
	
	private List<PrimaryKeyPolicyPattern> primaryKeyPolicyPatterns;

	public boolean isOneForEachTable() {
		return oneForEachTable;
	}

	public void setOneForEachTable(boolean oneForEachTable) {
		this.oneForEachTable = oneForEachTable;
	}

	public boolean isOneGlobal() {
		return oneGlobal;
	}

	public void setOneGlobal(boolean oneGlobal) {
		this.oneGlobal = oneGlobal;
	}

	public List<PrimaryKeyPolicyPattern> getPrimaryKeyPolicyPatterns() {
		return primaryKeyPolicyPatterns;
	}

	public void setPrimaryKeyPolicyPatterns(
			List<PrimaryKeyPolicyPattern> primaryKeyPolicyPatterns) {
		this.primaryKeyPolicyPatterns = primaryKeyPolicyPatterns;
	}
	
	public void addPrimaryKeyPolicyPattern (PrimaryKeyPolicyPattern primaryKeyPolicyPattern) {
		if (primaryKeyPolicyPatterns==null)
			primaryKeyPolicyPatterns = new ArrayList<PrimaryKeyPolicyPattern>();
		primaryKeyPolicyPattern.setPrimaryKeyPolicy(this);
		primaryKeyPolicyPatterns.add(primaryKeyPolicyPattern);
	}

	public DataModel getDataModel() {
		return dataModel;
	}
	public void setDataModel(DataModel dataModel) {
		this.dataModel = dataModel;
	}
	
	public PrimaryKeyPolicyPattern getFirstPrimaryKeyPolicyPattern() {
		for (PrimaryKeyPolicyPattern primaryKeyPolicyPattern : getPrimaryKeyPolicyPatterns()) {
			return primaryKeyPolicyPattern;
		}
		return null;
	}
}
