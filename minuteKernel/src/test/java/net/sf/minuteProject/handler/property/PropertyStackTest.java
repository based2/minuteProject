package net.sf.minuteProject.handler.property;


import java.util.Properties;

import junit.framework.Assert;
import net.sf.minuteProject.handler.property.PropertyStack;

import org.junit.Before;
import org.junit.Test;

public class PropertyStackTest {


	public static final String sample = "sample";
	public static final String sampleValue = "B${bonsoirValue}XX${bonjourValue}Z";
	public static final String sampleCorrectResult = "BBonSOIRXXBonjourZ";
	
	public static final String bonjour = "bonjourValue";
	public static final String bonjourResult = "Bonjour";
	public static final String bonsoir = "bonsoirValue";
	public static final String bonsoirResult = "BonSOIR";
	
	PropertyStack propertyStack;
	@Before
	public void setUp() throws Exception {
		propertyStack = new PropertyStack();
		Properties properties = new Properties();
		properties.setProperty("helloValue", "Hello");
		properties.setProperty("bonjourValue", "Bonjour");
		properties.setProperty("bonsoirValue", "BonSOIR");
		propertyStack.setProperties(properties);
	}
	@Test
	public void testPropertyStackParser () {
		String input = "test${helloValue}";
		String correctResult = "testHello";
		String result = propertyStack.resolvePropertyValue (input);
		Assert.assertTrue (result.equals(correctResult));
		testPropertyStackParser("${helloValue}${bonjourValue}", "HelloBonjour");
		testPropertyStackParser("B${bonsoirValue}XX${bonjourValue}Z", "BBonSOIRXXBonjourZ");
	}

	public void testPropertyStackParser (String input, String correctResult) {
		String result = propertyStack.resolvePropertyValue (input);
		Assert.assertTrue (result.equals(correctResult));
	}
	
	@Test
	public void testAddProperty () {
		propertyStack.addProperty(sample, sampleValue);
		String result = propertyStack.getProperties().getProperty(sample);
		Assert.assertTrue(result.equals(sampleCorrectResult));
	}
	
}
