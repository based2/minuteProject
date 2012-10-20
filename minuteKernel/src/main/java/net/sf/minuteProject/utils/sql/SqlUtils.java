package net.sf.minuteProject.utils.sql;

public class SqlUtils {

	public static final String [] reservedWord =
		new String [] {
		"state",
		"comment"};

	public static boolean isReservedWord(String word) {
		if (word==null)
			return false;
		for (int i = 0; i < reservedWord.length; i++) {
			if (word.equals(reservedWord[i]))
				return true;
		}
		return false;
	}
}
	
	
