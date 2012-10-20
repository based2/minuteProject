package net.sf.minuteProject.configuration.bean;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import net.sf.minuteProject.configuration.bean.enrichment.group.Group;
import net.sf.minuteProject.configuration.bean.model.data.Database;
import net.sf.minuteProject.configuration.bean.model.data.Function;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.utils.CommonUtils;
import net.sf.minuteProject.utils.ModelUtils;

public class FunctionPackage extends BusinessPackageAdapter {

	private FunctionModel functionModel;
	private List<Package> functions;
	
	public FunctionPackage(FunctionModel functionModel) {
		this.functionModel = functionModel;
	}

	@Override
	protected String getDefaultPackage() {
		return functionModel.getModel().getName();
	}

	public FunctionModel getFunctionModel() {
		return functionModel;
	}

	public void setFunctionModel(FunctionModel functionModel) {
		this.functionModel = functionModel;
	}
	
	void setPackages(Model model, Database database) {
		Hashtable<String, Package> ht = new Hashtable<String, Package>();
		for (Function function:database.getFunctions()) {
			if (ModelUtils.isToGenerate(functionModel, function)) {
				String packageName = CommonUtils.getFunctionPackageName(model, function);
				Package pack = (Package) ht.get(packageName);
				if (pack == null) {
					pack = new Package();
					pack.setFunctionPackage(this);
					pack.setName(packageName);
					function.setPackage(pack);
					function.setDatabase (database);
				}
				pack.addFunction(function);
				ht.put(packageName, pack);
			}
		}
		Enumeration<Package> enumeration = ht.elements();
		while (enumeration.hasMoreElements()) {
			getFunctionPackages().add(enumeration.nextElement());
		}
	}

	public List<Package> getFunctionPackages() {
		if (functions==null) functions = new ArrayList<Package>();
		return functions;
	}


}
