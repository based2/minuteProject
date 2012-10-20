package net.sf.minuteProject.configuration.bean.model.data;

import java.util.List;

public interface View extends Table {
	
	/**
	 * get the virtualPrimaryKey
	 * A view do not have pk but we can assign a default one
	 * It is sometimes needed when we consider that a view is an entity like a table
	 * Some framework require that an entity to have a pk, so it provides a fake one.
	 * This may have sides effects of course, but it is the configurator to decide if it is real or not
	 * @return Column[]
	 */
	public Column[] getVirtualPrimaryKeys();
	
	/**
	 * Get the real pk (if exists)
	 * @return
	 */
	public Column[] getRealPrimaryKeys();

	public void setVirtualPrimaryKeys(Column[] virtualPrimaryKeys);
	
	public void setRealPrimaryKeys(Column[] realPrimaryKeys);
	
	public void addVirtualPrimaryKey(Column virtualPrimaryKey);
	
	public void addRealPrimaryKey(Column realPrimaryKey);
	
	public Component[] getComponents();
	
	public void setComponents(List<Component> components);

	public void setForeignKey (ForeignKey foreignKey);
	
}
