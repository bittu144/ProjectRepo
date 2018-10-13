package com.shopware.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@ComponentScan("com.shopware")
@SpringBootApplication
public class ShopwareUserManagementApp {

	public static void main(String args[]) throws Throwable {
		SpringApplication.run(ShopwareUserManagementApp.class, args);
	}

}
