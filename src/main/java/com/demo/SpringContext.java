package com.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContext {
	private static ApplicationContext instance;
	public static void load(String path){
		instance = new ClassPathXmlApplicationContext(path);
	}
	public static <T> T getBean(Class<T> clazz) {
		return SpringContext.instance.getBean(clazz);
	}
	public static Object getBean(String name) {
		return SpringContext.instance.getBean(name);
	}
}
