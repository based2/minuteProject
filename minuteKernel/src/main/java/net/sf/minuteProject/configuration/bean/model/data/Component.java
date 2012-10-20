package net.sf.minuteProject.configuration.bean.model.data;

import org.w3c.dom.views.AbstractView;

import net.sf.minuteProject.configuration.bean.GeneratorBean;

public interface Component extends GeneratorBean{

	public Column[] getColumns();
	
	public void addColumn(Column column);
	
	public Component getParent();
	
	public void setParent(Component component);
	
	public Component[] getChildren();
	
	public void addChild (Component component);
	
	public void setName(String name);
	
	public String getPath ();
	
	public void setPath(String path);
	
	public Component getRoot();
	
	public Table getTable();
	
	public void setTable(Table table);
	
	public boolean hasParent();
}
