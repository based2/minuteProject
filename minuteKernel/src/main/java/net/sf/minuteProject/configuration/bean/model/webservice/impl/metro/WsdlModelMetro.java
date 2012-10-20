package net.sf.minuteProject.configuration.bean.model.webservice.impl.metro;

import java.io.File;
import java.util.List;

import net.sf.minuteProject.configuration.bean.WebServiceModel;
import net.sf.minuteProject.configuration.bean.model.webservice.EntityModel;
import net.sf.minuteProject.configuration.bean.model.webservice.Service;
import net.sf.minuteProject.configuration.bean.model.webservice.WsdlModel;
import net.sf.minuteProject.utils.wsdl.MetroWsdlServiceUtils;

import com.sun.tools.ws.processor.model.Model;
import com.sun.tools.ws.processor.modeler.wsdl.WSDLModeler;
import com.sun.tools.ws.wscompile.ErrorReceiverFilter;
import com.sun.tools.ws.wscompile.WsimportOptions;

public class WsdlModelMetro extends WsdlObjectMetro implements WsdlModel {

	private WebServiceModel webServiceModel;
	private Model wsdlModel;
	private EntityModel entityModel;
	private List<Service> services;
	private net.sf.minuteProject.configuration.bean.Model model;
	
	public WsdlModelMetro(WebServiceModel webServiceModel) {
		this.webServiceModel = webServiceModel;
		ErrorReceiverFilter receiver = new ErrorReceiverFilter();
		WsimportOptions options = new WsimportOptions();
		String location = webServiceModel.getWsdlLocation();
		File file = (location==null)?new File(""):new File(location);
		options.addWSDL(file);
		/*WSDLModeler wsdlModeler = new WSDLModeler(options, receiver);
		wsdlModel = wsdlModeler.buildModel();
		model = webServiceModel.getModel();
		name=webServiceModel.getModel().getName(); */
        name="missing maven dependency for WSDLModeler";
	}

	@Override
	public List<Service> getServices() {
		if (services==null) 
			services = initServices();
		return services;
	}

	private List<Service> initServices() {
		return MetroWsdlServiceUtils.getServices(wsdlModel);
	}

	public EntityModel getEntityModel () {
		if (entityModel==null) 
			entityModel = initEntityModel();
		return entityModel;			
	}

	private EntityModel initEntityModel() {
		return new WsdlEntityModelMetro(wsdlModel.getJAXBModel());
	}

	@Override
	public WebServiceModel getWebServiceModel() {
		return webServiceModel;
	}
	
	
}
