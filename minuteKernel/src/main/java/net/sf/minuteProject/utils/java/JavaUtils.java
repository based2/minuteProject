package net.sf.minuteProject.utils.java;

public class JavaUtils {

	public static final String [] reservedWord =
		new String [] {
		"abstract",
		"continue",
		"for",
		"new",
		"switch",
		"assert",
		"default",
		"goto",
		"package",
		"synchronized",
		"boolean",
		"do",
		"if",
		"private",
		"this",
		"break",
		"double",
		"implements",
		"protected",
		"throw",
		"byte",
		"else",
		"import",
		"public",
		"throws",
		"case",
		"enum",
		"instanceof",
		"return",
		"transient",
		"catch",
		"extends",
		"int",
		"short",
		"try",
		"char",
		"final",
		"interface",
		"static",
		"void",
		"class",
		"finally",
		"long",
		"strictfp",
		"volatile",
		"const",
		"float",
		"native",
		"super", 
		"while" };

	public static boolean isReservedWord(String word) {
		if (word==null)
			return false;
		for (int i = 0; i < reservedWord.length; i++) {
			if (word.equals(reservedWord[i]))
				return true;
		}
		return false;
	}
	
	public static String getJavaVariableNaming (String javaVar) {
		return (isReservedWord(javaVar))?javaVar+"Name":javaVar;
	}
	
	public static String getJavaClassNaming (String javaClass) {
		if (javaClass==null) return "ERROR_JAVA_CLASS_IS_NULL";
		return (isReservedWord(javaClass.toLowerCase()))?javaClass+"Name":javaClass;
	}
	
}
	
	
