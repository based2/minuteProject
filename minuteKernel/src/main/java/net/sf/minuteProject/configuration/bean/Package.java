package net.sf.minuteProject.configuration.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import net.sf.minuteProject.configuration.bean.enrichment.group.EntityGroup;
import net.sf.minuteProject.configuration.bean.enrichment.group.Group;
import net.sf.minuteProject.configuration.bean.enrichment.security.SecurityColor;
import net.sf.minuteProject.configuration.bean.model.data.Function;
import net.sf.minuteProject.configuration.bean.model.data.View;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.model.statement.Composite;
import net.sf.minuteProject.configuration.bean.model.statement.Query;
import net.sf.minuteProject.utils.TableUtils;

public class Package extends PackageAdapter <Group, GeneratorBean>{

	private List<Table> listOfTables;
	private List<View> listOfViews;
	private List<Table> listOfEntities;
	private String name;
	private BusinessPackage businessPackage;
	private FunctionPackage functionPackage;
	private SDDPackage sddPackage;
	private List<Function> listOfFunctions;
	private List<Query> listOfQueries;
	private List<Composite> listOfComposites;
	
	private SecurityColor securityColor;
	private String alias;

	public List<Table> getListOfEntities() {
		if (listOfEntities==null) {
			listOfEntities = new ArrayList<Table>();
			for (Table table : getListOfTables()) {
				listOfEntities.add(table);
			}
			for (Table table : getListOfViews()) {
				listOfEntities.add(table);
			}
		}
		return listOfEntities;
	}
	
	public List<View> getListOfViews() {
		if (listOfViews==null) listOfViews = new ArrayList<View>();
		return listOfViews;
	}
	
	public void addView(View view) {
		if (listOfViews==null)
			listOfViews = new ArrayList<View>();
		view.setPackage(this);
		listOfViews.add(view);
	}
	
	public List<net.sf.minuteProject.configuration.bean.model.data.Table> getListOfTables() {
		if (listOfTables==null) listOfTables = new ArrayList<Table>();
		return listOfTables;
	}
	
	public void addTable(net.sf.minuteProject.configuration.bean.model.data.Table table) {
		if (listOfTables==null)
			listOfTables = new ArrayList<Table>();
		table.setPackage(this);
		listOfTables.add(table);
	}
	
	
	public void addStatement(Query query) {
		if (listOfQueries==null)
			listOfQueries = new ArrayList<Query>();
		query.setPackage(this);
		listOfQueries.add(query);
	}

	public void addComposite(Composite composite) {
		if (listOfComposites==null)
			listOfComposites = new ArrayList<Composite>();
		composite.setPackage(this);
		listOfComposites.add(composite);
	}
	
	public SDDPackage getSddPackage() {
		return sddPackage;
	}

	public void setSddPackage(SDDPackage sddPackage) {
		this.sddPackage = sddPackage;
	}

	public List<Query> getListOfQueries() {
		return listOfQueries;
	}

	public String getName() {
		if (!StringUtils.isEmpty(name)) return name;
		return getBusinessPackage().getBusinessModel().getModel().getAlias();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public FunctionPackage getFunctionPackage() {
		return functionPackage;
	}

	public void setFunctionPackage(FunctionPackage functionPackage) {
		this.functionPackage = functionPackage;
	}

	public BusinessPackage getBusinessPackage() {
		return businessPackage;
	}
	public void setBusinessPackage(BusinessPackage businessPackage) {
		this.businessPackage = businessPackage;
	}
	
	public String getTechnicalPackage(Template template) {
		StringBuffer sb = new StringBuffer();
//		if (template.getAddModelDirName()!=null && template.getAddModelDirName().equals("false"))
		if (template.getAddTechnicalDirName()!=null && template.getAddTechnicalDirName().equals("false")) {
//			return getName();
			sb.append(template.getPackageRoot());
		}
		else
//		StringBuffer sb = new StringBuffer(getBusinessPackage().getBusinessModel().getModel().getTechnicalPackage(template));
			sb.append(getTechPackage(template));
		if (template.getAddBusinessPackageDirName()!=null && template.getAddBusinessPackageDirName().equals("false")) {}
		else {
			String name = getName();
			if (name!=null && !name.equals(""))
				sb.append("."+name);
		}
		return sb.toString();
	}

	private String getTechPackage(Template template) {
		if (getBusinessPackage()!=null) return getBusinessPackage().getBusinessModel().getModel().getTechnicalPackage(template);
		if (getFunctionPackage()!=null) return getFunctionPackage().getFunctionModel().getModel().getTechnicalPackage(template);
		if (getSddPackage()!=null) return getSddPackage().getStatementModel().getModel().getTechnicalPackage(template);
		return "";
	}

	public SecurityColor getSecurityColor() {
		return securityColor;
	}

	public void setSecurityColor(SecurityColor securityColor) {
		this.securityColor = securityColor;
	}

	public String getAlias() {
		if (alias==null  || alias.equals(""))
			alias = getName();
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public void addFunction(Function function) {
		if (listOfFunctions==null)
			listOfFunctions = new ArrayList<Function>();
		function.setPackage(this);
		listOfFunctions.add(function);
	}
	
	@Override
	protected List<GeneratorBean> convertGroupToElement(Group t) {
		List<GeneratorBean> tables = new ArrayList<GeneratorBean>();
		for (String element : t.getList()) {
			Table table = TableUtils.getTable(getBusinessPackage().getBusinessModel().getModel().getDataModel().getDatabase(), element);
			if (table!=null)
				tables.add(table);
		}
		return tables;
	}

	@Override
	public List<GeneratorBean> getElements() {
		List<GeneratorBean> list = new ArrayList<GeneratorBean>();
		for (Table table : getListOfEntities()) {
			list.add(table);
		}		
		return list;
	}

	public EntityGroup getDefaultGroup () {
		EntityGroup entityGroup = new EntityGroup();
		for (Table table : getListOfEntities()) {
			entityGroup.getList().add(table.getName());
		}
		return entityGroup;
	}
//	public String getDefaultPackage() {
//	if (defaultPackage==null)
//		return businessModel.getModel().getName();
//	return defaultPackage;
//	}	
}
