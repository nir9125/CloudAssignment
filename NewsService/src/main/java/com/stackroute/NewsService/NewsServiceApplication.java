package com.stackroute.NewsService;

import com.stackroute.NewsService.filter.NewsFilter;
import jakarta.servlet.FilterRegistration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NewsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsServiceApplication.class, args);}

	@Bean
	public FilterRegistrationBean getBean(){
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new NewsFilter());
		bean.addUrlPatterns("/api/v1/*");
		bean.addUrlPatterns("/user/*");
		return bean;
	}
}
