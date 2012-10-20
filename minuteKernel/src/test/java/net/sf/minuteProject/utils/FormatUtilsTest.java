package net.sf.minuteProject.utils;

import junit.framework.TestCase;

public class FormatUtilsTest extends TestCase {

	private String name1 = "this is a test";
	private String shortNameName1 = "ThisIsATest";

	private String name2 = "this-is another-test";
	private String shortNameName2 = "ThisIsAnotherTest";

	private String expressionToTrim1 = "____bbbbbbbasdasd____dasdsa___daddddddsd__";

	public void testFirstUpperCaseOnly () {
		String s = FormatUtils.firstUpperCaseOnly("s");
		assertTrue ("S".equals(s));
		s = FormatUtils.firstUpperCaseOnly("PRODUCTID");
		assertTrue ("Productid".equals(s));
	}	

	public void testGetShortNameFromVerbose() {
		String resultName1 = FormatUtils.getShortNameFromVerbose(name1);
		assertTrue(resultName1, resultName1.equals(shortNameName1));

		String resultName2 = FormatUtils.getShortNameFromVerbose(name2);
		assertTrue(resultName2, resultName2.equals(shortNameName2));
	}

	public void testGetEachWordFirstLetterUpper() {
		String resultName1 = FormatUtils.getShortNameFromVerbose(name1);
		assertTrue(resultName1, resultName1.equals(shortNameName1));
	}

	public void testEliminateMultipleSequenceOfChar() {
		String result = FormatUtils.eliminateMultipleSequenceOfChar(expressionToTrim1, '_', 'b', 'd');
		assertTrue(result.equals("_basdasd_dasdsa_dadsd_"));
		result = FormatUtils.trimExpression(result, "_");
		assertTrue(result.equals("basdasd_dasdsa_dadsd"));
	}


}
