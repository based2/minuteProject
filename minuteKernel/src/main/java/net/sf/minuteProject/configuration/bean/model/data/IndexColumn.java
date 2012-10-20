package net.sf.minuteProject.configuration.bean.model.data;

public interface IndexColumn {

    /**
     * Returns the position within the owning index.
     *
     * @return The position
     */
    public int getOrdinalPosition();
    
    /**
     * Returns the name of the column.
     * 
     * @return The name
     */
    public String getName();

    /**
     * Returns the indexed column.
     *
     * @return The column
     */
    public Column getColumn();

    /**
     * Returns the size of the column in the index.
     * 
     * @return The size
     */
    public String getSize();
    
    public boolean equalsIgnoreCase(IndexColumn other);
}
