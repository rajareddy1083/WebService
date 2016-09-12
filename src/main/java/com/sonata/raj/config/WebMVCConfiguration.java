package com.sonata.raj.config;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.sonata.raj"}, excludeFilters = @ComponentScan.Filter({ Configuration.class }))
public class WebMVCConfiguration extends WebMvcConfigurerAdapter{

	 @Autowired
	    @Qualifier("defaultObjectMapper")
	    private ObjectMapper objectMapper;

	    @Override
	    public void configureMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
	        messageConverters.addAll(buildMessageConverters());
	    }

	    @Override
		public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	    	configurer.defaultContentType(MediaType.APPLICATION_JSON);
		}

		@Bean
	    public List<HttpMessageConverter<?>> buildMessageConverters() {
			  List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
              // for all other objects
              MappingJackson2HttpMessageConverter jackson2ConverterOther = new MappingJackson2HttpMessageConverter();
              jackson2ConverterOther.setObjectMapper(objectMapper);

              messageConverters.add(jackson2ConverterOther);
              return messageConverters;
	    }
		  
	    @Bean
	    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
	        return new PropertySourcesPlaceholderConfigurer();
	    }
	    
	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	       registry.addResourceHandler("/css/**").addResourceLocations("/css/");
	    }
	    
	    @Bean
	    public ViewResolver configureViewResolver() {
	        InternalResourceViewResolver viewResolve = new InternalResourceViewResolver();
	        viewResolve.setPrefix("/jsp/");
	        viewResolve.setSuffix(".jsp");
	        return viewResolve;
	    }
	    
	    @Bean(name = "dataSource")
	    public DataSource getDataSource() {
	        BasicDataSource dataSource = new BasicDataSource();
	        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	        dataSource.setUrl("jdbc:mysql://localhost:3306/usersdb");
	        dataSource.setUsername("root");
	        dataSource.setPassword("secret");
	        return dataSource;
	    }
}
