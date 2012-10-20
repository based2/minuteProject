package net.sf.minuteProject.utils.format;

public class FormatCacheEntry {

	private String input, converter;

	public FormatCacheEntry(String input, String converter) {
		super();
		this.input = input;
		this.converter = converter;
	}

	public String getInput() {
		return input;
	}

	public String getConverter() {
		return converter;
	}
	
	@Override
	public String toString() {
		return input+"-"+converter;
	}
}
