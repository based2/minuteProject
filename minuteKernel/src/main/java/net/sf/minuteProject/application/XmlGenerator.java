package net.sf.minuteProject.application;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.apache.commons.lang.StringUtils;
import org.apache.ddlutils.model.Database;
import org.apache.velocity.VelocityContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.Configuration;
import net.sf.minuteProject.configuration.bean.FileSource;
import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.configuration.bean.Model;
import net.sf.minuteProject.configuration.bean.Package;
import net.sf.minuteProject.configuration.bean.Target;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.TemplateTarget;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.view.Function;
import net.sf.minuteProject.configuration.bean.view.Service;
import net.sf.minuteProject.configuration.bean.view.View;
import net.sf.minuteProject.configuration.bean.xml.impl.dom4j.DocumentDom4j;
import net.sf.minuteProject.configuration.bean.xml.impl.dom4j.ElementDom4j;
import net.sf.minuteProject.exception.MinuteProjectException;
import net.sf.minuteProject.plugin.xml.schema.XmlSchemaUtils;
import net.sf.minuteProject.utils.BslaLibraryUtils;
import net.sf.minuteProject.utils.BslaViewLibraryUtils;
import net.sf.minuteProject.utils.CommonUtils;
import net.sf.minuteProject.utils.ConvertUtils;
import net.sf.minuteProject.utils.DatabaseUtils;
import net.sf.minuteProject.utils.FormatUtils;
import net.sf.minuteProject.utils.ModelUtils;
import net.sf.minuteProject.utils.URLUtils;
import net.sf.minuteProject.utils.ViewUtils;

/**
 * @author Florian Adler
 * 
 */
public class XmlGenerator extends AbstractGenerator {

	public static final String GENERATOR_MODEL_RULES = "net/sf/minuteProject/configuration/modelXml-config-rules.xml";

	private Model model;
	
    private Document document;
    private Element root;
    private net.sf.minuteProject.configuration.bean.xml.Document doc;
    private net.sf.minuteProject.configuration.bean.xml.Element rootElement;
    private net.sf.minuteProject.configuration.bean.xml.Element baseElement;
    private List<net.sf.minuteProject.configuration.bean.xml.Element> elements;
	private String modelConfig;

	/**
	 * Constructs the generator with its configuration
	 * 
	 * @param configurationFile
	 */
	public XmlGenerator(String configurationFile) {
		super(configurationFile);
	}

	@Override
	public AbstractConfiguration getConfigurationRoot() {
		return new Configuration();
	}

	@Override
	public String getConfigurationRulesFile() {
		return GENERATOR_MODEL_RULES;
	}
	
	public String getPropertyConfigurationRulesFile() {
		return null;
	}

	public static void main(String args[]) throws Exception {
		String config;
		if (args.length < 1) {
			System.exit(1);
		}
		config = args[0];
		XmlGenerator generator = new XmlGenerator(config);
		Configuration configuration = (Configuration) generator.load();
		Model model = configuration.getModel();
		generator.setModel(model);
		generator.loadModel(model);
		generator.loadTarget(model.getConfiguration(), model.getConfiguration()
				.getTarget());
		generator.setBaseElement (configuration);
		generator.generate(model.getConfiguration().getTarget());
	}

	private void loadModel(Model model) throws DocumentException, java.net.MalformedURLException {
		FileSource fileSource = model.getDataModel().getFileSource();
		if (fileSource!=null) {
			document = parse(fileSource);
			doc = new DocumentDom4j(document);
			root = document.getRootElement();
			rootElement = getRootElement(root);
			//treeWalk (root);
			treeWalkWithParent (root);
		}
	}
	
	private void setBaseElement (Configuration configuration) {
		baseElement = XmlSchemaUtils.getBaseElement (doc, configuration);
	}

    //TODO set the implementation in the elementdom4j class
    public void treeWalkWithParent(Element element) {
        for ( int i = 0, size = element.nodeCount(); i < size; i++ ) {
            Node node = element.node(i);
            if ( node instanceof Element ) {
            	Element ele = (Element)node;
            	//ele.getNamespace().getName()
            	ElementDom4j elementDom4jParent = new ElementDom4j(element);
            	ElementDom4j elementDom4j = new ElementDom4j(ele);
            	elementDom4j.setParentElement((net.sf.minuteProject.configuration.bean.xml.Element)elementDom4jParent);
            	getElements().add(elementDom4j);
            	treeWalkWithParent( ele);
            }
            else {
                // do something....
            }
        }
    }

    
//  TODO set the implementation in the elementdom4j class
    public net.sf.minuteProject.configuration.bean.xml.Element getRootElement (Element root) {
    	return new ElementDom4j(root);
    }
    
    private Document parse(FileSource fileSource) throws DocumentException, java.net.MalformedURLException {
    	String filename = fileSource.getDir()+"/"+fileSource.getName();
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(filename));
        return document;
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.minuteProject.application.Generator#generate(net.sf.minuteProject.configuration.bean.Template)
	 */
	public void generate(Template template) throws MinuteProjectException {
		if (template.getEntitySpecific().equals("true"))
			generateArtifactsByEntity(template);
		else if (template.getEntitySpecific().equals("true"))
			generateArtifactsByEntity(template);
		else if (template.getPackageSpecific().equals("true"))
			generateArtifactsByPackage(template);
		else if (template.getModelSpecific().equals("true"))
			generateArtifactsByModel(template);
		else if (template.getNodeAttributeNameSpecific().equals("true"))
			generateArtifactsByNodeAttributeName(template);		
	}

	public Model getModel() {
//		if (model == null) {
//			ModelGenerator modelGenerator = new ModelGenerator(getModelConfig());
//			setModel((Model) modelGenerator.load());
//		}
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}


	private void generateArtifactsByModel(Template template) throws MinuteProjectException {
		writeTemplateResult(getModel(), template);
	}

	private void generateArtifactsByPackage(Template template) throws MinuteProjectException {
		List packages = model.getBusinessModel().getBusinessPackage().getPackages();
		for (Iterator<Package> iter = packages.iterator(); iter.hasNext();) {
			writeTemplateResult((Package) iter.next(), template);
		}
	}

	private void generateArtifactsByEntity(Template template) throws MinuteProjectException {	
		for (Iterator iter =  getElements().iterator(); iter.hasNext(); ) {
			net.sf.minuteProject.configuration.bean.xml.Element element = (net.sf.minuteProject.configuration.bean.xml.Element)iter.next();
			writeTemplateResult(element, template);
		}
	}

	private void generateArtifactsByNodeAttributeName(Template template) throws MinuteProjectException {	
		for (Iterator iter =  getElements().iterator(); iter.hasNext(); ) {
			String nodeName = template.getNodeNameValue();
			net.sf.minuteProject.configuration.bean.xml.Element element = (net.sf.minuteProject.configuration.bean.xml.Element)iter.next();
			if (element.getElementName().equals(nodeName)) {
				String nodeAttributeName = template.getNodeAttributeNameValue();
				String value = element.getAttributeValue(nodeAttributeName);
				element.setName(value);
				// discriminator generator method
				if (template.isToGenerate(element)) {
					writeTemplateResult(element, template);
				}
			}
		}
	}
	
//	protected void writeTemplateResult(GeneratorBean bean, Template template) throws MinuteProjectException {
//		String outputFilename = template.getGeneratorOutputFileNameForConfigurationBean(bean, template);
//		VelocityContext context = getVelocityContext(template);
//		String beanName = getAbstractBeanName(bean);
//		context.put(beanName, bean);
//		context.put("template", template);
//		putCommonContextObject(context);
//		produce(context, template, outputFilename);
//	}

   protected String getAbstractBeanName (GeneratorBean bean) {
		String beanName = StringUtils.lowerCase(bean.getClass().getName());
		beanName = StringUtils.substring(beanName,
				beanName.lastIndexOf(".") + 1);
		if (beanName.equals("elementdom4j"))
			return "element";		
		return beanName;
   }
	   
	protected void putCommonContextObject(VelocityContext context, Template template) {
		context.put("document", doc);
		context.put("rootElement", rootElement);
		context.put("baseElement", baseElement);
		context.put("configuration", model.getConfiguration());
		context.put("elements", elements);
		context.put("convertUtils", new ConvertUtils());
		context.put("commonUtils", new CommonUtils());
		context.put("viewUtils", new ViewUtils());
		context.put("formatUtils", new FormatUtils());
		context.put("bslaLibraryUtils", new BslaLibraryUtils());
		context.put("databaseUtils", new DatabaseUtils());
		context.put("modelUtils", new ModelUtils());
		context.put("xmlSchemaUtils", new XmlSchemaUtils());
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public List<net.sf.minuteProject.configuration.bean.xml.Element> getElements() {
		if (elements==null)
			elements = new ArrayList<net.sf.minuteProject.configuration.bean.xml.Element>();
		return elements;
	}

	public void setElements(List<net.sf.minuteProject.configuration.bean.xml.Element> elements) {
		this.elements = elements;
	}

	public Element getRoot() {
		return root;
	}

	public void setRoot(Element root) {
		this.root = root;
	}
	
	public String getModelConfig() {
		return modelConfig;
	}

	public void setModelConfig(String modelConfig) {
		this.modelConfig = modelConfig;
	}

}
