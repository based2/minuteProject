package net.sf.minuteProject.configuration.bean.model.data.impl;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Reference;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.model.data.impl.DDLUtils.ColumnDDLUtils;

public abstract class ReferenceAbstract extends AbstractConfiguration implements Reference{

	private Reference reference;
		
    /**
     * Creates a new, empty reference.
     */
    public ReferenceAbstract(Reference reference)
    {
    	this.reference = reference;
    }

    /**
     * Returns the sequence value within the owning key.
     *
     * @return The sequence value
     */
    public int getSequenceValue()
    {
        return reference.getSequenceValue();
    }

    /**
     * Sets the sequence value within the owning key. Please note
     * that you should not change the value once the reference has
     * been added to a key.
     *
     * @param sequenceValue The sequence value
     */
    public void setSequenceValue(int sequenceValue)
    {
    	reference.setSequenceValue(sequenceValue);
    }

    /**
     * Returns the local column.
     *
     * @return The local column
     */
    public Column getLocalColumn()
    {
        return reference.getLocalColumn();
    }

    /**
     * Returns the foreign column.
     *
     * @return The foreign column
     */
    public Column getForeignColumn()
    {
    	return reference.getForeignColumn();
        //return new ColumnDDLUtils (reference.getForeignColumn());
    }

    /**
     * Returns the name of the local column.
     * 
     * @return The column name
     */
    public String getLocalColumnName()
    {
        return reference.getLocalColumnName();
    }

    /**
     * Sets the name of the local column. Note that you should not use this method when
     * manipulating the model manually. Rather use the {@link #setLocalColumn(Column)} method.
     * 
     * @param localColumnName The column name
     */
    public void setLocalColumnName(String localColumnName)
    {
    	reference.setLocalColumnName(localColumnName);
    }
    
    /**
     * Returns the name of the foreign column.
     * 
     * @return The column name
     */
    public String getForeignColumnName()
    {
        return reference.getForeignColumnName();
    }
    
    /**
     * Sets the name of the remote column. Note that you should not use this method when
     * manipulating the model manually. Rather use the {@link #setForeignColumn(Column)} method.
     * 
     * @param foreignColumnName The column name
     */
    public void setForeignColumnName(String foreignColumnName)
    {
    	reference.setForeignColumnName(foreignColumnName);
    }

    /**
     * {@inheritDoc}
     */
    public String toString()
    {
        return reference.toString();
    }
    
    // Added methods
    
    public void setForeignColumn(Column column) {
    	reference.setForeignColumn(column);
    }

    public void setForeignTable(Table table) {
    	reference.setForeignTable(table);
    }

    public Table getForeignTable () {
    	return reference.getForeignTable();
    }
    
    public void setForeignTableName(String foreignTableName) {
		reference.setForeignTableName(foreignTableName);
	}
	
    public String getForeignTableName () {
    	return reference.getForeignTableName();
    }    

	public void setLocalColumn(Column localColumn) {
		reference.setLocalColumn(localColumn);
	}
	
	@Override
	public boolean isMasterRelationship() {
		// TODO Auto-generated method stub
		return reference.isMasterRelationship();
	}	
	
	@Override
	public void setMasterRelationship() {
		reference.setMasterRelationship();
	}

	public void setAggregateRelationship(){
		reference.setAggregateRelationship();
	}
	
	public boolean isAggregateRelationship(){
		return reference.isAggregateRelationship();
	}	
}
