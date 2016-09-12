package com.sonata.raj.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
@Order(1)
public class WebApplicationInitializer  extends AbstractAnnotationConfigDispatcherServletInitializer{
	
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {	
		super.onStartup(servletContext);
		
	}
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {DefaultConfiguration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {WebMVCConfiguration.class }; 
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
