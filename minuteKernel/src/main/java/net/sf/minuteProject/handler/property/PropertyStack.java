package net.sf.minuteProject.handler.property;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

public class PropertyStack {
	Properties properties;
	
	class Prop {
		String name, value;	
	}
	
	abstract class Chunk {
		Chunk(String value) {
			this.value = value;
		}
		String value;
		public abstract String getValue();
		public abstract int getLength();
	}
	
	class AbsoluteChunk extends Chunk {
		AbsoluteChunk(String value) {
			super(value);
		}

		@Override
		public String getValue() {
			return value;
		}

		@Override
		public int getLength() {
			return getValue().length();
		}
	}
	
	class RelativeChunk extends Chunk {
		RelativeChunk(String value) {
			super(value);
		}
		@Override
		public String getValue() {
			return getProperties().getProperty(value);
		}

		@Override
		public int getLength() {
			return value.length()+3; // 3 for ${ and }
		}
		
	}
	
	PropertyStack() {
		
	}

	public Properties getProperties() {
		if (properties == null)
			properties = new Properties();
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public void addProperty(String name, String value) {
		getProperties().setProperty(name, resolvePropertyValue(value));
	}
	
	public String resolvePropertyValue (String value) {
		List<Chunk> props = parseValue(value);
		return resolvePropertyValue (props);
	}
	
	private String resolvePropertyValue (List<Chunk> chunks) {
		StringBuffer stringBuffer = new StringBuffer();
		for (Chunk chunk : chunks) {
			stringBuffer.append(chunk.getValue());
		}		
		return stringBuffer.toString();
	}
	
	/**
	 * parse the input string into Substring called chunks.
	 * Each chunk is of where absolute (containing no reference) or relative (containing reference)
	 * a reference is between ${ and } token
	 * @param value
	 * @return
	 */
	List<Chunk> parseValue (String value) {
		// search for ${
		// 	take string before : if not null => absolutechunk
		//  search for }
		//   if no => abolutechunk
		//   if yes =>  relative chunk
		// to do until string completely parsed
		List<Chunk> result = new ArrayList<Chunk>();
		int length = value.length();
		int posix = 0;
		while (posix < length) {
			Chunk chunk = searchNextChunk(StringUtils.substring(value, posix, length));
			if (chunk.getLength()!=0) {
				result.add(chunk);
			}
			posix = posix + chunk.getLength();
		}
		return result;
	}
	
	private Chunk searchNextChunk (String rawString) {
		Chunk chunk;
		String beforeRelativeSeparator = StringUtils.substringBefore(rawString, "${");
		if (beforeRelativeSeparator!=null && !beforeRelativeSeparator.equals(""))
			chunk = new AbsoluteChunk(beforeRelativeSeparator);
		else {// we found a "${" token => search the end "}" 
			rawString = StringUtils.substringAfter(rawString, "${"); // to position after separator
			String betweenRelativeSeparator = StringUtils.substringBefore(rawString, "}");
			if (rawString.length() == betweenRelativeSeparator.length()) // no ending token !!
				chunk = new AbsoluteChunk(betweenRelativeSeparator);
			else
				chunk = new RelativeChunk(betweenRelativeSeparator);
		}
		return chunk;
	}
	
	
}
