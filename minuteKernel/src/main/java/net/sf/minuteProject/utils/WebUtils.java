package net.sf.minuteProject.utils;

import net.sf.minuteProject.configuration.bean.Model;

public class WebUtils {
	
	public static final String SITEMESHMAINLAYOUT_TEMPLATE = "SitemeshMainLayout";
	public static final String GEN_CRUD_SERVLET_TEMPLATE = "GenCRUDServlet";
	public static final String BSLASPRINGCONFIG_TEMPLATE = "BslaSpringConfig";
	public static final String SITEMESH_MENU = "BslaWebMenu";
	
	public String getDefaultRootURLBeginner(Model model) {
		return URLUtils.getDefaultRootURLBeginner();
	}
	
	public static String getSitemeshMainDecorator(Model model) {
		return CommonUtils.getTemplateArtifactName (model, SITEMESHMAINLAYOUT_TEMPLATE);
	}
	
	public static String getSitemeshMainDecoratorDir(Model model) {
		return CommonUtils.getTemplateArtifactDirName (model, SITEMESHMAINLAYOUT_TEMPLATE);
	}
	
	public static String getProjectDefaultServletName(Model model) {
		return CommonUtils.getTemplateArtifactName (model, GEN_CRUD_SERVLET_TEMPLATE);		
	}
	
	public static String getProjectDefaultServletClasspath(Model model) {
		return CommonUtils.getArtifactFullClasspath (model, GEN_CRUD_SERVLET_TEMPLATE);				
	}
	
	public static String getDefaultHomePageContent(Model model) {
		return CommonUtils.getArtifactRelativePathDirAndFullName (model, SITEMESH_MENU);						
	}
	
	public static String getSpringConfigMainInRootClassPath(Model model) {
		return CommonUtils.getArtifactRelativePathDirAndFullName (model, BSLASPRINGCONFIG_TEMPLATE);								
	}
	
}
