package net.sf.minuteProject.validation;

import net.sf.minuteProject.configuration.bean.GeneratorBean;

public class GeneratorBeanValidator {

	public static boolean isNotNull (GeneratorBean generatorBean) {
		return !isNull(generatorBean);
	}
	
	public static boolean isNull (GeneratorBean generatorBean) {
		return (generatorBean==null)?true:false;
	}	
}
