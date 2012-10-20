package net.sf.minuteProject.application;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationRoot;
import net.sf.minuteProject.configuration.bean.Target;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.exception.MinuteProjectException;

/**
 * @author Florian Adler
 *
 */
public interface Generator {

	public static final String GENERATOR_TARGET_RULES = "net/sf/minuteProject/configuration/generator-config-targetSet-rules.xml";
	/**
	 * Loads a configuration to get a configuration
	 * @param configuration
	 * @param rules
	 * @return AbstractConfiguration
	 */
	public AbstractConfiguration load (String configuration, String rules) throws Exception;


	/**
	 * Loads a target via its refname into a root element
	 * @param abstractConfigurationRoot
	 * @param targetRefname
	 * @throws Exception
	 */
	public void loadTarget (AbstractConfigurationRoot abstractConfigurationRoot, Target target) throws Exception;

	/**
	 * generate the targets template artifacts
	 * @param target
	 */
	public void generate (Target target) throws Exception;
	
	/**
	 * generate the template's artifacts
	 * @param template
	 */
	public void generate (Template template) throws MinuteProjectException;
		
	public void getSolutionPortfolio (String solutionPortfolioFileName);
	
}
