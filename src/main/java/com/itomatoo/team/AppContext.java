/**  
 * Project Name:framework  
 * File Name:AppContext.java  
 * Package Name:org.try4.jewelry.framework  
 * Date:2015年11月28日下午5:10:27  
 * Copyright (c) 2015, weibin.ji@try4.org All Rights Reserved.  
 *  
 */
package com.itomatoo.team;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * ClassName: AppContext <br/>
 * TODO <一句话描述方法的功能> date: 2015年11月28日 下午5:10:27 <br/>
 * 
 * @author weibin.ji@try4.org
 * @version
 * @since JDK 1.6
 */
@Component
public class AppContext {

	// local variables
	/**
	 * spring context
	 */
	private static ApplicationContext context;

	static {
		context = new ClassPathXmlApplicationContext(new String[] { "classpath*:applicationContext-bean.xml",
				"classpath*:applicationContext-dao.xml", "classpath*:applicationContext-service.xml" });
	}

	/**
	 * get context
	 * 
	 * @return
	 */
	public static ApplicationContext getContext() {
		if (context == null) {
			context = new ClassPathXmlApplicationContext("classpath*:**/applicationContext*.xml");
		}
		return context;
	}

	/**
	 * return context bean by type
	 * 
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T bean(Class<?> type) {
		if (context == null) {
			context = new ClassPathXmlApplicationContext("classpath*:**/applicationContext*.xml");
		}
		return (T) BeanFactoryUtils.beanOfType(context, type);
	}

	/**
	 * return context bean by name
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T bean(String name) {
		if (context == null) {
			context = new ClassPathXmlApplicationContext("classpath*:**/applicationContext*.xml");
		}
		return (T) context.getBean(name);
	}
}
