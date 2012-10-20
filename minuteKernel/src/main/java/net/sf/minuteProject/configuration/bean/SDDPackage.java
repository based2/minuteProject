package net.sf.minuteProject.configuration.bean;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import net.sf.minuteProject.configuration.bean.enrichment.group.Group;
import net.sf.minuteProject.configuration.bean.model.data.Database;
import net.sf.minuteProject.configuration.bean.model.data.Function;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.model.statement.Composite;
import net.sf.minuteProject.configuration.bean.model.statement.Query;
import net.sf.minuteProject.utils.CommonUtils;
import net.sf.minuteProject.utils.ModelUtils;

public class SDDPackage extends BusinessPackageAdapter {

	private StatementModel statementModel;
	private List<Package> functions;
	
	public SDDPackage(StatementModel statementModel) {
		this.statementModel = statementModel;
	}

	@Override
	protected String getDefaultPackage() {
		return statementModel.getModel().getName();
	}

	public StatementModel getStatementModel() {
		return statementModel;
	}

	public void setStatementModel(StatementModel statementModel) {
		this.statementModel = statementModel;
	}
	
	void setPackages(Model model, Database database) {
		Hashtable<String, Package> ht = new Hashtable<String, Package>();
		for (Query query:statementModel.getQueries().getQueries()) {
//			if (ModelUtils.isToGenerate(statementModel, query)) {
				String packageName = CommonUtils.getSDDPackageName(query);
				Package pack = (Package) ht.get(packageName);
				if (pack == null) {
					pack = new Package();
					pack.setSddPackage(this);
					pack.setName(packageName);
					query.setPackage(pack);
//					query.setDatabase (database);
				}
				pack.addStatement(query);
				ht.put(packageName, pack);
//			}
		}
		for (Composite composite:statementModel.getComposites().getComposites()) {
//			if (ModelUtils.isToGenerate(statementModel, query)) {
			String packageName = CommonUtils.getSDDPackageName(composite);
			Package pack = (Package) ht.get(packageName);
			if (pack == null) {
				pack = new Package();
				pack.setSddPackage(this);
				pack.setName(packageName);
				composite.setPackage(pack);
//					query.setDatabase (database);
			}
			pack.addComposite(composite);
			ht.put(packageName, pack);
//			}
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
