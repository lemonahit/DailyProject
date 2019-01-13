package com.zhaotao.beanannotation.javabased;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

/**
 * Created by 陶 on 2019/1/6.
 */
@Configuration
@ImportResource("classpath:config.xml")
public class StoreConfig {

//	@Value("${url}")
//	private String url;
//
//	@Value("${jdbc.username}")
//	private String username;
//
//	@Value("${password}")
//	private String password;
//
//	@Bean
//	public MyDriverManager myDriverManager() {
//		return new MyDriverManager(url, username, password);
//	}

//  @Bean(name = "stringStore", initMethod="init", destroyMethod="destroy")
//	public Store stringStore() {
//		return new StringStore();
//	}

	@Bean(name = "stringStore")
	@Scope(value="prototype")
	public Store stringStore() {
		return new StringStore();
	}

}
