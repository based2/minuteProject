package net.sf.minuteProject.configuration.bean.model.data.impl.DDLUtils;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationRoot;
import net.sf.minuteProject.configuration.bean.Package;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Component;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.model.data.View;
import net.sf.minuteProject.configuration.bean.model.data.impl.TableAbstract;
import net.sf.minuteProject.configuration.bean.system.Property;
import net.sf.minuteProject.utils.FormatUtils;

public class ComponentDDLUtils extends AbstractConfigurationRoot implements Component{

	private List<Column> columns;
	private Component parent;
	private List<Component> children;
	private String name, path;
	private Table table;
	private Package pack;
	
	public void addColumn(Column column) {
		getColumnList().add(column);
	}

	public Column[] getColumns() {
		return (Column[])getColumnList().toArray(new Column[columns.size()]);
	}

	private List<Column> getColumnList() {
		if (columns==null)
			columns = new ArrayList<Column>();
		return columns;
	}

	public Component[] getChildren() {
		return (Component[])getChildrenList().toArray(new Component[children.size()]);
	}
	
	public void addChild(Component component) {
		getChildrenList().add(component);
	}

	private List<Component> getChildrenList() {
		if (children==null)
			children = new ArrayList<Component>();
		return children;
	}
	
	public Component getParent() {
		return parent;
	}

	public void setParent(Component component) {
		this.parent = component;
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Package getPackage() {
		return pack;
	}

	public String getTechnicalPackage(Template template) {
		return getPackage().getTechnicalPackage(template);
	}

	public void setPackage(Package pack) {
		this.pack = pack;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Component getRoot() {
		return getUpperElement(this);
	}

	private Component getUpperElement (Component component) {
		Component parent = getParent();
		if (parent==null)
			return component;
		else
			return getUpperElement(parent);
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public boolean hasParent() {
		return (parent!=null);
	}

	public String getGeneratedBeanName() {
		return FormatUtils.getJavaName(getName());
	}

	public List<Property> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setProperties(List<Property> properties) {
		// TODO Auto-generated method stub
		
	}

	public Property[] getPropertiesArray() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasProperty(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	public Property getPropertyByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public Property getPropertyByTag(String tag) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
