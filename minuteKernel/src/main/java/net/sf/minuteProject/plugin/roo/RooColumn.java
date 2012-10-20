package net.sf.minuteProject.plugin.roo;

public class RooColumn {
	
	private String rooConsoleType, 
	               minSizeChunk,
	               sizeMaxChunk,
	               notNullChunk,
	               typeChunk,
	               columnVariableName,
	               columnClassName;

	public String getRooConsoleType() {
		return rooConsoleType;
	}

	public void setRooConsoleType(String rooConsoleType) {
		this.rooConsoleType = rooConsoleType;
	}

	public String getMinSizeChunk() {
		return minSizeChunk;
	}

	public void setMinSizeChunk(String minSizeChunk) {
		this.minSizeChunk = minSizeChunk;
	}

	public String getSizeMaxChunk() {
		return sizeMaxChunk;
	}

	public void setSizeMaxChunk(String sizeMaxChunk) {
		this.sizeMaxChunk = sizeMaxChunk;
	}

	public String getNotNullChunk() {
		return notNullChunk;
	}

	public void setNotNullChunk(String notNullChunk) {
		this.notNullChunk = notNullChunk;
	}

	public String getTypeChunk() {
		return typeChunk;
	}

	public void setTypeChunk(String typeChunk) {
		this.typeChunk = typeChunk;
	}

	public String getColumnVariableName() {
		return columnVariableName;
	}

	public void setColumnVariableName(String columnVariableName) {
		this.columnVariableName = columnVariableName;
	}

	public String getColumnClassName() {
		return columnClassName;
	}

	public void setColumnClassName(String columnClassName) {
		this.columnClassName = columnClassName;
	}

	@Override
	public String toString() {
		return "RooColumn [columnVariableName=" + columnVariableName
				+ ", minSizeChunk=" + minSizeChunk + ", notNullChunk="
				+ notNullChunk + ", rooConsoleType=" + rooConsoleType
				+ ", sizeMaxChunk=" + sizeMaxChunk + ", typeChunk=" + typeChunk
				+ "]";
	}

	

}
