package net.sf.minuteProject.configuration.bean.model.webservice.impl.metro;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.model.webservice.Operation;
import net.sf.minuteProject.configuration.bean.model.webservice.Port;
import net.sf.minuteProject.configuration.bean.model.webservice.WsdlObject;


public class WsdlPortMetro extends WsdlObjectMetro implements Port {

	private com.sun.tools.ws.processor.model.Port port;
	private List<Operation> operations;
	
	WsdlPortMetro (com.sun.tools.ws.processor.model.Port port) {
		this.port = port;
		namespace = port.getName().getNamespaceURI();
		name=port.getName().getLocalPart();
		entity=new WsdlTechnicalEntityMetro(port.getEntity());
	}
	
//	@Override
//	public String getName() {
//		return port.getName().toString();
//	}

	@Override
	public List<Operation> getOperations() {
		if (operations==null)
			operations=initOperations();
		return operations;
	}

	private List<Operation> initOperations() {
		operations = new ArrayList<Operation>();
		for (com.sun.tools.ws.processor.model.Operation operation: port.getOperations())
			operations.add(new WsdlOperationMetro(operation));
		return operations;
	}


	
}
