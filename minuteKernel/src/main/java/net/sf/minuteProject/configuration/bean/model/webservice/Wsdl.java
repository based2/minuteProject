package net.sf.minuteProject.configuration.bean.model.webservice;

import org.apache.commons.lang.StringUtils;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.WebServiceModel;

public class Wsdl extends AbstractConfiguration{

	WebServiceModel webServiceModel;
	private String locationUri;
	private String rootdir, dir, file;

	public String getRootdir() {
		return rootdir;
	}

	public void setRootdir(String rootdir) {
		this.rootdir = rootdir;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getLocationUri() {
		return locationUri;
	}

	public void setLocationUri(String locationUri) {
		this.locationUri = locationUri;
	}

	public WebServiceModel getWebServiceModel() {
		return webServiceModel;
	}

	public void setWebServiceModel(WebServiceModel webServiceModel) {
		this.webServiceModel = webServiceModel;
	}

	public String getLocation() {
		if (!StringUtils.isEmpty(locationUri)) return locationUri;
		StringBuffer sb = new StringBuffer();
		if (!StringUtils.isEmpty(rootdir)) sb.append(rootdir+"/");
		if (!StringUtils.isEmpty(dir)) sb.append(dir+"/");
		if (!StringUtils.isEmpty(file)) sb.append(file+"/");
		String s = sb.toString();
		return (!StringUtils.isEmpty(s))?s:null;
	}
	
	
}
