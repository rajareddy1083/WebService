package com.sonata.raj.config;

import java.net.URL;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourcePropertySource;

@Configuration
public class ApplicationPropertiesConfiguration implements EnvironmentAware {
	
	public static final String PROPERTIES_FILENAME = "config.properties";
	public static final String SYSTEM_PROPERTY = "com.sonata.config";
	
	private Environment environment;


    /**
     * Creates an instance of Sprin Environment from the appropriate file.
     */
	@Bean
	public Environment environmentConfiguration() {
		ConfigurableEnvironment env = (ConfigurableEnvironment) environment;
	
		final String configLocation = System.getProperty(SYSTEM_PROPERTY);
		boolean propertiesLoaded = false;
		
		if (configLocation != null) {
			try {
				env.getPropertySources().addFirst(new ResourcePropertySource("Local properties file", new UrlResource(configLocation)));
				propertiesLoaded = true;
			}
			catch (Exception e) {
				throw new BeanCreationException("The system property " + SYSTEM_PROPERTY + " does not point to a valid file." + configLocation, e);
			}
		}
		
		if (!propertiesLoaded) {
			try {
				URL resource = getClass().getClassLoader().getResource(PROPERTIES_FILENAME);
				env.getPropertySources().addFirst(new ResourcePropertySource("D:/Site_WS_Luna_Rail_TEst/Test-service/src/main/resources/config.properties"));
			}
			catch (Exception e) {
				throw new BeanCreationException("The " + PROPERTIES_FILENAME + " file could not be open.", e);
			}
		}
        
		return env;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	public void setEnvironment(Environment environment) {
		environment=this.environment;
	}

}
