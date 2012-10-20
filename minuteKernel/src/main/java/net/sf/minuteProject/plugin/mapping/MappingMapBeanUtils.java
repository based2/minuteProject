package net.sf.minuteProject.plugin.mapping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.loader.mapping.node.Bean;
import net.sf.minuteProject.loader.mapping.node.BeanAttribute;
import net.sf.minuteProject.loader.mapping.node.BeanMap;
import net.sf.minuteProject.loader.mapping.node.BeanMapping;
import net.sf.minuteProject.loader.mapping.node.BeanMappingProperties;
import net.sf.minuteProject.loader.mapping.node.BeanMappingProperty;
import net.sf.minuteProject.loader.mapping.node.BeanMappings;
import net.sf.minuteProject.loader.mapping.node.Service;
import net.sf.minuteProject.loader.mapping.node.Validation;
import net.sf.minuteProject.loader.mapping.node.ValidationProperty;
import net.sf.minuteProject.loader.mapping.node.Variable;
import net.sf.minuteProject.utils.CommonUtils;
import net.sf.minuteProject.utils.FormatUtils;

public class MappingMapBeanUtils {
	
	public static final String DEFAULT_STRING_RETURN = "";
	public static final String BEAN_OBJECT_TEMPLATE = "BeanObject";
	public static final String VALIDATION_WRAPPER_TEMPLATE = "ValidationWrapper";
	public static final String NAME_INFO = "NAME";
	public static final String PACKAGE_INFO = "PACKAGE";
	public static final String DEFAULT_SUPER_CLASS = "DEFAULT_SUPER_CLASS";
	public static final String DEFAULT_SUPER_CLASS_OTHERWISE_SPECIFIED = "DEFAULT_SUPER_CLASS_OTHERWISE_SPECIFIED";

	
	public String getTypeFormObjectVariable (String variable, String objectName, BeanMapping beanMapping) {
		Bean bean = getBean(beanMapping, objectName);
		for (BeanAttribute att : bean.getAttributes()) {
			if (att.getName().equals(variable))
				return getType(att);
		}
		return "NOT TYPE FOUND";
	}
	
	private String getType (BeanAttribute beanAttribute) {
		String type = beanAttribute.getType();
		if (type!=null && type.equals(""))
			return beanAttribute.getRefType();
		return type;
	}
	
	private BeanAttribute getBeanAttribute (String beanName, String attributeName, BeanMap beanMap) {
		Bean bean = getBean(beanMap, beanName);
		if (bean!=null) {
			getBeanAttribute(bean, attributeName);
		}
		return null;
	}
	
	private BeanAttribute getBeanAttribute (Bean bean, String attributeName) {
		return bean.getFirstBeanAttributeFromAttributeByName(attributeName);
	}
	
	private String getBeanAttributeClassName (Bean bean, String attributeName) {
		BeanAttribute beanAttribute = getBeanAttribute (bean, attributeName);
		if (beanAttribute!=null)
		   return getType(beanAttribute);
		return "NO_CLASSNAME";
	}
	
	public String getPackageName(BeanAttribute beanAttribute) {
		if (beanAttribute==null)
			return "ATTRIBUTE_DOES_NOT_HAVE_PACKAGE";
		String packageName = beanAttribute.getPackageName();
		if (packageName==null)
			return "ATTRIBUTE_DOES_NOT_HAVE_PACKAGE";
		if (!packageName.equals(""))
			return packageName;
		Bean attributeBean = getBean(beanAttribute);
		if (attributeBean==null)
			return "ATTRIBUTE_DOES_NOT_HAVE_PACKAGE";		
		return attributeBean.getPackageName();
	}
	
	public String getPackageName(BeanMapping beanMapping, Template template) {
		return beanMapping.getPackageName();
	}
	
	public String getPackageName (Bean bean) {
		return getPackageForBean(bean);
	}
	
	public String getPackageForBean (BeanMapping beanMapping, String beanName) {
		return getPackageForBean(getBean(beanMapping, beanName));
	}
	
	public String getPackageForBean (BeanMap beanMap, String beanName) {
		return getPackageForBean(getBean(beanMap, beanName));
	}
	
	public String getPackageForBean (Bean bean) {
		if (bean!=null)
			return bean.getPackageName();
		return "NO_PACKAGE_SPECIFIED";
	}
	
	public Bean getBean (BeanMap beanMap, String beanName) { 
		for (Bean bean: beanMap.getBeans().getBeans()) {
			if (bean.getName().equals(beanName))
				return bean;
		}
		return null;		
	}
	public Bean getBean (BeanMapping beanMapping, String beanName) {
		BeanMap beanMap = beanMapping.getBeanMap();
		return getBean(beanMap, beanName);
	}
	
	public Bean getBean (BeanAttribute beanAttribute) {
		
//		BeanMap beanMap = beanMapping.getBeanMap();
		for (Bean bean: beanAttribute.getBeanMap().getBeans().getBeans()) {
			if (bean.getName().equals(beanAttribute.getRefType()))
				return bean;
		}
		// no reference found
		return null;
	}

	public String getBeanClassName (Bean bean, Template template) {
		if (bean!=null) {
			Template templateTarget = CommonUtils.getTargetTemplate(template, BEAN_OBJECT_TEMPLATE);
			return CommonUtils.getClassName(bean, templateTarget);
		}
		return "BEAN CANNOT BE NULL";
	}
	
	public String getBeanClassName (String beanName, BeanMapping beanMapping, Template template) {
		Bean bean = getBean(beanMapping, beanName);
		return getBeanClassName(bean, template);		
	}
	
	public String getBeanClassName (String beanName, BeanMap beanMap, Template template) {
		Bean bean = getBean(beanMap, beanName);
		return getBeanClassName(bean, template);		
	}
	
	public String getBeanClassName (BeanAttribute beanAttribute, Template template) {
		Bean bean = getBean(beanAttribute);
		if (bean!=null) {
			getBeanClassName(bean, template);
		}
		return FormatUtils.firstUpperCase(beanAttribute.getRefType());
	}
	
	public BeanMappingProperty[] getSimpleProperties (BeanMappingProperties beanMappingProperties) {
		return beanMappingProperties.getMapPropertysArray();
	}

	public boolean isCallMapping (BeanMappingProperty beanMappingProperty) {
		if (beanMappingProperty.getCallMapping()!=null 
			&& !beanMappingProperty.getCallMapping().equals(""))
			return true;
		return false;
	}

	public boolean isLoop (BeanMappingProperty beanMappingProperty) {
		if (beanMappingProperty.getIsLoop()!=null 
			&& !beanMappingProperty.getIsLoop().equals(""))
			return true;
		return false;
	}
	
	public BeanMapping getCalledBeanMapping (BeanMappingProperty beanMappingProperty) {
		if (isCallMapping(beanMappingProperty)) {
			String calledBeanMapping = beanMappingProperty.getCallMapping();
			BeanMappings beanMappings = beanMappingProperty.getBeanMap().getMappings();
			for (BeanMapping beanMapping : beanMappings.getMappings()) {
				if (beanMapping.getName().equals(calledBeanMapping))
					return beanMapping;
			}
		}
		return null; 
	}

	public int getPathLevel (BeanMappingProperty beanMappingProperty) {
		String what = beanMappingProperty.getWhat();
		if (what!=null) {
			return getPathLevel(what);
		}
		return 0;
	}
	
	public int getPathLevel (String beanPath) {
		StringTokenizer st = new StringTokenizer(beanPath, ".");
		return st.countTokens();
	}
	
	public String getAvoidNullPointerTest (BeanMapping beanMapping, BeanMappingProperty beanMappingProperty) {
		String what = beanMappingProperty.getWhat();
		String originBeanVariable = FormatUtils.getJavaNameVariable(beanMapping.getOriginBean());
		return getAvoidNullPointerTest(originBeanVariable, what);
	}
	
	public String getAvoidNullPointerTest (String rootVariable, String beanPath) {
        List<String> distinctBeanPath = getDistinctBeanPath(beanPath);	
        Iterator iter = distinctBeanPath.iterator();
        StringBuffer sb = new StringBuffer("(");
        while (iter.hasNext()) {
			String element = (String) iter.next();
			String javaPath = getJavaPathFromBeanPath(element);
			sb.append(rootVariable);
			sb.append(".");
			sb.append(javaPath);
			sb.append("!=null");
			if (iter.hasNext()) {
				sb.append(" && ");
			}
		}
        sb.append(")");
        return sb.toString();
	}
	
	private List<String> getDistinctBeanPath (String beanPath) {
		List<String> list = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(beanPath, ".");
		int i=0;
		StringBuffer sb = new StringBuffer();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			sb.append(token);
			list.add(sb.toString());
			if (st.hasMoreTokens())
				sb.append(".");
		}
		return list;
	}
	
	public String getMappingPropertyMethodResult (BeanMapping beanMapping, BeanMappingProperty beanMappingProperty) {
		String function = beanMappingProperty.getFunction();
		String what = beanMappingProperty.getWhat();
		String originBeanVariable = FormatUtils.getJavaNameVariable(beanMapping.getOriginBean());
		if (function.equals("")) {
			String whatMappingMethod = getJavaPathFromBeanPath(what);
			return originBeanVariable+"."+whatMappingMethod;
		} else {
			return getFunction (originBeanVariable, beanMappingProperty);//function, what);
		}
	}
	
	private String getJavaPathFromBeanPath (String beanPath) {
		StringTokenizer st = new StringTokenizer(beanPath, ".");
		StringBuffer sb = new StringBuffer();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			String tokenJavaName = FormatUtils.firstUpperCase(token);
			sb.append("get");
			sb.append(tokenJavaName);
			sb.append("()");
			if (st.hasMoreTokens())
				sb.append(".");
		}
		return sb.toString();	
	}
	
	public String getFunction (String originBeanVariable, BeanMappingProperty beanMappingProperty) {
		String function = beanMappingProperty.getFunction();
		String what = beanMappingProperty.getWhat();
		if (function.equals("concat")) {
			return getConcatFunction (originBeanVariable, what);
		}
		if (function.equals("defaultValue")) {
			return " new "+getDefaultObjectConstructor(beanMappingProperty)+"(\""+beanMappingProperty.getValue()+"\") ";
		}		
		return "FUNCTION_NOT_AVAILABLE";
	}
	
	private String getDefaultObjectConstructor (BeanMappingProperty beanMappingProperty) {
		return " String ";
	}
	
	private String getConcatFunction(String originBeanVariable, String what) {
		StringTokenizer st = new StringTokenizer(what, ",");
		StringBuffer sb = new StringBuffer();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			String tokenJavaName = FormatUtils.getJavaName(token);
			sb.append(originBeanVariable);
			sb.append(".");
			sb.append("get");
			sb.append(tokenJavaName);
			sb.append("()");
			if (st.hasMoreTokens())
				sb.append("+");
		}
		return sb.toString();
	}
	
	// validation part
	
	public boolean isMandatoryCheck (ValidationProperty validationProperty) {
		return isPropertyToCheckBasedOnBoolean(validationProperty.getMandatory());
	}
	
	public boolean isMaxLengthCheck (ValidationProperty validationProperty) {
		return isPropertyToCheckBasedOnValue(validationProperty.getMaxLength());
	}
	
	public boolean isPropertyToCheckBasedOnBoolean (String property) {
		if (property!=null && property.equals("true"))
			return true;
		return false;
	}
	
	public boolean isPropertyToCheckBasedOnValue (String property) {
		if (property!=null && !property.equals(DEFAULT_STRING_RETURN))
			return true;
		return false;
	}
	
	public String getValidationMethodName (ValidationProperty validationProperty) {
		String name = validationProperty.getFunction();
		if (!name.equals(DEFAULT_STRING_RETURN))
			return name;
		else {
			String what = validationProperty.getWhat();
			if (!what.equals(DEFAULT_STRING_RETURN)) {		
				return getValidationMethodName(what);
			}
			return "NO_VALIDATION_NAME_FOUND"; 
		}
	}
	
	private String getValidationMethodName (String name) {
		String nameFormat = FormatUtils.firstUpperCaseOnly(name);
		return "validate"+nameFormat;		
	}
	
	public boolean isValidationOfABean (Validation validation) {
		return isPropertyToCheckBasedOnValue(validation.getBean());
	}
	
	public Bean getValidationBean (Validation validation) {
		if (isValidationOfABean(validation)) {
			return getBean(validation.getBeanMap(), validation.getBean());
		}
		return null;
	}

	public String getVariableFunctionParam (Variable variable, Bean validationBean, Template template) {
		return getVariableFunctionParam(variable, validationBean, template, false);
	}
	
	public String getVariableFunctionTypeAndParam (Variable variable, Bean validationBean, Template template) {
		return getVariableFunctionParam(variable, validationBean, template, true);
	}
	
	public String getVariableFunctionParam (Variable variable, Bean validationBean, Template template, boolean withType) {
		String functionParam = variable.getFunctionParam();
//		variable.getBeanMap();
		StringTokenizer st = new StringTokenizer(functionParam, ",");
		StringBuffer sb = new StringBuffer();
		while (st.hasMoreTokens()) {
			String attribute = st.nextToken();
			if (withType) {
				String tokenJavaName = getBeanAttributeClassName (validationBean, attribute);
				sb.append(tokenJavaName);
				sb.append(" ");
			}
			sb.append(attribute);
			if (st.hasMoreTokens())
				sb.append(", ");
		}
		return sb.toString();
	}

	public String getBeanDefaultSuperOtherwiseSpecifiedInfo (BeanMap beanMap, Bean bean, String info) {
		String superInfo = getBeanSuperInfo (beanMap, bean, info);
		if (superInfo!=null && !superInfo.equals(DEFAULT_STRING_RETURN))
			return superInfo;
		return getBeanDefaultSuperInfo (info);
	}
	
	private String getBeanSuperInfo (BeanMap beanMap, Bean bean, String info) {
		if (!bean.getExtendType().equals(DEFAULT_STRING_RETURN)) {
			Bean superBean = getBean(bean.getBeanMap(), bean.getExtendType());
			if (superBean!=null) {
				if (info.equals(NAME_INFO))
				    return superBean.getName();
				if (info.equals(PACKAGE_INFO))
				    return superBean.getPackageName();				
			}
		}
		return DEFAULT_STRING_RETURN;
	}
	
	public String getBeanSuperName (Template template, BeanMap beanMap, Bean bean) {
		return getBeanSuperInfo(template, beanMap, bean, NAME_INFO);
	}
	
	public String getBeanSuperPackage (Template template, BeanMap beanMap, Bean bean) {
		return getBeanSuperInfo(template, beanMap, bean, PACKAGE_INFO);
	}
	
	private String getBeanSuperInfo (Template template, BeanMap beanMap, Bean bean, String info) {
		if (template.getIsTrueProperty(DEFAULT_SUPER_CLASS_OTHERWISE_SPECIFIED)) 
			return getBeanDefaultSuperOtherwiseSpecifiedInfo(beanMap, bean, info);
		if (template.getIsTrueProperty(DEFAULT_SUPER_CLASS)) 
			return getBeanDefaultSuperInfo (info);
		return getBeanSuperInfo(beanMap, bean, info);
	}
	
	private String getBeanDefaultSuperInfo (String info) {
		if (info.equals(NAME_INFO))
		    return "DataTransferObject";
		if (info.equals(PACKAGE_INFO))
		    return "net.sf.minuteProject.architecture.bsla.dto";		
		return DEFAULT_STRING_RETURN;
	}
	
	public boolean isToGenerateBasedOnIsNotInPackage(Template template, GeneratorBean generatorBean) {
		return !isToGenerateBasedOnIsInPackage(template, generatorBean);
	}
	
	public boolean isToGenerateBasedOnIsInPackage(Template template, GeneratorBean generatorBean) {
	    if (generatorBean instanceof Bean) {
	    	Bean bean = (Bean)generatorBean;
	    	return bean.isInPackage();
	    }
	    if (generatorBean instanceof Service) {
	    	Service service = (Service)generatorBean;
	    	return service.isInPackage();
	    }	    
	    return false;		
	}
	
	public Validation getValidator(ValidationProperty validationProperty) {
		if (!validationProperty.getValidator().equals("")){
		   return getValidatorFromValidationProperty(validationProperty);
		}
		return null;
	}
	
	public Validation getValidatorFromValidationProperty(ValidationProperty validationProperty) {
		for (Validation validation : validationProperty.getBeanMap().getValidations().getValidations()) {
			if (validation.getName()!=null && validation.getName().equals(validationProperty.getValidator()))
				return validation;
		}
		return null;
	}
	
	public String getValidatorClassName (Validation validation, Template template) {
		if (validation!=null) {
			Template templateTarget = CommonUtils.getTargetTemplate(template, VALIDATION_WRAPPER_TEMPLATE);
			return CommonUtils.getClassName(validation, templateTarget);
		}
		return "VALIDATION CANNOT BE NULL";
	}
}
