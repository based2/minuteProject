package net.sf.minuteProject.configuration.bean.view;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.Template;

/**
 * @author Florian Adler
 *
 */
public class Service extends AbstractConfiguration{

	private View view;
	private List<Function> functions;

	public List<Function> getFunctions() {
		if (functions == null)
			setFunctions(new ArrayList<Function>());
		return functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}
	
	public void addFunction (Function function) {
		function.setService(this);
		getFunctions().add(function);
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}
	
	public String getTechnicalPackage(Template template) {
		StringBuffer sb = new StringBuffer(getView().getTechnicalPackage(template));
		sb.append("."+getName());
		return sb.toString();
	}	
}
