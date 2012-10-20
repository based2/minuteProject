package net.sf.minuteProject.configuration.bean;

import net.sf.minuteProject.utils.FormatUtils;
import net.sf.minuteProject.utils.ModelUtils;
import net.sf.minuteProject.utils.StringUtils;

public class Model extends AbstractConfiguration{
	
	private static final String DEFAUTMODEL_VALUE = "defautmodel";
	private String packageRoot;
	private String version;
	
	private DataModel dataModel;
	private BusinessModel businessModel;
	private FunctionModel functionModel;
	private StatementModel statementModel;
	private Configuration configuration;
	private WebServiceModel webServiceModel;

	public WebServiceModel getWebServiceModel() {
		if (webServiceModel==null) {
			webServiceModel=new WebServiceModel();
//			webServiceModel.setModel(this);
		}		
		return webServiceModel;
	}

	public void setWebServiceModel(WebServiceModel webServiceModel) {
		this.webServiceModel = webServiceModel;
		this.webServiceModel.setModel(this);
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public BusinessModel getBusinessModel() {
		if (businessModel==null) {
			businessModel=new BusinessModel();
			businessModel.setModel(this);
		}
		return businessModel;
	}

	public void setBusinessModel(BusinessModel businessModel) {
		businessModel.setModel(this);
		this.businessModel = businessModel;
	}
	
	public boolean hasBusinessModel() {
		if (businessModel!=null)
			return true;
		return false;
	}

	public DataModel getDataModel() {
		return dataModel;
	}

	public FunctionModel getFunctionModel() {
		return functionModel;
	}

	public void setFunctionModel(FunctionModel functionModel) {
		functionModel.setModel(this);
		this.functionModel = functionModel;
	}
	
	public boolean hasFunctionModel() {
		if (functionModel!=null)
			return true;
		return false;
	}
	
	public boolean hasStatementModel() {
		if (statementModel!=null)
			return true;
		return false;
	}

	public void setDataModel(DataModel dataModel) {
		dataModel.setModel(this);
		this.dataModel = dataModel;
	}

	public String getTechnicalPackage(Template template) {
		StringBuffer sb = new StringBuffer(ModelUtils.getPackageRoot(template));
		if ((template.getAddModelDirName()==null 
			|| template.getAddModelDirName().equals("")
			|| template.getAddModelDirName().equals("true"))	
			&& 
			!(template.getApplicationSpecific()!=null && template.getApplicationSpecific().equals("true"))
			) {
				sb.append("."+getName());
			}
		if (template.getTechnicalPackage()!=null && !template.getTechnicalPackage().equals(""))
			sb.append("."+template.getTechnicalPackage());		
		return sb.toString();
	}

	public String getName() {
		if (StringUtils.isEmpty(name)){
			name= DEFAUTMODEL_VALUE;
		}
		return name;
	}

	public String getPackageRoot() {
		return packageRoot;
	}

	public void setPackageRoot(String packageRoot) {
		this.packageRoot = packageRoot;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}		
	
	public String getAlias() {
		return FormatUtils.getJavaName(super.getAlias()).toLowerCase();
	}

	public boolean isUsingDefaultName() {
		return DEFAUTMODEL_VALUE.equals(getName());
	}

	public StatementModel getStatementModel() {
		return statementModel;
	}

	public void setStatementModel(StatementModel statementModel) {
		this.statementModel = statementModel;
		this.statementModel.setModel(this);
	}
	
}
