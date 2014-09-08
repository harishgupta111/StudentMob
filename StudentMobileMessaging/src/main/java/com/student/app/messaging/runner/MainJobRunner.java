package com.student.app.messaging.runner;

import javax.jms.JMSException;
import javax.jms.Topic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.student.app.messaging.IAdminService;

public class MainJobRunner {
	
	public static void main(String[] args) throws JMSException {
		MainJobRunner runner = new MainJobRunner();
		runner.run();
	}

	public void run() throws JMSException {
		
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:BeansJMS.xml");
		IAdminService iAdminService = (IAdminService) context.getBean("iAdminService");
		Topic topic = iAdminService.createTopic("HolidayTopic");
		System.out.println(topic.getTopicName());
	}

}
