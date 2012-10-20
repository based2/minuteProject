package net.sf.minuteProject.utils.code;

import junit.framework.TestCase;

public class RestrictedCodeUtilsTest extends TestCase {

	public static final String EXPECTED_RESULT_FINAL_CONVERSION = "Narrative_Pa_rt_s_and_A_nnexes_wro_ng_def_i_ned";                                                                	
	
	public static final String INPUT_VALUE1 = "Narrative#$,,,.... Pa.rt.s #$and A^^&^$##@*&^nnexes_wro,,ng,,def,.i,.!@!#$#@ned.,  ";
			
	public void testConvertToValidJava() {
		System.out.println(RestrictedCodeUtils.convertToValidJava(INPUT_VALUE1));
		assertEquals(RestrictedCodeUtils.convertToValidJava(INPUT_VALUE1),
				EXPECTED_RESULT_FINAL_CONVERSION);
	}
	
	public void testConvertToValidJavaWithUpperCase() {
		assertEquals(RestrictedCodeUtils.convertToValidJavaWithUpperCase(INPUT_VALUE1),
				EXPECTED_RESULT_FINAL_CONVERSION.toUpperCase());
	}
						

}
