package net.sf.minuteProject.utils.io;

import java.util.HashMap;
import java.util.Map;

import net.sf.minuteProject.configuration.bean.Template;
import static net.sf.minuteProject.utils.io.UpdatedAreaUtils.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class UpdatedAreaUtilsTest extends TestCase {

	Template template;
	Map<String, String> updatedArea= new HashMap<String, String>();
	
	@Before
	public void setUp() {
		template = new Template();
		template.setFileExtension("java");
		template.setUpdatable(true);
		template.setHasUpdatableNature(true);

	}
	
	private void populateUpdatedAreaWith (String key, String value){
		updatedArea = new HashMap<String, String>();
		updatedArea.put(key, value);
	}
	@Test
	public void testImportUpdatedAreas() {
		
		populateUpdatedAreaWith(IMPORT,"test");
		String importArea = getImportSnippet(template, updatedArea);
		String testValue = getAddedAreaSnippet(template,IMPORT, "test").getContent();
		assertTrue("import should be set to default "+importArea +"\n"+testValue, testValue.equals(importArea));
			
		populateUpdatedAreaWith("dummy","");
		String defaultValue = getAddedAreaSnippet(template,IMPORT, null).getContent();		
		importArea = getImportSnippet(template, updatedArea);
		assertTrue("import should be set to default "+importArea, defaultValue.equals(importArea));
		
		populateUpdatedAreaWith(UpdatedAreaUtils.IMPORT,"");
		importArea = getImportSnippet(template, updatedArea);
		assertTrue("import should be null "+importArea, defaultValue.equals(importArea));
			
	}
	
	
}
