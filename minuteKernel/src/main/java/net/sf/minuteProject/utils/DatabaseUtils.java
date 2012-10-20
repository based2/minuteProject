package net.sf.minuteProject.utils;

import net.sf.minuteProject.configuration.bean.Model;
import net.sf.minuteProject.configuration.bean.model.data.Database;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.strategy.datamodel.PrimaryKeyPolicy;
import net.sf.minuteProject.configuration.bean.strategy.datamodel.PrimaryKeyPolicyPattern;
import net.sf.minuteProject.configuration.bean.strategy.datamodel.PrimaryKeyPolicyPatternEnum;

public class DatabaseUtils {
	
	public static String providePrimaryKeyLookUpString (Table table) {
		Database database = table.getDatabase();
		if (database.getType().equals("DB2")){
			return "SELECT NEXTVAL FOR "+provideSequence(table)+" AS ID FROM SYSIBM.SYSDUMMY1";
		} else if (database.getType().equals("ORACLE")){
			return "SELECT "+provideSequence(table)+".NEXTVAL AS ID FROM DUAL";
		} else if (database.getType().equals("MYSQL")){
			return "SELECT LAST_INSERT_ID() AS value";
		}
		else if (database.getType().equals("HSQLDB")){
			return "SELECT NEXT VALUE FOR "+provideSequence(table)+" AS ID FROM DUAL";
		} else
		return "ERROR_ON_LOOK_UP for PK";
	}
	
	public String provideSequence (Model model) {
		PrimaryKeyPolicyPattern primaryKeyPolicyPattern = getPrimaryKeyPolicyPattern(model);
		return provideSequenceOneGlobal(primaryKeyPolicyPattern);
	}
	
	public static String provideSequenceOneGlobal (PrimaryKeyPolicyPattern primaryKeyPolicyPattern) {
		StringBuffer sb = new StringBuffer();
		if (primaryKeyPolicyPattern.getPrefix()!=null)
			sb.append(primaryKeyPolicyPattern.getPrefix());
		sb.append(primaryKeyPolicyPattern.getSequenceName());
		if (primaryKeyPolicyPattern.getSuffix()!=null)
			sb.append(primaryKeyPolicyPattern.getSuffix());
		return sb.toString();
	}
	
	public static String provideSequence (Table table) {
		PrimaryKeyPolicy primaryKeyPolicy = table.getDatabase().getDataModel().getPrimaryKeyPolicy();
		if (primaryKeyPolicy==null) {
			return "NO LOOK UP for PK";
		}
		PrimaryKeyPolicyPattern primaryKeyPolicyPattern = primaryKeyPolicy.getFirstPrimaryKeyPolicyPattern();
		if (primaryKeyPolicyPattern==null) {
			return "NO LOOK UP for PK : no pattern found";
		}
		if (primaryKeyPolicy.isOneGlobal()) {
			return provideSequenceOneGlobal(primaryKeyPolicyPattern);
		} else if (primaryKeyPolicy.isOneForEachTable()){
			String seq = initSequence(primaryKeyPolicyPattern, table);
			if (primaryKeyPolicyPattern.getPrefix()!=null || primaryKeyPolicyPattern.getSuffix()!=null) {
				if (primaryKeyPolicyPattern.getPrefix()!=null)
					seq = primaryKeyPolicyPattern.getPrefix() + seq;
				if (primaryKeyPolicyPattern.getSuffix()!=null)
				    seq = seq+primaryKeyPolicyPattern.getSuffix();
				return seq;
			} else
				return seq + "_SEQ";
		}
		else
			return table.getName()+"_SEQ";
	}
	
	private static String initSequence(PrimaryKeyPolicyPattern primaryKeyPolicyPattern, Table table) {
		StringBuffer sb = new StringBuffer();
		sb.append(table.getName());
		if (primaryKeyPolicyPattern.isAppendPrimaryKeyName())
			sb.append("_"+TableUtils.getPrimaryKey(table));
		return sb.toString();
	}

	public boolean isPrimayKeyLookUpStringNeeded (Table table) {
		if (TableUtils.getPrimaryKeyType(table).equals("INT") ||
				TableUtils.getPrimaryKeyType(table).equals("INTEGER") ||
				TableUtils.getPrimaryKeyType(table).equals("DECIMAL") ||
				TableUtils.getPrimaryKeyType(table).equals("BIGINT") 
				)
			return true;
		return false;
			
	}
	
	//
	public boolean isPrimaryKeyPolicyOneGlobal(Model model) {
		PrimaryKeyPolicy primaryKeyPolicy = getPrimaryKeyPolicy(model);
		if (primaryKeyPolicy!=null)
			return primaryKeyPolicy.isOneGlobal();
		return false;
	}
	
	public boolean isPrimaryKeyPolicyOneForEachTable(Model model) {
		return !isPrimaryKeyPolicyOneGlobal(model);
	}
	
	private PrimaryKeyPolicy getPrimaryKeyPolicy (Table table) {
		return table.getDatabase().getDataModel().getPrimaryKeyPolicy();
	}
	
	private PrimaryKeyPolicy getPrimaryKeyPolicy (Model model) {
		return model.getDataModel().getPrimaryKeyPolicy();
	}
//	public boolean 
	private PrimaryKeyPolicyPattern getPrimaryKeyPolicyPattern (PrimaryKeyPolicy primaryKeyPolicy) {
		if (primaryKeyPolicy==null) {
			//TODO log should provide a policy pattern
			return null;
		}
		return  primaryKeyPolicy.getFirstPrimaryKeyPolicyPattern();
	}
	
	private PrimaryKeyPolicyPattern getPrimaryKeyPolicyPattern (Model model) {
		PrimaryKeyPolicy primaryKeyPolicy = getPrimaryKeyPolicy(model);
		return  getPrimaryKeyPolicyPattern(primaryKeyPolicy);
	}
	
	private PrimaryKeyPolicyPattern getPrimaryKeyPolicyPattern (Table table) {
		PrimaryKeyPolicy primaryKeyPolicy = getPrimaryKeyPolicy(table);
		return  getPrimaryKeyPolicyPattern(primaryKeyPolicy);
	}
	
	public PrimaryKeyPolicyPatternEnum getPrimaryKeyPolicyPatternEnum (Table table) {
		PrimaryKeyPolicyPattern primaryKeyPolicyPattern = getPrimaryKeyPolicyPattern (table);
		if (primaryKeyPolicyPattern != null) {
			return primaryKeyPolicyPattern.getPrimaryKeyPolicyPatternEnum();
		}
		return PrimaryKeyPolicyPatternEnum.OTHER;
	}
	
	public boolean isPrimaryKeyPolicyIdentity(Table table) {
		return getPrimaryKeyPolicyPatternEnum(table).equals(PrimaryKeyPolicyPatternEnum.IDENTITY);
	}
	
	public boolean isPrimaryKeyPolicySequence(Table table) {
		return getPrimaryKeyPolicyPatternEnum(table).equals(PrimaryKeyPolicyPatternEnum.SEQUENCE);
	}
	
	public boolean isPrimaryKeyPolicyIncrement(Table table) {
		return getPrimaryKeyPolicyPatternEnum(table).equals(PrimaryKeyPolicyPatternEnum.AUTOINCREMENT);
	}
	
}
