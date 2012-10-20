package net.sf.minuteProject.configuration.bean.model.data;

/**
 * Inspired from DDLUtils Foreign Key class
 * Represents a database foreign key.
 * 
 * @author Florian Adler
 */
public interface ForeignKey extends Cloneable
{
    
   /**
     * Returns the name of this foreign key.
     * 
     * @return The name
     */
    public String getName();

    /**
     * Sets the name of this foreign key.
     * 
     * @param name The name
     */
    public void setName(String name);
    
    /**
     * Returns the foreign table.
     *
     * @return The foreign table
     */
    public Table getForeignTable();

    /**
     * Returns the name of the foreign table.
     * 
     * @return The table name
     */
    public String getForeignTableName();
    
    /**
     * Sets the name of the foreign table. Please note that you should not use this method
     * when manually constructing or manipulating the database model. Rather utilize the
     * {@link #setForeignTable(Table)} method.
     * 
     * @param foreignTableName The table name
     */
    public void setForeignTableName(String foreignTableName);

    /**
     * Returns the number of references.
     * 
     * @return The number of references
     */
    public int getReferenceCount();

    /**
     * Returns the indicated reference.
     * 
     * @param idx The index
     * @return The reference
     */
    public Reference getReference(int idx);

    /**
     * Returns the references.
     * 
     * @return The references
     */
    public Reference[] getReferences();

    /**
     * Returns the first reference if it exists.
     * 
     * @return The first reference
     */
    public Reference getFirstReference();

    /**
     * Removes the indicated reference.
     * 
     * @param idx The index of the reference to remove
     */
    public void removeReference(int idx);

    public String toVerboseString();
    
    public void setReference(Reference reference);
    
    public boolean isBidirectional();
    
    public void setBidirectional(boolean isBidirectional);
}
