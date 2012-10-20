package net.sf.minuteProject.configuration.bean;

import java.io.Serializable;
import java.util.List;

import net.sf.minuteProject.configuration.bean.system.Property;

public interface GeneratorBean extends Cloneable, Serializable{

	// Minute standard method
	/**
	 * In MinuteProject a table is associated with a package
	 * @return net.sf.minuteProject.configuration.bean.Package
	 */
	public net.sf.minuteProject.configuration.bean.Package getPackage() ;

	/**
	 * Get the package to which the table is associated to 
	 * @param pack
	 */
	public void setPackage(net.sf.minuteProject.configuration.bean.Package pack) ;
	
	/**
	 * Get the technical package
	 * @param template
	 * @return String
	 */
	public String getTechnicalPackage(Template template);
	
	public String getName();

	/**
	 * Get the name of the formatted to be output.
	 * @return String
	 */
	public String getGeneratedBeanName();
	
	public void setProperties(List<Property> properties);
	
	public List<Property> getProperties();
	
	public Property[] getPropertiesArray();
	
	public boolean hasProperty (String name);
	
	public Property getPropertyByTag (String tag) ;
	
	public Property getPropertyByName (String name) ;

	public String getAlias();
	
	public void setAlias(String alias);
	
    public void setComment (String comment);
    
    public String getComment();
    /**
     * Returns the description of the column.
     *
     * @return The description
     */
    public String getDescription();

    /**
     * Sets the description of the column.
     *
     * @param description The description
     */
    public void setDescription(String description);    
    
    public void enableCache();
    
}
