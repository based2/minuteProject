package net.sf.minuteProject.utils.format;

import java.util.Hashtable;
import java.util.Map;

public class FormatCache {

	private static FormatCache instance;
//	private static Object o__;
	
	private static Map<String, String> map;
	
	private FormatCache() {
	}
	
	public static FormatCache getInstance () {
		if (null==instance) {
		   synchronized (FormatCache.class) {
			   if (null==instance) {
				   instance = new FormatCache();
				   map = new Hashtable<String, String>();
			   }
		   }	
		}
		return instance;
	}
	
//	private static Map<FormatCacheEntry, String> getMap () {
//		if (null==map) {
//		   synchronized (o1__) {
//			   if (null==map) {
//				   map = new Hashtable<FormatCacheEntry, String>();
//			   }
//		   }	
//		}
//		return map;
//	}
	
	private static Map<String, String> getMap () {
		return map;
	}
	
	public String getCacheEntry (FormatCacheEntry fce) {
		return getInstance().getMap().get(fce.toString());
	}
	
	public void putCacheEntryValue (FormatCacheEntry fce, String value) {
		getInstance().getMap().put(fce.toString(), value);
	}
}
