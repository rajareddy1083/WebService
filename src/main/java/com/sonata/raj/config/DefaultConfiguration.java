package com.sonata.raj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.sonata.raj.helpers.Utils;

@Configuration
public class DefaultConfiguration {
	
	@Bean
	public Utils service(){
		return new Utils();
	}
	
	 @Bean
	    public ObjectMapper defaultObjectMapper() {
	        final ObjectMapper mapper = new ObjectMapper();
	        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
	        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	        mapper.registerModule(new JodaModule());
	        return mapper;
	    }

	

}
