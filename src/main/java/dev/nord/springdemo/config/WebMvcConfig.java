package dev.nord.springdemo.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import dev.nord.springdemo.interceptors.HeaderInterceptor;

@Configuration
@ComponentScan("dev.nord.springdemo")
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Bean
	public DataSource dataSource() {
		final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
		dsLookup.setResourceRef(true);
		DataSource dataSource = dsLookup.getDataSource("jdbc/springdb");
		return dataSource;
	}
	
//	@Bean
//	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
//		RequestMappingHandlerMapping rmhm = new RequestMappingHandlerMapping();
//		rmhm.setUseSuffixPatternMatch(true);
//		rmhm.setUseTrailingSlashMatch(true);
//		return rmhm;
//	}
	
	@Bean
	public UrlBasedViewResolver urlBasedViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HeaderInterceptor());
	}
	
	

}
