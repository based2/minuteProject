package net.sf.minuteProject.configuration.bean.model.data.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.enrichment.Stereotype;
import net.sf.minuteProject.configuration.bean.enrichment.Trigger;
import net.sf.minuteProject.configuration.bean.enrichment.rule.Derivation;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.utils.FormatUtils;

public abstract class ColumnAbstract extends ColumnBaseAbstract implements Column {

	private Column column;
	private Table table;
	private Boolean isLob;
	private Stereotype stereotype;
	private Boolean isVersion, isSearchable;
	private Integer minLength;
	private List<Trigger> triggers;
	private List<Derivation> derivations;
	
	public ColumnAbstract(Column column, Table table) {
		this.column = column;
		this.table = table;
//		this.alias = column.getAlias();
	}
	
	public String getGeneratedBeanName() {
		return FormatUtils.getJavaName(getTable().getAlias()+"_"+getAlias());
	}	
    /**
     * Returns the name of the column.
     * 
     * @return The name
     */
    public String getName()
    {
    	return column.getName();
    }

    /**
     * Sets the name of the column.
     * 
     * @param name The name
     */
    public void setName(String name)
    {
    	column.setName(name);
    }

    /**
     * Returns the java name of the column. This property is unused by DdlUtils and only
     * for Torque compatibility.
     * 
     * @return The java name
     */
    public String getJavaName()
    {
        return column.getJavaName();
    }

    /**
     * Sets the java name of the column. This property is unused by DdlUtils and only
     * for Torque compatibility.
     * 
     * @param javaName The java name
     */
    public void setJavaName(String javaName)
    {
    	column.setJavaName(javaName);
    }

    /**
     * Returns the description of the column.
     *
     * @return The description
     */
    public String getDescription()
    {
        return column.getDescription();
    }

    /**
     * Sets the description of the column.
     *
     * @param description The description
     */
    public void setDescription(String description)
    {
        column.setDescription(description);
    }

    /**
     * Determines whether this column is a primary key column.
     * 
     * @return <code>true</code> if this column is a primary key column
     */
    public boolean isPrimaryKey()
    {
        return column.isPrimaryKey();
    }

    /**
     * Specifies whether this column is a primary key column.
     * 
     * @param primaryKey <code>true</code> if this column is a primary key column
     */
    public void setPrimaryKey(boolean primaryKey)
    {
        column.setPrimaryKey(primaryKey);
    }

    /**
     * Determines whether this column is a required column, ie. that it is not allowed
     * to contain <code>NULL</code> values.
     * 
     * @return <code>true</code> if this column is a required column
     */
    public boolean isRequired()
    {
        return column.isRequired();
    }

    /**
     * Specifies whether this column is a required column, ie. that it is not allowed
     * to contain <code>NULL</code> values.
     * 
     * @param required <code>true</code> if this column is a required column
     */
    public void setRequired(boolean required)
    {
        column.setRequired(required);
    }

    /**
     * Determines whether this column is an auto-increment column.
     * 
     * @return <code>true</code> if this column is an auto-increment column
     */
    public boolean isAutoIncrement()
    {
        return column.isAutoIncrement();
    }

    /**
     * Specifies whether this column is an auto-increment column.
     * 
     * @param autoIncrement <code>true</code> if this column is an auto-increment column
     */
    public void setAutoIncrement(boolean autoIncrement)
    {
    	column.setAutoIncrement(autoIncrement);
    }

    /**
     * Returns the code (one of the constants in {@link java.sql.Types}) of the
     * JDBC type of the column.
     * 
     * @return The type code
     */
    public int getTypeCode()
    {
        return column.getTypeCode();
    }

    /**
     * Sets the code (one of the constants in {@link java.sql.Types}) of the
     * JDBC type of the column. 
     * 
     * @param typeCode The type code
     */
    public void setTypeCode(int typeCode)
    {
    	column.setTypeCode(typeCode);
    }

    /**
     * Returns the JDBC type of the column.
     * 
     * @return The type
     */
    public String getType()
    {
        return column.getType();
    }
    
    public String getRealType()
    {
    	return column.getType();
    }

    /**
     * Sets the JDBC type of the column.
     *
     * @param type The type
     */
    public void setType(String type)
    {
    	column.setType(type);
    }

    /**
     * Determines whether this column is of a numeric type.
     * 
     * @return <code>true</code> if this column is of a numeric type
     */
    public boolean isOfNumericType()
    {
        return column.isOfNumericType();
    }

    /**
     * Determines whether this column is of a text type.
     * 
     * @return <code>true</code> if this column is of a text type
     */
    public boolean isOfTextType()
    {
        return column.isOfTextType();
    }

    /**
     * Determines whether this column is of a binary type.
     * 
     * @return <code>true</code> if this column is of a binary type
     */
    public boolean isOfBinaryType()
    {
        return column.isOfBinaryType();
    }

    /**
     * Determines whether this column is of a special type.
     * 
     * @return <code>true</code> if this column is of a special type
     */
    public boolean isOfSpecialType()
    {
        return column.isOfSpecialType();
    }
    
    /**
     * Returns the size of the column.
     * 
     * @return The size
     */
    public String getSize()
    {
        return column.getSize();
    }

    /**
     * Returns the size of the column as an integer.
     * 
     * @return The size as an integer
     */
    public int getSizeAsInt()
    {
        return column.getSizeAsInt();
    }

    /**
     * Sets the size of the column. This is either a simple integer value or
     * a comma-separated pair of integer values specifying the size and scale.
     * 
     * @param size The size
     */
    public void setSize(String size)
    {
    	column.setSize(size);
    }
    
    /**
     * Returns the scale of the column.
     * 
     * @return The scale
     */
    public int getScale()
    {
        return column.getScale();
    }

    /**
     * Sets the scale of the column.
     *
     * @param scale The scale
     */
    public void setScale(int scale)
    {
    	column.setScale(scale);
    }

    /**
     * Sets both the size and scale.
     * 
     * @param size  The size
     * @param scale The scale
     */
    public void setSizeAndScale(int size, int scale){
    	column.setSizeAndScale(size, scale);
    }
    
    /**
     * Returns the precision radix of the column.
     * 
     * @return The precision radix
     */
    public int getPrecisionRadix()
    {
        return column.getPrecisionRadix();
    }

    /**
     * Sets the precision radix of the column.
     * 
     * @param precisionRadix The precision radix
     */
    public void setPrecisionRadix(int precisionRadix)
    {
    	column.setPrecisionRadix(precisionRadix);
    }

    /**
     * Returns the default value of the column.
     * 
     * @return The default value
     */
    public String getDefaultValue()
    {
        return column.getDefaultValue();
    }


    /**
     * Sets the default value of the column. Note that this expression will be used
     * within quotation marks when generating the column, and thus is subject to
     * the conversion rules of the target database.
     * 
     * @param defaultValue The default value
     */
    public void setDefaultValue(String defaultValue)
    {
        column.setDefaultValue(defaultValue);
    }

    /**
     * {@inheritDoc}
     */
    protected Object clone() throws CloneNotSupportedException
    {
    	return super.clone();
        /*Column result = (Column)super.clone();

        result._name            = _name;
        result._javaName        = _javaName;
        result._primaryKey      = _primaryKey;
        result._required        = _required;
        result._autoIncrement   = _autoIncrement;
        result._typeCode        = _typeCode;
        result._type            = _type;
        result._size            = _size;
        result._defaultValue    = _defaultValue;
        result._scale           = _scale;
        result._size            = _size;
        result._sizeAsInt       = _sizeAsInt;

        return result;*/
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object obj)
    {
    	return column.equals(obj);
     }

    /**
     * {@inheritDoc}
     */
    public int hashCode()
    {
    	return column.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    public String toString()
    {
    	return column.toString();
    }

    /**
     * Returns a verbose string representation of this column.
     * 
     * @return The string representation
     */
    public String toVerboseString()
    {
    	return column.toVerboseString();
    }

	public Table getTable() {
		return column.getTable();
	}
	
	public boolean isLob() {
		if (isLob==null)
			isLob = getIsLob();
		return isLob;
	}
	
	private Boolean getIsLob() {
		if (getType().equals("CLOB") || column.getType().equals("BLOB"))
			return true;
		return false;
	}

	public Stereotype getStereotype() {
		return stereotype;
	}

	public void setStereotype(Stereotype stereotype) {
		this.stereotype = stereotype;
	}

	public void setIsVersion(Boolean isVersion) {
		this.isVersion = isVersion;
	}
	
	public boolean isVersion() {
		if (isVersion!=null)
			return isVersion;
		return false;
	}

	public void setVersion(boolean isVersion) {
		this.isVersion = isVersion;
	}	
	
   public int minLength() {
   	if (minLength==null)
   		minLength = 0;
   	return minLength;
   }
   
   public void setMinLength(int minLength) {
   	this.minLength = minLength;
   }
   
	public boolean isSearchable() {
		return isSearchable;
	}

	public void setSearchable(boolean isSearchable) {
		this.isSearchable = isSearchable;
	}
	
	public List<Trigger> getTriggers() {
		if (triggers==null) triggers = new ArrayList<Trigger>();
		return triggers;
	}

	protected void setTriggers(List<Trigger> triggers) {
		this.triggers = triggers;
	}
	
	@Override
	public void addTriggers(Trigger trigger) {
		getTriggers().add(trigger);
	}

	public List<Derivation> getDerivations() {
		return derivations;
	}

	public void setDerivations(List<Derivation> derivations) {
		this.derivations = derivations;
	}
	
}
