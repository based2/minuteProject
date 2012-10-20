package net.sf.minuteProject.configuration.bean;

import net.sf.minuteProject.configuration.bean.condition.FunctionGenerationCondition;
import net.sf.minuteProject.configuration.bean.model.data.Database;

import org.apache.log4j.Logger;

public class FunctionModel {

	private static Logger logger = Logger.getLogger(FunctionModel.class);
	
	private Model model;
	private FunctionGenerationCondition functionGenerationCondition;
	private FunctionPackage functionPackage;

	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	
	public FunctionPackage getFunctionPackage() {
		if (functionPackage==null) functionPackage = new FunctionPackage(this);
		return functionPackage;
	}
	
	public void setFunctionPackage(FunctionPackage functionPackage) {
		this.functionPackage = functionPackage;
	}
	
	public void complementFunctionWithFunctionEntity() {
		Database database = model.getDataModel().getDatabase();
		getFunctionPackage().setPackages(model, database);
	}
	
	public FunctionGenerationCondition getFunctionGenerationCondition() {
		return functionGenerationCondition;
	}
	
	public void setFunctionGenerationCondition(
			FunctionGenerationCondition functionGenerationCondition) {
		this.functionGenerationCondition = functionGenerationCondition;
	}
	
}
