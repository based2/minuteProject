package net.sf.minuteProject.application;

import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.Configuration;
import net.sf.minuteProject.configuration.bean.Model;
import net.sf.minuteProject.configuration.bean.Target;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.TemplateTarget;
import net.sf.minuteProject.configuration.bean.view.Function;
import net.sf.minuteProject.configuration.bean.view.Service;
import net.sf.minuteProject.configuration.bean.view.View;
import net.sf.minuteProject.exception.MinuteProjectException;
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
public class ViewGenerator extends AbstractGenerator{

	public static final String GENERATOR_VIEW_RULES = "net/sf/minuteProject/configuration/view-config-rules.xml";
	
	private View view;
	private String viewConfig;
	
	public String getViewConfig() {
		return viewConfig;
	}

	public void setViewConfig(String viewConfig) {
		this.viewConfig = viewConfig;
	}

	/**
	 * Constructs the generator with its configuration
	 * @param configurationFile
	 */
	public ViewGenerator (String configurationFile) {
		super(configurationFile);
	}
	
	@Override
	public AbstractConfiguration getConfigurationRoot() {
		return new View();
	}

	@Override
	public String getConfigurationRulesFile() {
		return GENERATOR_VIEW_RULES;
	}
	
	public String getPropertyConfigurationRulesFile() {
		return null;
	}

	public static void main (String args[]) throws Exception {
		String viewConfig = "generator-view-config.xml";
		ViewGenerator viewGenerator = new ViewGenerator(viewConfig);
		View view = (View) viewGenerator.load();
		viewGenerator.setView(view);
		viewGenerator.loadTarget(view, view.getTarget()); 
		viewGenerator.generate(view.getTarget());
	}

	
	public View load2 () throws Exception{
		View view = new View();
        InputStream input = getClass().getClassLoader().getSystemResourceAsStream(getConfigurationFile());
        URL rulesURL = getClass().getClassLoader().getResource(getConfigurationRulesFile());
        Digester digester = DigesterLoader.createDigester(rulesURL);
        digester.push(view);
        digester.parse(input);
        return view;		
	}

	/* (non-Javadoc)
	 * @see net.sf.minuteProject.application.Generator#generate(net.sf.minuteProject.configuration.bean.Template)
	 */
	public void generate (Template template) throws MinuteProjectException{
		// TODO Auto-generated method stub
		//getView();
		if (template.getViewSpecific().equals("true"))
			generateArtifactsByView (template);  	
	    else if (template.getServiceSpecific().equals("true"))
	    	generateArtifactsByService (template);  	
	    else if (template.getFunctionSpecific().equals("true"))
	    	generateArtifactsByFunction (template);		
		
	}

	public View getView() throws MinuteProjectException {
		if (view==null) {
			ViewGenerator viewGenerator = new ViewGenerator(getViewConfig());
			setView ((View) viewGenerator.load());			
		}
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}
	
    private void writeTemplateResultView (VelocityContext context, Template template) {
    	try {
    		String outputFilename = template.getGeneratorOutputFileNameForView(getView(), template);
    		// TODO set as method in Generator
    		context.put("view", getView());
    		context.put("template", template);
    		putCommonContextObject(context);
	    	produce (context, template, outputFilename); 
    	}        
    	catch( Exception e )
        {
            System.out.println(e);
        }
    }  

    private void generateArtifactsByView (Template template) throws MinuteProjectException{
   		writeTemplateResult (getView(), template);
    }
    
    private void generateArtifactsByService (Template template) throws MinuteProjectException{
    	for (Iterator<Service> iter = getView().getServices().iterator(); iter.hasNext();) {
    		writeTemplateResult ((Service)iter.next(), template);
    	}
    }

    private void generateArtifactsByFunction (Template template) throws MinuteProjectException{
    	for (Iterator<Service> iter = getView().getServices().iterator(); iter.hasNext();) {
        	for (Iterator<Function> iter2 = ((Service)iter.next()).getFunctions().iterator(); iter2.hasNext();) {
        		writeTemplateResult ((Function)iter2.next(), template);
        	}
        }
    }
    
    private void writeTemplateResult (AbstractConfiguration bean, Template template) throws MinuteProjectException{
    	String outputFilename = template.getGeneratorOutputFileNameForConfigurationBean(bean, template);
		VelocityContext context = getVelocityContext(template);
		String beanName = StringUtils.lowerCase(bean.getClass().getName());
		beanName = StringUtils.substring(beanName, beanName.lastIndexOf(".")+1);
    	context.put(beanName, bean);
		context.put("template", template);
		putCommonContextObject(context);
    	try {
			produce (context, template, outputFilename);
		} catch (Exception e) {
			e.printStackTrace();
			throwException(e, "ERROR on template " + template.getName() + " - on bean " + bean.getName());
		}     	
    }
    
    private void writeTemplateResultFunction (VelocityContext context, Function function, Template template) {
    	try {
    		String outputFilename = template.getGeneratorOutputFileNameForFunction(function, template);
    		// TODO set as method in Generator
    		context.put("function", function);
    		context.put("template", template);
    		putCommonContextObject(context);
	    	produce (context, template, outputFilename); 
    	}        
    	catch( Exception e )
        {
            System.out.println(e);
        }
    } 
    
    private void putCommonContextObject (VelocityContext context) {
		context.put("convertUtils", new ConvertUtils());
		context.put("commonUtils", new CommonUtils());
		context.put("viewUtils", new ViewUtils());
		context.put("formatUtils", new FormatUtils());
		context.put("bslaViewLibraryUtils", new BslaViewLibraryUtils());
		context.put("URLUtils", new URLUtils());
    }
    
    
}
