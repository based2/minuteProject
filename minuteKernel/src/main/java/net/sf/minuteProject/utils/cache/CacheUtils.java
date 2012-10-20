package net.sf.minuteProject.utils.cache;

import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.utils.TableUtils;

public class CacheUtils {

	public static final String READ_ONLY  = "read-only";
	public static final String READ_WRITE  = "read-write";
	
	public boolean hasCache (Table table) {
		return hasCacheDefault(table);
	}
	
	public String getCacheStrategy (Table table) {
		return getCacheStrategyDefault(table);
	}
	
	private boolean hasCacheDefault (Table table) {
		if (TableUtils.isReferenceDataContentType(table) || TableUtils.isPseudoStaticDataContentType(table))
			return true;
		return false;
	}
	
	private String getCacheStrategyDefault (Table table) {
		if (TableUtils.isReferenceDataContentType(table))
			return READ_ONLY;
		if (TableUtils.isPseudoStaticDataContentType(table))
			return READ_WRITE;
		return null;
	}
}
