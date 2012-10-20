package net.sf.minuteProject.configuration.bean.model.webservice.impl.metro;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.model.webservice.Port;
import net.sf.minuteProject.configuration.bean.model.webservice.Service;
import net.sf.minuteProject.configuration.bean.model.webservice.WsdlObject;

public class WsdlServiceMetro extends WsdlObjectMetro implements Service{

	private com.sun.tools.ws.processor.model.Service service;
	private List<Port> ports;
	
	public WsdlServiceMetro(com.sun.tools.ws.processor.model.Service service) {
		this.service = service;
		entity=new WsdlTechnicalEntityMetro(service.getEntity());
	}
	
	public String getName(){
		return service.getName().toString();
	}

	@Override
	public List<Port> getPorts() {
		if (ports==null)
			ports=initPorts();
		return ports;
	}

	private List<Port> initPorts() {
		ports = new ArrayList<Port>();
		for (com.sun.tools.ws.processor.model.Port port : service.getPorts())
			ports.add(new WsdlPortMetro(port));
		return ports;
	}
	
	
}
