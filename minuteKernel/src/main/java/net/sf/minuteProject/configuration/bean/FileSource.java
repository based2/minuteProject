package net.sf.minuteProject.configuration.bean;

public class FileSource extends AbstractConfiguration{

	private String name;
	private String dir;
	
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
