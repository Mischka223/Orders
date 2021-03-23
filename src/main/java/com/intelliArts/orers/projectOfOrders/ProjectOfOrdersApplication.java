package com.intelliArts.orers.projectOfOrders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.servlet.ServletContext;

@SpringBootApplication
public class ProjectOfOrdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectOfOrdersApplication.class, args);

	}
	private void registerHiddenFieldFilter(ServletContext context) {
		context.addFilter("hiddenHttpMethodFilter",new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null,true,"/*");
	}
}
