package net.sf.minuteProject.utils.print;

import net.sf.minuteProject.application.Generator;

public class PrintUtils {
	
	public static String print(String text, String value) {
		System.out.println(">>>>>>> "+text+" - "+value);
		return text;
	}
	
	public static String print(String text, Generator bean) {
		System.out.println(">>>>>>> "+text+" - "+bean);
		return text;
	}
}
