package net.sf.minuteProject.configuration.bean;

import net.sf.minuteProject.configuration.bean.model.webservice.Wsdl;
import net.sf.minuteProject.configuration.bean.model.webservice.WsdlModel;
import net.sf.minuteProject.configuration.bean.model.webservice.WsdlModelFactory;

public class WebServiceModel extends AbstractConfiguration{

	private Wsdl wsdl;
	private Model model;
	private WsdlModel wsdlModel;

	public WsdlModel getWsdlModel() {
		return wsdlModel;
	}
	public void setWsdlModel(WsdlModel wsdlModel) {
		this.wsdlModel = wsdlModel;
	}
	
	public Wsdl getWsdl() {
		if (wsdl==null) {
			wsdl=new Wsdl();
			wsdl.setWebServiceModel(this);
		}		
		return wsdl;
	}
	public void setWsdl(Wsdl wsdl) {
		this.wsdl = wsdl;
	}
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	public void load() {
		wsdlModel = WsdlModelFactory.getInstance().getWsdlModel(this);
		
	}
	public String getWsdlLocation() {
		if (wsdl!=null) return wsdl.getLocation();
		return null;
	}


}
