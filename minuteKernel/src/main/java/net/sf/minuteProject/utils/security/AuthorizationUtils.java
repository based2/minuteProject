package net.sf.minuteProject.utils.security;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import net.sf.minuteProject.configuration.bean.Model;
import net.sf.minuteProject.configuration.bean.Package;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.model.data.View;
import net.sf.minuteProject.configuration.bean.presentation.Presentation;
import net.sf.minuteProject.configuration.bean.presentation.PresentationEntities;
import net.sf.minuteProject.configuration.bean.presentation.PresentationEntity;

public class AuthorizationUtils {

	/**
	 * returns null or blank if access is for all
	 * otherwise the user roles define in presentationEntity
	 */
	public static String getPresentationEntityAccessRoles (String name, Model model) {
		PresentationEntity presentationEntity = getPresentationEntity(name, model);
		if (presentationEntity!=null) {
			return presentationEntity.getRoles();
		}
		return null;
	}
	
	public static PresentationEntity getPresentationEntity (String name, Model model) {
		Presentation presentation = model.getBusinessModel().getPresentation();
		if (presentation!=null) {
			for (PresentationEntity presentationEntity : presentation.getPresentationEntities().getPresentationEntitys()) {
				if (presentationEntity.getName().equals(name))
					return presentationEntity;
			}
		}
		return null;
	}

	public static String getPresentationEntityPackageAccessRoles (Package pack) {
		Model model = pack.getBusinessPackage().getBusinessModel().getModel();
		Map<String, String> map = new Hashtable<String, String>();
		List<Table> tables = pack.getListOfTables();
		
		if (getPresentationEntityPackageAccessRoles(tables, map, model)==null)
			return null;
		
		List views = pack.getListOfViews();
		String roles = getPresentationEntityPackageAccessRoles(views, map, model);
		if (roles==null)
			return null;
		
		return roles;

	}
	
	public static String getPresentationEntityPackageAccessRoles (List<Table> tables,Map<String, String> map, Model model) {
		for (Table table : tables) {
			String accessRoles = getPresentationEntityAccessRoles(table.getName(), model);
			if (accessRoles==null)
				return null; // access to package is open to any role, if one table within the package is open to anyrole
			String [] roles = StringUtils.split(accessRoles, ",");
			for (int i = 0; i < roles.length; i++) {
				map.put(roles[i], roles[i]);
			}
		}
		StringBuffer sb = new StringBuffer();
		for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
			String role = (String) iter.next();
			sb.append(role);
			if (iter.hasNext())
				sb.append(",");
		}
		return sb.toString();
	}
	
	public static boolean hasSameColor (Table t1, Table t2){
		return (getColor(t1).equals(getColor(t2)))?true:false;
	}
	
	public static String getColor (Table table) {
		// to change to allow multiple colors
		if (table!=null 
				&& table.getPackage()!=null 
				&& table.getPackage().getSecurityColor()!=null
				&& table.getPackage().getSecurityColor().getRoles()!=null)
			return table.getPackage().getSecurityColor().getRoles();
		return "";
	}
}
