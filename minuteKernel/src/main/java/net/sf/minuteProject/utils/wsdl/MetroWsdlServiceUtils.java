package net.sf.minuteProject.utils.wsdl;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.model.webservice.Service;
import net.sf.minuteProject.configuration.bean.model.webservice.impl.metro.WsdlServiceMetro;

import com.sun.tools.ws.processor.model.Model;

public class MetroWsdlServiceUtils {

	public static List<Service> getServices(Model wsdlModel) {
//		wsdlModel.getJAXBModel().getGeneratedClassNames();
		List<Service> services = new ArrayList<Service>();
		for (com.sun.tools.ws.processor.model.Service service : wsdlModel.getServices())
			services.add(getService(service));
		return services;
	}

	private static Service getService(com.sun.tools.ws.processor.model.Service s) {
//		s.getPorts().get(0).;
		Service service = new WsdlServiceMetro(s);
		return service;
	}

}
