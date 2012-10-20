package net.sf.minuteProject.utils;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.Template;

import net.sf.minuteProject.configuration.bean.view.Function;
import net.sf.minuteProject.configuration.bean.view.Service;
import net.sf.minuteProject.configuration.bean.view.View;

public class ViewUtils {
	
	public static final String DataTransferObjectInputTemplateName = "DataTransferObjectInput";
	public static final String DataTransferObjectOutputTemplateName = "DataTransferObjectOutput";
	public static final String BslaViewDaoInterfaceTemplateName = "BslaViewDaoInterface";
	public static final String BslaViewServiceInterfaceTemplateName = "BslaViewServiceInterface";
	public static final String BslaViewServiceImplementationTemplateName = "BslaViewServiceImpl";

	public static String getInputDtoImport (Function function, Template template) {
		return BslaLibraryUtils.getLevelTemplateFullPath(function, template, "DataTransferObjectInput");
	}

	public static String getOutputDtoImport (Function function, Template template) {
		return BslaLibraryUtils.getLevelTemplateFullPath(function, template, "DataTransferObjectOutput");
	}
	
	public static String getDaoInterfacePackageName (Service service, Template template) {
		return BslaLibraryUtils.getLevelTemplateFullPath(service, template, "BslaViewDaoInterface");		
	}
	
	public static String getInputDtoClassName (Function function, Template template) {
		return BslaLibraryUtils.getTemplateClassName(function, template, "DataTransferObjectInput");
	}

	public static String getOutputDtoClassName (Function function, Template template) {
		return BslaLibraryUtils.getTemplateClassName(function, template, "DataTransferObjectOutput");
	}	

	public static String getDaoInterfaceName (AbstractConfiguration bean, Template template) {
		return CommonUtils.getTemplateClassName(bean, template, "BslaViewDaoInterface");		
	}
	
	public static String getServiceInterfacePackageName (Service service, Template template) {
		return BslaLibraryUtils.getLevelTemplateFullPath(service, template, BslaViewServiceInterfaceTemplateName);		
	}

	public static String getServiceInterfaceName (AbstractConfiguration bean, Template template) {
		return CommonUtils.getTemplateClassName(bean, template, BslaViewServiceInterfaceTemplateName);		
	}

	public static String getServiceImplementationPackageName (AbstractConfiguration bean, Template template) {
		return BslaLibraryUtils.getLevelTemplateFullPath(bean, template, BslaViewServiceImplementationTemplateName);		
	}
	
}
