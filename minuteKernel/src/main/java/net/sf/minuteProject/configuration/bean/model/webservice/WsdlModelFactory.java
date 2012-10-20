package net.sf.minuteProject.configuration.bean.model.webservice;

import net.sf.minuteProject.configuration.bean.WebServiceModel;
import net.sf.minuteProject.configuration.bean.model.webservice.impl.metro.WsdlModelMetro;

public class WsdlModelFactory {

	private static WsdlModelFactory wsdlModelFactory;
	
	public static WsdlModelFactory getInstance() {
		if (wsdlModelFactory==null) wsdlModelFactory = new WsdlModelFactory();
		return wsdlModelFactory;
	}

	public WsdlModel getWsdlModel(WebServiceModel webServiceModel) {
		return new WsdlModelMetro(webServiceModel);
	}
	
}
