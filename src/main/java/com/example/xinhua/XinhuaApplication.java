package com.example.xinhua;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
public class XinhuaApplication implements ApplicationListener<ApplicationReadyEvent> {

	public static void main(String[] args) {
		Logger Logger = LoggerFactory.getLogger(XinhuaApplication.class);
		Logger.info("AAA-启动前-AAA");
		SpringApplication.run(XinhuaApplication.class, args);
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		System.out.println("BBB-启动了-BBB");
	}

}
