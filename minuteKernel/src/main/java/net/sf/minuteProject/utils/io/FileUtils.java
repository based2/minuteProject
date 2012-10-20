package net.sf.minuteProject.utils.io;

import static net.sf.minuteProject.utils.io.UpdatedAreaUtils.MP_MANAGED_ADDED_AREA_BEGINNING;
import static net.sf.minuteProject.utils.io.UpdatedAreaUtils.MP_MANAGED_ADDED_AREA_ENDING;
import static net.sf.minuteProject.utils.io.UpdatedAreaUtils.MP_MANAGED_REFERENCE_MARKER;
import static net.sf.minuteProject.utils.io.UpdatedAreaUtils.MP_MANAGED_STOP_GENERATING;
import static net.sf.minuteProject.utils.io.UpdatedAreaUtils.MP_MANAGED_UPDATABLE_BEGINNING_ENABLE;
import static net.sf.minuteProject.utils.io.UpdatedAreaUtils.MP_MANAGED_UPDATABLE_ENDING;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import net.sf.minuteProject.configuration.bean.system.Property;
import net.sf.minuteProject.loader.implicitstructure.node.Line;
import net.sf.minuteProject.loader.implicitstructure.node.Lines;

import org.apache.commons.lang.StringUtils;

public class FileUtils {

	private enum ModificationType {
		ADDED, UPDATED
	};

	public static String getAbsoluteDir(String relDir, String defDir,
			String targetDir) {
		if (relDir == null)
			relDir = defDir;
		return FileUtils.getAbsolutePathFromPath(relDir, targetDir);
	}

	public static String getFileFullPathFromFileInRootClassPath(
			String filePathInClassPath) {
		URL url = Thread.currentThread().getContextClassLoader()
				.getResource(filePathInClassPath);
		if (url == null)
			return filePathInClassPath;// "RESOURCE NOT IN THE PATH";
		else {
			try {
				return new File(url.toURI()).getAbsolutePath();
			} catch (URISyntaxException e) {
				e.printStackTrace();
				return filePathInClassPath;
			}
		}
	}

	public static String getAbsolutePathFromPath(String inputPathInFile,
			String inputPathFromFile) {
		String inputAbsolutePath = getAbsolutePathFromPath(inputPathInFile);
		if (inputAbsolutePath != null)
			return inputAbsolutePath;
		return getAbsolutePathFromPath(resolvePath(inputPathFromFile,
				inputPathInFile));
	}

	public static String getAbsolutePathFromPath(String inputPathInFile) {
		if (inputPathInFile != null) {
			File file = new File(inputPathInFile);
			if (file.exists())
				try {
					return file.getCanonicalPath();
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
		}
		return null;
	}

	public static String getRoot() {
		return getFileFullPathFromFileInRootClassPath(".");
	}

	public static String getFileFullPathFromFileInClassPath(
			String filePathInClassPath) {
		return getFileFullPathFromFileInRootClassPath(filePathInClassPath);
	}

	public static String getFileFullPath(String dir, String filename) {
		return getFileFullPath(null, dir, filename);
	}

	public static String getFileFullPath(String triggerFileInClassPath,
			String dir, String filename) {
		String rootPath;
		if (dir != null)
			return rootPath = dir;
		else {
			if (triggerFileInClassPath == null)
				rootPath = getRoot();
			else {
				String context = getFileFullPathFromFileInClassPath(triggerFileInClassPath);
				rootPath = stripFileName(context);
			}
		}
		return resolvePath(rootPath, filename);
	}

	private static String getMinuteProjectHome() {
		return System.getenv("MP_HOME");
	}

	public static String stripFileName(String filename) {
		// return getAbsolutePathFromPath(filename);
		filename = StringUtils.replace(filename, "\\", "/");
		int i = StringUtils.lastIndexOf(filename, "/");
		// int i = StringUtils.lastIndexOf(filename, "/");
		return StringUtils.substring(filename, 0, i);
	}

	public static String stripRelativePath(String filename) {
		int i = StringUtils.lastIndexOf(filename, "\\");
		return StringUtils.substring(filename, i);
	}

	public static String resolvePath(String root, String relativePath) {
		return root + "/" + relativePath;
	}

	public static Map<String, String> getAddedArea(File file) {
		Map<String, String> areas = new HashMap<String, String>();
		String strLine;
		try {
			// Open the file that is the first
			// command line parameter
			FileInputStream fstream = new FileInputStream(file);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			// skip first line
//			strLine = br.readLine();
			boolean isWithinAddedArea = false;
			String key = null;
			StringBuffer sb = null;
			int cpt = 0;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				// Print the content on the console
				if (artifactIsNotPartOfTheGeneration(strLine)) {
					areas = new HashMap<String, String>();
					areas.put(MP_MANAGED_STOP_GENERATING,
							MP_MANAGED_STOP_GENERATING);
					return areas;
				}
				if (!isWithinAddedArea) {
					cpt = 0;
					if (isBeginning(strLine)) {
						isWithinAddedArea = true;
						key = getKey(strLine);
						if (key != null)
							sb = new StringBuffer();
					}
				} else {
					if (isEnding(strLine)) {
						isWithinAddedArea = false;
						if (key != null && sb.length() > 0) {
							// sb.append(strLine+"\n");
							// sb.append(strLine);
							areas.put(key, sb.toString());
						}
						key = null;
					}
				}
				if (isWithinAddedArea) {
					cpt++;
					if (strLine != null && sb != null) {
						if (cpt > 1) {
							if (cpt > 2)
								// prepend CRLF from third line on
								sb.append("\n");
							// first line not taken since directive start with
							// second
							sb.append(strLine);
						}
					}
				}
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			e.printStackTrace();
			System.err.println("Error in retrieving updatable areas: "
					+ e.getMessage());
		}
		return areas;
	}

	private static boolean artifactIsNotPartOfTheGeneration(String strLine) {
		return StringUtils.contains(strLine, MP_MANAGED_STOP_GENERATING);
	}

	private static String getKey(String strLine) {
		// if (isMpManagedAdded(strLine))
		return StringUtils.substringBetween(strLine,
				MP_MANAGED_REFERENCE_MARKER, MP_MANAGED_REFERENCE_MARKER);
		// else {//isMpManagedUpdatable
		// StringUtils.
		// }
	}

	private static boolean isMpManagedAdded(String strLine) {
		return StringUtils.contains(strLine, MP_MANAGED_ADDED_AREA_BEGINNING);
	}

	private static boolean isEnding(String strLine) {
		return contains(strLine, MP_MANAGED_ADDED_AREA_ENDING)
				|| contains(strLine, MP_MANAGED_UPDATABLE_ENDING);
	}

	private static boolean isBeginning(String strLine) {
		return contains(strLine, MP_MANAGED_ADDED_AREA_BEGINNING)
				|| contains(strLine, MP_MANAGED_UPDATABLE_BEGINNING_ENABLE);
	}

	private static boolean contains(String strLine, String search) {
		return StringUtils.contains(strLine, search);
	}

	public String readFirstLine(File file) {
		String strLine = "";
		if (file.exists()) {
			try {
				// Open the file that is the first
				// command line parameter
				FileInputStream fstream = new FileInputStream(file);
				// Get the object of DataInputStream
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(
						new InputStreamReader(in));
				strLine = br.readLine();
				in.close();
			} catch (Exception e) {// Catch exception if any
				System.err.println("Error: " + e.getMessage());
			}
		}
		return strLine;
	}

	private Lines parseLines(File file, String separator,
			List<Property> properties) {
		Lines lines = new Lines();
		String strLine;
		try {
			// Open the file that is the first
			// command line parameter
			FileInputStream fstream = new FileInputStream(file);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			// skip first line
			strLine = br.readLine();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				// Print the content on the console
				lines.addLine(parseLine(strLine, separator, properties));
				// System.out.println (strLine);
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		return lines;
	}

	private Line parseLine(String strLine, String separator,
			List<Property> properties) {
		Line line = new Line();
		StringTokenizer st = new StringTokenizer(strLine, separator);
		int size = properties.size();
		int i = 0;
		while (st.hasMoreElements()) {
			if (i < size) {
				String token = st.nextToken();
				Property prop = properties.get(i);
				Property property = new Property();
				property.setName(prop.getName());
				property.setValue(token);
				line.addProperty(property);
				i++;
			}
		}
		return line;
	}

	public Lines getLines(File file, String separator) {
		// Lines lines = new Lines();
		String definition = readFirstLine(file);
		List<Property> properties = getProperties(definition, separator);
		// lines.addLine(line);
		return parseLines(file, separator, properties);
	}

	private List<Property> getProperties(String definition, String separator) {
		List<Property> properties = new ArrayList<Property>();
		StringTokenizer st = new StringTokenizer(definition, separator);
		while (st.hasMoreElements()) {
			String token = st.nextToken();
			Property property = new Property();
			property.setName(token);
			properties.add(property);
		}
		return properties;
	}
}
