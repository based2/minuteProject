package net.sf.minuteProject.utils.lang;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class XmlCharEntityReferencesUtils {

	private static Map<String, String> conversionMap = new HashMap<String, String>();

	static {

		conversionMap.put("&amp;", "&");
	}

	public static String getConvertedValue(String key) {
		return conversionMap.get(key);
	}

	public static Set<String> getKeys() {
		return conversionMap.keySet();
	}

	public static String convertToValidPlainTextFromXml(String input) {
		for (String key : XmlCharEntityReferencesUtils.getKeys()) {
			input = input.replaceAll(key, XmlCharEntityReferencesUtils.getConvertedValue(key));
		}
		return input;
	}

}
