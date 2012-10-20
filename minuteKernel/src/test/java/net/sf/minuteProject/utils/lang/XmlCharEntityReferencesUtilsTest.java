package net.sf.minuteProject.utils.lang;

import junit.framework.TestCase;

public class XmlCharEntityReferencesUtilsTest extends TestCase {

	public static final String EXPECTED_RESULT_FINAL_CONVERSION = "Narrative Parts & Annexes";

	public static final String INPUT_VALUE1 = "Narrative Parts &amp; Annexes";

	public void testConvertXmlCharEntityReferencesUtils() {
		assertTrue(XmlCharEntityReferencesUtils.convertToValidPlainTextFromXml(INPUT_VALUE1).equals(EXPECTED_RESULT_FINAL_CONVERSION));
	}

}
