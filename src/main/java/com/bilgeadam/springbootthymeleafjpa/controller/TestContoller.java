package com.bilgeadam.springbootthymeleafjpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bilgeadam.springbootthymeleafjpa.service.OgretmenService;

@Controller
public class TestContoller
{
	private OgretmenService ogretmenService;

	public TestContoller(OgretmenService ogretmenService)
	{
		this.ogretmenService = ogretmenService;
	}

	// localhost:8080/deneme
	@GetMapping(path = "deneme")
	public ModelAndView getDeneme()
	{
		ModelAndView modelAndView = new ModelAndView("deneme");
		modelAndView.addObject("ogretmenler", ogretmenService.getAll());
		return modelAndView;
	}
}