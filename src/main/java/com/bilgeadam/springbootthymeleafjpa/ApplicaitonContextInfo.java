package com.bilgeadam.springbootthymeleafjpa;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile(value = "!prod")
public class ApplicaitonContextInfo
{
	public ApplicationContext applicationContext;

	public ApplicaitonContextInfo(ApplicationContext applicationContext)
	{
		this.applicationContext = applicationContext;
	}

	@GetMapping(path = "beans", produces = MediaType.TEXT_HTML_VALUE)
	public String setApplicationContext()
	{
		//localhost:8080/beans
		String result = "";
		result += applicationContext.getClass() + "</br>";
		String[] names = applicationContext.getBeanDefinitionNames();
		Arrays.sort(names);
		result += "</br>";
		result += "---- " + names.length + " ----";
		result += "</br>";
		for (String name : names)
		{
			result += name + " ---> " + applicationContext.getBean(name).getClass().getName() + "</br></br>";
		}
		return result;
	}
}