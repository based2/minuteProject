package net.sf.minuteProject.utils.wsdl;

import org.apache.commons.lang.StringUtils;

import net.sf.minuteProject.configuration.bean.Configuration;
import net.sf.minuteProject.configuration.bean.Model;
import net.sf.minuteProject.configuration.bean.WebServiceModel;
import net.sf.minuteProject.configuration.bean.environment.Environment;
import net.sf.minuteProject.configuration.bean.model.webservice.WsdlModel;

public class WsdlUtils {

//	#set($wsdlDirectory=$wsdlUtils.getWsdlDirectory($model))
//	#set($wsdlFile=$wsdlUtils.getWsdlFile($model))
	static getWsdlDirectory (Model model) {
		model.getWebServiceModel().getWsdl().getDir()
	}
	
	static getWsdlRootDirectory (Model model) {
		model.getWebServiceModel().getWsdl().getRootdir()
	}
	
	static hasWsdlRootDirectory (Model model) {
		def s = model.getWebServiceModel().getWsdl().getRootdir()
		s!=null && !s.equals ("")
	}

	static getWsdlFile (Model model) {
		getWsdlFile model.webServiceModel
	}
	
	static getWsdlFile (WebServiceModel webServiceModel) {
		webServiceModel.wsdl.file
	}

	static getWsdlFileWithoutExtension (WebServiceModel webServiceModel) {
		StringUtils.removeEnd webServiceModel.wsdl.file, ".wsdl"
	}
	
	static getWsdlFileRootDir (WebServiceModel webServiceModel) {
		webServiceModel.wsdl.rootdir
	}

	static getWsdlFileDir (WebServiceModel webServiceModel) {
		StringUtils.replace webServiceModel.wsdl.dir, "\\", "/"
	}
	
	public static Environment getEnvironment(WsdlModel wsdlModel, String environmentName) {
		Configuration configuration = getModel(wsdlModel).getConfiguration()
		configuration.getEnvironmentByName(environmentName)
	}
	public static Model getModel(WsdlModel wsdlModel) {
		wsdlModel.getWebServiceModel().getModel()
	}
}
