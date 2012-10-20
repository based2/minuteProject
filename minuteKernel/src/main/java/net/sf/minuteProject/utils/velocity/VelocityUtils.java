package net.sf.minuteProject.utils.velocity;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import net.sf.minuteProject.configuration.bean.Target;
import net.sf.minuteProject.configuration.bean.TemplateTarget;
import net.sf.minuteProject.loader.catalog.technologycatalog.node.Technology;
import net.sf.minuteProject.loader.catalog.technologycatalog.node.TechnologyCatalog;

public class VelocityUtils {

//	public static String getDistinctVelocityDirectory (TechnologyCatalog technologyCatalog) {
//		List<String> list = new ArrayList<String>();
//		StringBuffer sb = new StringBuffer();
//		for (Technology technology : technologyCatalog.getTechnologies().getTechnologys()) {
//			list.add(getDistinctVelocityDirectory(technology));
//		}
//		for (String string : list) {
//			sb.append(string+",");
//		}	
//		return sb.toString();
//	}
//
//	private static String getDistinctVelocityDirectory(Technology technology) {
//		return technology.getTemplateDir();
//	}
//	
//	public static String getDistinctVelocityLib(List<Target> list) {
//		StringBuffer sb = new StringBuffer();
//		Hashtable<String, String> ht = new Hashtable<String, String>();
//		for (Target target : list) {
//			for (TemplateTarget templateTarget : target.getTemplateTargets()) {
//				ht.put(templateTarget.getLibdir(), templateTarget.getLibdir());
//			}
//		}
//		Enumeration<String> e = ht.elements();
//		while (e.hasMoreElements())
//			sb.append((String)e.nextElement()+",");
//		return sb.toString();
//	}
	
}
