package net.sf.minuteProject.utils.io;

public class UpdatedAreaHolder {
	
	private String beginSnippet, endSnippet, snippet;
	private boolean isUpdated;
	
	public UpdatedAreaHolder(){}
	public String getBeginSnippet() {
		return beginSnippet;
	}
	public void setBeginSnippet(String beginSnippet) {
		this.beginSnippet = beginSnippet;
	}
	public boolean isUpdated() {
		return isUpdated;
	}
	public void setUpdated(boolean isUpdated) {
		this.isUpdated = isUpdated;
	}
	public String getEndSnippet() {
		return endSnippet;
	}
	public void setEndSnippet(String endSnippet) {
		this.endSnippet = endSnippet;
	}
	public String getSnippet() {
		return snippet;
	}
	public void setSnippet(String snippet) {
		this.snippet = snippet;
	} 
	
	String getContent () {
//		if (isUpdated==false) return null;
		StringBuffer sb = new StringBuffer();
		sb.append(beginSnippet+"\n");
		if (snippet!=null && !"".equals(snippet))
			sb.append(snippet+"\n");
		sb.append(endSnippet);
		return sb.toString();
	}
}
