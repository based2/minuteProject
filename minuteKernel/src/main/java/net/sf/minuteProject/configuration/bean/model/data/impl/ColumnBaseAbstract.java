package net.sf.minuteProject.configuration.bean.model.data.impl;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.model.data.BaseColumn;
import net.sf.minuteProject.configuration.bean.model.data.Column;

public abstract class ColumnBaseAbstract extends AbstractConfiguration implements BaseColumn{

	private String typeAlias;
	
	@Override
	public String getTypeAlias() {
		if (typeAlias==null)
			typeAlias=getType();
		return typeAlias;
	}
	
	public void setTypeAlias(String typeAlias){
		this.typeAlias = typeAlias;
	}
//
//    public String getType()
//    {
//        return getTypeAlias();
//    }
//    
//	protected abstract String getRealType();
}
