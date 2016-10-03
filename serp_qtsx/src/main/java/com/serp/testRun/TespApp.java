package com.serp.testRun;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.serp.configuration.AppConfig;
import com.serp.model.Estimate;
import com.serp.model.User;
import com.serp.service.EstimateService;
import com.serp.service.StatusService;
import com.serp.service.UserService;

public class TespApp {

	public static void main(String[] args) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		UserService ser = (UserService) context.getBean("UserService");
		 
		System.err.println(ser.getAllUser().size());
		context.close();
	}

}
