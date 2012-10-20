package net.sf.minuteProject.configuration.bean.model.data.impl.DDLUtils;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.enrichment.Stereotype;
import net.sf.minuteProject.configuration.bean.model.data.Function;
import net.sf.minuteProject.configuration.bean.model.data.FunctionColumn;
import net.sf.minuteProject.configuration.bean.model.data.constant.Direction;
import net.sf.minuteProject.configuration.bean.model.data.impl.ColumnBaseAbstract;
import net.sf.minuteProject.utils.FormatUtils;

public class FunctionColumnDDLUtils extends ColumnBaseAbstract implements FunctionColumn {

	public FunctionColumnDDLUtils() {}
	
	private Direction direction;
	private int precision, precisionRadix, scale, sizeAsInt, typeCode;
	private Stereotype stereotype;
	private String defaultValue, size, type;
	private boolean isRequired, isReturn;
	private Integer minLength;
	private Function function;
	
	public Direction getDirection() {
		return direction;
	}

	public int getPrecision() {
		return precision;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public String getDefaultValue() {
		// TODO Auto-generated method stub
		return defaultValue;
	}

	public String getJavaName() {
		// TODO Auto-generated method stub
		return FormatUtils.getJavaName(getName());
	}


	public boolean isRequired() {
		return isRequired;
	}

	public void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}

	public int getPrecisionRadix() {
		return precisionRadix;
	}

	public void setPrecisionRadix(int precisionRadix) {
		this.precisionRadix = precisionRadix;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getSizeAsInt() {
		return sizeAsInt;
	}

	public void setSizeAsInt(int sizeAsInt) {
		this.sizeAsInt = sizeAsInt;
	}

    public String getType()
    {
    	return type;
    }
    
	public void setType(String type) {
		this.type = type;
	}

	public int getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(int typeCode) {
		this.typeCode = typeCode;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Stereotype getStereotype() {
		return stereotype;
	}

	public void setStereotype(Stereotype stereotype) {
		this.stereotype = stereotype;
	}

	public boolean isLob() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isOfBinaryType() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isOfNumericType() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isOfSpecialType() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isOfTextType() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setJavaName(String javaName) {
		// TODO Auto-generated method stub
		
	}

	public void setSizeAndScale(int size, int scale) {
		// TODO Auto-generated method stub
		
	}

	public String toVerboseString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTechnicalPackage(Template template) {
		return getPackage().getTechnicalPackage(template);
	}
	
   public int minLength() {
   	if (minLength==null)
   		minLength = 0;
   	return minLength;
   }
   
   public void setMinLength(int minLength) {
   	this.minLength = minLength;
   }
	
	@Override
	public boolean isReturn() {
		return (Direction.RETURN.equals(direction));
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}


}
