package net.sf.minuteProject.utils.format;

import junit.framework.TestCase;
import net.sf.minuteProject.plugin.format.I18nUtils;

public class I18nUtilsTest extends TestCase {

	private static final String nameWithUnderscore = "TEST_TEST";
	private static final String nameWithoutUnderscore = "TESTTEST";
	private static final String test = "Test";
	private static final String testtest = "Testtest";
	
	private static final String ACTIVITY = "activity";
	private static final String ACTIVITIES = "activities";
	private static final String Y = "y";
	private static final String IES = "ies";	
	
	
	public void testPlurialize () {
		String s = I18nUtils.plurialize(ACTIVITY);
		assertTrue(s+ " should be equal to "+ACTIVITIES, s.equals(ACTIVITIES));
		s = I18nUtils.plurialize(Y);
		assertTrue(s+ " should be equal to "+IES, s.equals(IES));
	}
	
	public void testGetI18nFromDBNameStripPrefix () {
		String s = I18nUtils.getI18nFromDBNameStripPrefix(nameWithUnderscore);
		assertTrue("result = "+s+" while input = "+nameWithUnderscore,test.equals(s));
		s = I18nUtils.getI18nFromDBNameStripPrefix(nameWithoutUnderscore);
		assertTrue(nameWithoutUnderscore.equals(s));
		s = I18nUtils.getI18nFromDBNameStripPrefix(null);
	}
	
	
	public void testGetI18nFromDBNameStripSufix () {
		String s = I18nUtils.getI18nFromDBNameStripSufix(nameWithoutUnderscore, true);
		assertTrue("result = "+s+" while input = "+nameWithoutUnderscore, testtest.equals(s));
	}
}
