package net.sf.minuteProject.plugin.mapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.loader.mapping.node.Bean;
import net.sf.minuteProject.loader.mapping.node.BeanMap;
import net.sf.minuteProject.loader.mapping.node.BeanMapping;
import net.sf.minuteProject.loader.mapping.node.Call;
import net.sf.minuteProject.loader.mapping.node.Function;
import net.sf.minuteProject.loader.mapping.node.Service;
import net.sf.minuteProject.loader.mapping.node.Variable;

public class MappingServiceUtils {

	public static final String END_PACKAGE_APPENDER = "END_PACKAGE_APPENDER";
	public static final String EXPECTED_BUSINESS_PACKAGE = "EXPECTED_BUSINESS_PACKAGE";
	public static final String[] JAVA_DEFAULT_OBJECT_AND_PRIMITIVE = 
		{"Boolean",
		 "boolean",
		 "String",
		 "Integer",
		 "int",
		 "Long",
		 "long",
		 "Object"};

	MappingMapBeanUtils mappingMapBeanUtils = new MappingMapBeanUtils();
	
	public MappingServiceUtils() {
		
	}
	
	//TODO set in MappingMapBeanUtils
	public Bean getOriginTypeFromCallMappingObject (String objectName, BeanMapping beanMapping) {
		return getTypeFromCallMappingObject(objectName, beanMapping, "ORIGIN");
	}
	
	public Bean getTargetTypeFromCallMappingObject (String objectName, BeanMapping beanMapping) {
		return getTypeFromCallMappingObject(objectName, beanMapping, "TARGET");
	}
	
	public Bean getTypeFromCallMappingObject (String objectName, BeanMapping beanMapping, String direction) {
		BeanMap beanMap = beanMapping.getBeanMap();
		BeanMapping beanMappingCalled = getBeanMapping(objectName, beanMap);
		if (direction.equals("ORIGIN"))
			return mappingMapBeanUtils.getBean(beanMap, beanMappingCalled.getOriginBean()) ;	
		if (direction.equals("TARGET"))
			return mappingMapBeanUtils.getBean(beanMap, beanMappingCalled.getTargetBean()) ;
		return null;
	}
	
	protected BeanMapping getCalledBeanMapping (String objectName, BeanMapping beanMapping) {
		BeanMap beanMap = beanMapping.getBeanMap();
		return getBeanMapping(objectName, beanMap);
	}
	
	protected BeanMapping getBeanMapping (String objectName, BeanMap beanMap) {
		for (BeanMapping beanMapping : beanMap.getMappings().getMappings()) {
			if (beanMapping.getName().equals(objectName))
				return beanMapping;
		}
		return null;
	}
	
	
	//
	public String test() {
		return "test2";
	}
	
	public String getServicePackage (Template template, Service service) {
		String packageName = service.getPackageName();
		if (packageName!=null && !packageName.equals(""))
			return packageName;
		String rootPackageName = service.getRootPackageName();
		String technicalPackageName = service.getTechnicalPackageName();
		String businessPackageName = service.getBusinessPackageName();
		String servicePackageName = rootPackageName+"."+technicalPackageName+"."+businessPackageName;
		String appender = template.getPropertyValue(END_PACKAGE_APPENDER);
		if (appender!=null) {
			return servicePackageName+"."+appender;
		}
		return servicePackageName;
	}
	
	public String getFunctionReturnObject (Template template, Function function) {
		if (function==null)
			return "";
		String ret = function.getReturn();
		if (isAmongDefaultObject(ret))
			return ret;
		if (!ret.equals(""))
			return mappingMapBeanUtils.getBeanClassName(ret, function.getBeanMap(), template);
		String holder = function.getHolder();
		if (function.isReturnWrappedInHolder()==true && !holder.equals(""))
		    return mappingMapBeanUtils.getBeanClassName(holder, function.getBeanMap(), template);
		return "void";
	}
	
	public String getFunctionImportReturnObject (Template template, Function function) {
		if (function==null)
			return "";
		String ret = function.getReturn();
		if (isAmongDefaultObject(ret))
			return "";
		if (!ret.equals("")) {
//			Bean bean =  mappingMapBeanUtils.getBean(function.getBeanMap(), ret);
//			return mappingMapBeanUtils.getPackageForBean(bean);
			return mappingMapBeanUtils.getPackageForBean(function.getBeanMap(), ret);
		}
		String holder = function.getHolder();
		if (function.isReturnWrappedInHolder()==true && !holder.equals(""))
			return mappingMapBeanUtils.getPackageForBean(function.getBeanMap(), holder);
//		    return mappingMapBeanUtils.getBeanClassName(holder, function.getBeanMap(), template);
		return "";		
	}
	
	private boolean isAmongDefaultObject (String input) {
		for (int i = 0; i < JAVA_DEFAULT_OBJECT_AND_PRIMITIVE.length; i++) {
			if (JAVA_DEFAULT_OBJECT_AND_PRIMITIVE[i].equals(input))
				return true;
		}
		return false;
	}
	
	public boolean isToGenerateBasedOnIsNotInPackageAndInBusinessPackage (Template template, GeneratorBean generatorBean) {
		String expectedBP = template.getPropertyValue(EXPECTED_BUSINESS_PACKAGE);
		if (expectedBP==null) 
			return false;
		boolean ret = mappingMapBeanUtils.isToGenerateBasedOnIsNotInPackage(template, generatorBean);
		if (ret) {
			if (generatorBean instanceof Service) {
				Service service = (Service) generatorBean;
				if (expectedBP.equals(service.getBusinessPackageName()))
					return true;
			}
		}
		return false;
	}
	
	public String getServiceClassName (Template template, Service service) {
		if (service==null)
			return "SERVICE_CANNOT_BE_NULL";
		if (template==null)
			return "TEMPLATE_CANNOT_BE_NULL";
		return template.getOutputFileMain(service);
	}
	
	public List<Bean> getFunctionInputObjects(Function function) {
		List<Bean> list = new ArrayList<Bean>();
//		System.out.println("function input ="+function.getInput());
		StringTokenizer st = new StringTokenizer(function.getInput(),",");
		while (st.hasMoreTokens()) {
			String s = (String) st.nextToken();
//			System.out.println("token = "+s);
			Bean bean = mappingMapBeanUtils.getBean(function.getBeanMap(), s);
//			System.out.println("bean = "+bean);
			list.add(bean);
		}	
		return list;
	}

	public List<Bean> getDistinctVariables(Service service) {
		List<Bean> beans= new ArrayList<Bean>();
		Map<String, Bean> map = new HashMap<String, Bean>();
		for (Function function : service.getFunctions().getFunctions()) {
			map.putAll(getDistinctVariables(function));
		}
		beans.addAll(map.values());
		return beans;		
	}
	
	public Map<String, Bean> getDistinctVariables(Function function) {
		Map<String, Bean> map = new HashMap<String, Bean>();
		for (Variable variable : function.getVariables().getVariables()) {
			Bean bean = mappingMapBeanUtils.getBean(function.getBeanMap(), variable.getBean());
			if (bean!=null)
				map.put(variable.getName(), bean);
		}
		return map;
	}
	
	public List<Service> getDistinctCalledServices(Service service) {
		List<Service> services= new ArrayList<Service>();
		Map<String, Service> map = new HashMap<String, Service>();
		for (Function function : service.getFunctions().getFunctions()) {
			for (Call call : function.getCalls().getCalls()) {
				Service serviceCalled = getCalledService(call);
				if (serviceCalled!=null)
					map.put(call.getService(), serviceCalled);
			}		
		}		
		services.addAll(map.values());
		return services;		
	}
	
	public Service getCalledService (Call call) {
		return getService(call.getBeanMap(), call.getService());
	}
	
    private Service getService (BeanMap beanMap, String serviceName) {
    	if (serviceName!=null) {
	    	for (Service service : beanMap.getServices().getServices()) {
	    		String name = service.getName();
	    		if (name==null)
	    			break;
				if (service.getName().equals(serviceName))
					return service;
			}
    	}
    	return null;
    }
	
}
