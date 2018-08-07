package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author heling
 * 	application在spring boot有两作用：配置和启动引导
 * 	放弃传统WAR部署
 * 	main方法 使得应用程序可以当做一个可执行的jar文件来运行
 *  (exclude = {DataSourceAutoConfiguration.class}) 自动扫描数据源配置
 */
//@Configuration 			//标明该类使用spring基于Java的配置
//@ComponentScan			//启用组件扫描，web控制类和其他组件才能被自动扫描，并注册为Bean
//@EnableAutoConfiguration	//开启自动配置魔力 称为abracadabra咒语
@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class})     //开启组件扫描和自动配置，作用等同于前面三个组合，springBoot1.2开始有效
//@ComponentScan(basePackages = "com.example.demo.dao")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);//启动引导应用程序
	}
}
