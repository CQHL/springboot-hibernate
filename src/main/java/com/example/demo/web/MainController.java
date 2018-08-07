package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller		//标记的class组件自动扫描注册为Bean
public class MainController {
	@RequestMapping("/")		//映射URL路径
	@ResponseBody				//返回的实体
	String home() {
		return "hello spring boot world!";
	}
}
