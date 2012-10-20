package net.sf.minuteProject.configuration.bean.model.data;

import java.util.List;

import net.sf.minuteProject.configuration.bean.enrichment.Trigger;
import net.sf.minuteProject.configuration.bean.enrichment.rule.Derivation;

/**
 * Represents a column in the database model.
 * 
 * @author Florian Adler
 */
public interface Column extends BaseColumn
{
    /**
     * Determines whether this column is a primary key column.
     * 
     * @return <code>true</code> if this column is a primary key column
     */
    public boolean isPrimaryKey();

    /**
     * Specifies whether this column is a primary key column.
     * 
     * @param primaryKey <code>true</code> if this column is a primary key column
     */
    public void setPrimaryKey(boolean primaryKey);

    /**
     * Determines whether this column is an auto-increment column.
     * 
     * @return <code>true</code> if this column is an auto-increment column
     */
    public boolean isAutoIncrement();

    /**
     * Specifies whether this column is an auto-increment column.
     * 
     * @param autoIncrement <code>true</code> if this column is an auto-increment column
     */
    public void setAutoIncrement(boolean autoIncrement);

    
    public Table getTable ();
    
    public boolean isVersion();
    
    public void setVersion(boolean isVersion);
    
	 public void setSearchable(boolean isSearchable);
	
	 public boolean isSearchable();
	 
	 public List<Trigger> getTriggers();
	 
	 public void addTriggers(Trigger trigger);

	public void setDerivations(List<Derivation> derivations);
	
	public List<Derivation> getDerivations();
	 
}

