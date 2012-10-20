package net.sf.minuteProject.utils.security;

import net.sf.minuteProject.configuration.bean.enrichment.security.EntitySecuredAccess;
import net.sf.minuteProject.configuration.bean.model.data.Table;

public class EntitySecurityUtils {

	public static boolean isEntityRoleSecured(EntitySecuredAccess esa) {
		return (esa!=null && esa.getRoles()!=null)?true:false;
	}
	
	public static String getEntitySecuredRoles(EntitySecuredAccess esa) {
		return (esa!=null)?esa.getRoles():null;
	}	

	public static boolean isEntityRoleSecured(net.sf.minuteProject.configuration.bean.Package pack) {
		return isEntityRoleSecured(pack.getSecurityColor());
	}
	
	public static String getEntitySecuredRoles(net.sf.minuteProject.configuration.bean.Package pack) {
		return getEntitySecuredRoles(pack.getSecurityColor());
	}	
	
	public static boolean isEntityRoleSecured(Table table) {
		return isEntityRoleSecured(table.getEntitySecuredAccess());
	}
	
	public static String getEntitySecuredRoles(Table table) {
		return getEntitySecuredRoles(table.getEntitySecuredAccess());
	}	
}
