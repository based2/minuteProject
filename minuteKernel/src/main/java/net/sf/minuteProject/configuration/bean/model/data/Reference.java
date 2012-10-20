package net.sf.minuteProject.configuration.bean.model.data;

import java.io.Serializable;

import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.utils.ColumnUtils;

/**
 * Inspired from DDLUtils Reference
 * Represents a reference between a column in the local table and a column in another table.
 * 
 * @author Florian Adler
 */
public interface Reference extends GeneratorBean
{


    /**
     * Returns the sequence value within the owning key.
     *
     * @return The sequence value
     */
    public int getSequenceValue();

    /**
     * Sets the sequence value within the owning key. Please note
     * that you should not change the value once the reference has
     * been added to a key.
     *
     * @param sequenceValue The sequence value
     */
    public void setSequenceValue(int sequenceValue);

    /**
     * Returns the local column.
     *
     * @return The local column
     */
    public Column getLocalColumn();
    

    /**
     * Sets the local column.
     *
     * @param localColumn The local column
   */  
    public void setLocalColumn(Column localColumn);
    
    /**
     * Returns the local table.
     *
     * @return The local table
     */
    public String getLocalTableName();
    

    /**
     * Sets the local table.
     *
     * @param localTable The local table
   */  
    public void setLocalTableName(String localTableName);
    

    /**
     * Returns the foreign column.
     *
     * @return The foreign column
     */
    public Column getForeignColumn();

    /**
     * Sets the foreign column.
     *
     * @param foreignColumn The foreign column
     
    public void setForeignColumn(Column foreignColumn);
*/
    /**
     * Returns the name of the local column.
     * 
     * @return The column name
     */
    public String getLocalColumnName();

    /**
     * Sets the name of the local column. Note that you should not use this method when
     * manipulating the model manually. Rather use the {@link #setLocalColumn(Column)} method.
     * 
     * @param localColumnName The column name
     */
    public void setLocalColumnName(String localColumnName);
    
    /**
     * Returns the name of the foreign column.
     * 
     * @return The column name
     */
    public String getForeignColumnName();
    
    /**
     * Sets the name of the remote column. Note that you should not use this method when
     * manipulating the model manually. Rather use the {@link #setForeignColumn(Column)} method.
     * 
     * @param foreignColumnName The column name
     */
    public void setForeignColumnName(String foreignColumnName);

    public void setForeignColumn(Column column);

    public void setForeignTable(Table table);
    
    public Table getForeignTable ();

	public void setForeignTableName(String foreignTableName);
	
	public String getForeignTableName ();
	
	public void setLocalTable(Table localTable);
	
	public Table getLocalTable();

	public boolean isMasterRelationship();
	
	public void setMasterRelationship();

	public void setAggregateRelationship();
	
	public boolean isAggregateRelationship();
	
}


