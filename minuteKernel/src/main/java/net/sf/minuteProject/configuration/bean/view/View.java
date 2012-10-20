package net.sf.minuteProject.configuration.bean.view;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationRoot;
import net.sf.minuteProject.configuration.bean.Template;

/**
 * @author Florian Adler
 *
 */
public class View extends AbstractConfigurationRoot{

	private List<Service> services;

	public List<Service> getServices() {
		if (services==null)
			setServices(new ArrayList<Service>());
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public void addService (Service service) {
		service.setView(this);
		getServices().add(service);
	}
	
	public String getTechnicalPackage(Template template) {
		StringBuffer sb = new StringBuffer(template.getPackageRoot());
		sb.append("."+getProjectname());
		sb.append("."+template.getTechnicalPackage());
		sb.append("."+getName());
		return sb.toString();	
	}	
	
}
