package net.sf.minuteProject.plugin.springframework;

public class SpringSecurityUtils {
	
	public static String getRoles(String roles) {
		StringBuffer sb = new StringBuffer();
		for (String r : roles.split(",")) {
			r = r.trim();
			sb.append("ROLE_"+r.toUpperCase());
		}
		return sb.toString();
	}
}
