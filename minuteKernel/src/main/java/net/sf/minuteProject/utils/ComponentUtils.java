package net.sf.minuteProject.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;

import net.sf.minuteProject.configuration.bean.Package;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Component;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.model.data.impl.DDLUtils.ColumnDDLUtils;
import net.sf.minuteProject.configuration.bean.model.data.impl.DDLUtils.ComponentDDLUtils;

public class ComponentUtils {

	public static List<Component> getComponent (Table table) {
		//for each column
		Hashtable<String, Component> components = composeComponentList(table);
		return complementComponentListWithRelationship(components);
		//return new ArrayList<Component>(components.values());
	}
	
	private static Hashtable<String, Component> composeComponentList (Table table) {
		Package pack = copyPackage(table);
		Hashtable<String, Component> components = new Hashtable<String, Component>();
		for (Column column : table.getColumns()) {
			String key = getComponentBeanPath(table.getName(), column.getName());
			String fullPath = getComponentBeanPropertyPath(table.getName(), column.getName());
			Component component = components.get(key);
			if (component==null) {
				// to set in create New component
				component = new ComponentDDLUtils();
				component.setPath(key);
				component.setName(getBeforeLastInPathElement(fullPath));
				//Package pack = table.getPackage();
				//pack.setName(pack.getName()+StringUtils.lowerCase(FormatUtils.getJavaName(table.getName())));
				component.setPackage(pack);
				component.setTable (table);
			}
			column.setName(getLastInPathElement(fullPath));
			component.addColumn(column);
			components.put(key, component);
		}
		return components;
	}
	
	//TODO put in packageUtils
	private static Package copyPackage (Table table) {
		Package pack = new Package();
		Package packOrig = table.getPackage();
		String formatAlias = StringUtils.lowerCase(FormatUtils.getJavaName(table.getAlias()));
		pack.setName(packOrig.getName()+"."+formatAlias);
		pack.setBusinessPackage(packOrig.getBusinessPackage());
		return pack;
	}
	
	private static List<Component> complementComponentListWithRelationship(Hashtable<String, Component> input) {
		input = setAllParentComponent(input);
		input = getSetAllParentChildRelationship(input);
		return new ArrayList<Component>(input.values());
	}
	
	private static Hashtable<String, Component> setAllParentComponent (Hashtable<String, Component> input) {
		// foreach component get beanpath
		Hashtable<String, Component> output = new Hashtable<String, Component>(input);
		for (Component component : input.values()) {
			String path = component.getPath();
			StringTokenizer st = new StringTokenizer(path,".");
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < st.countTokens(); i++) {
				String componentName = (String)st.nextToken();
				sb.append(componentName);
				String currentPath = sb.toString();
				Component component2 = (Component)input.get(currentPath);
				if (component2==null) {	
					component2 = createNewComponent(componentName,currentPath, component);
					output.put(currentPath, component2);
				}		
			}
			if (st.hasMoreElements())
				sb.append(".");
		}
		return output;
	}
	
	private static Component createNewComponent(String componentName, String path, Component component) {
		Component componentNew = new ComponentDDLUtils();
		componentNew.setPath(path);
		componentNew.setName(componentName);
		//Table table = component.getTable();
		Package pack = component.getPackage();
		//pack.setName(pack.getName()+StringUtils.lowerCase(FormatUtils.getJavaName(table.getName())));
		componentNew.setPackage(pack);
		componentNew.setTable(component.getTable());
		return componentNew;
		
	}

	private static Hashtable<String, Component> getSetAllParentChildRelationship (Hashtable<String, Component> input) {
		for (Component component : input.values()) {
			String path = component.getPath();
			String upPath = getPathBeforeLastInPathElement(path);
			Component parent = (Component)input.get(upPath);
			if (!upPath.equals("")) {
				if (parent==null) {
					System.out.println("Error in composition of the component tree");
				} else {
					component.setParent(parent);
					parent.addChild(component);
				}			
			}
		}		
		return input;
	}

	private static String getComponentBeanPath(String root, String name) {
		return getComponentBeanPath(getComponentBeanPropertyPath(root, name));
	}
	
	private static String getComponentBeanPath (String beanPropertyPath) {
		return getPathBeforeLastInPathElement(beanPropertyPath);
	}
	
	private static String getComponentBeanPropertyPath(String root, String name) {
		return getComponentFullPath(root, name);
	}
	private static String getComponentFullPath(String root, String name) {
		return FormatUtils.getJavaName(root)+"."+getComponentPath(name);
	}
	
	private static String getComponentPath(String name) {
		//get component then field
		StringBuffer result = new StringBuffer();
		StringTokenizer st = new StringTokenizer(name,"__");
		boolean first=false;
		while (st.hasMoreTokens()) {
			if (first==false) 
				first = true;
			else 
				result.append(".");
			result.append (FormatUtils.getJavaName(st.nextToken()));
		}
		return result.toString();
	}

	private static String getElementInPathAtPosixFromEnd(String path, int posix) {
		StringTokenizer st = new StringTokenizer(path,".");
		int cpt = st.countTokens();
//		if (cpt==1)
//			return root;
		int i = cpt-posix;
		for (int j = 0; j < i; j++) {
			st.nextToken();
		}
		return (String)st.nextToken();		
	}
	
	private static String getLastInPathElement(String path) {
//		StringTokenizer st = new StringTokenizer(path,".");
//		int cpt = st.countTokens();
////		if (cpt==1)
////			return root;
//		int i = cpt-1;
//		for (int j = 0; j < i; j++) {
//			st.nextToken();
//		}
		return getElementInPathAtPosixFromEnd(path,1);
	}
	
	private static String getBeforeLastInPathElement(String path) {
		return getElementInPathAtPosixFromEnd(path,2);
//		StringTokenizer st = new StringTokenizer(path,".");
//		int cpt = st.countTokens();
////		if (cpt==1)
////			return root;
//		int i = cpt-2;
//		for (int j = 0; j < i; j++) {
//			st.nextToken();
//		}
//		return (String)st.nextToken();
	}
	
	private static String getPathBeforeLastInPathElement (String path) {
		StringTokenizer st = new StringTokenizer(path,".");
		StringBuffer sb = new StringBuffer();
		int cpt = st.countTokens();
//		if (cpt==1)
//			return root;
		int i = cpt-1;
		for (int j = 0; j < i; j++) {
			String token = st.nextToken();
			sb.append(token);
			if (j!=i-1)
				sb.append(".");
		}
		return sb.toString();
//		String last = getLastInPathElement(path);
//		path = StringUtils.substringBefore(path, last);
//		return 
	}	
}
