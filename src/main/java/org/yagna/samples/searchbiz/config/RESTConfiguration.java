package org.yagna.samples.searchbiz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import se.walkercrou.places.GooglePlaces;

@org.springframework.context.annotation.Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.yagna.samples.searchbiz")
@PropertySource("classpath:/application.properties")
public class RESTConfiguration {
	
	@Value("${google.api.key}") 
    private String googleApiKey;
	
	@Bean
	public View jsonTemplate() {
		MappingJackson2JsonView view = new MappingJackson2JsonView();
		view.setPrettyPrint(true);
		return view;
	}

	@Bean
	public ViewResolver viewResolver() {
		return new BeanNameViewResolver();
	}

	@Bean
	public GooglePlaces googlePlaces() {
		return new GooglePlaces(this.googleApiKey);
	}

	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		RequestMappingHandlerMapping mappping = new RequestMappingHandlerMapping();
		mappping.setUseSuffixPatternMatch(false);
		return mappping;
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
	    return new PropertySourcesPlaceholderConfigurer();
	}

}