package net.sf.minuteProject.configuration.bean.strategy.datamodel;

import org.apache.commons.lang.StringUtils;

public enum PrimaryKeyPolicyPatternEnum {
	SEQUENCE,
	IDENTITY,
	AUTOINCREMENT,
	NONE,
	OTHER;

	public static PrimaryKeyPolicyPatternEnum getPrimaryKeyPolicy(String pkPolicy) {
		pkPolicy = StringUtils.upperCase(pkPolicy);
		for (PrimaryKeyPolicyPatternEnum pk : values()) {
			if (pk.toString().equals(pkPolicy))
				return pk;
		}
		return null;
	}
}
