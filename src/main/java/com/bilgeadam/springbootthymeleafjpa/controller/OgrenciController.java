package com.bilgeadam.springbootthymeleafjpa.controller;

import java.util.List;

import com.bilgeadam.springbootthymeleafjpa.validation.OgrenciValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.bilgeadam.springbootthymeleafjpa.model.Ogrenci;
import com.bilgeadam.springbootthymeleafjpa.service.OgrenciService;

import jakarta.servlet.http.HttpServletRequest;

// localhost:8080/ogrenci
@Controller
@RequestMapping(path = "ogrenci")
public class OgrenciController
{
	private OgrenciService ogrenciService;
	private OgrenciValidator ogrenciValidator;

	public OgrenciController(OgrenciService ogrenciService, OgrenciValidator ogrenciValidator)
	{
		this.ogrenciService = ogrenciService;
		this.ogrenciValidator = ogrenciValidator;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		if (binder.getTarget() instanceof Ogrenci) {
			binder.addValidators(new OgrenciValidator());
		}
	}

	@ExceptionHandler(value = BindException.class)
	public ModelAndView exception(HttpServletRequest request, Errors errors){
		ModelAndView ogrenciView = new ModelAndView("redirect:kaydet");
		Ogrenci ogrenci=new Ogrenci();
		ogrenciView.addObject("ogrenci", ogrenci);
		for (ObjectError error : errors.getAllErrors()){
			if (error.getCode().equals("yearinvalid")){
				System.out.println("--------> Year invalid");
			}
			if (error.getCode().equals("numberinvalid")){
				System.out.println("--------> OGR_NUMBER invalid");
			}
		}
		return ogrenciView;
	}


	@ModelAttribute("requestURI")
	public String requestURI(final HttpServletRequest request)
	{
		return request.getRequestURI();
	}

	@GetMapping(path = { "", "/" })
	public ModelAndView ogrenci()
	{
		ModelAndView ogrenciView = new ModelAndView("ogrenciler");
		List<Ogrenci> liste = ogrenciService.getAll();
		ogrenciView.addObject("ogrenci_list", liste);
		return ogrenciView;
	}

	@GetMapping(path = "detay")
	public ModelAndView detay(@RequestParam(name = "id") Long id)
	{
		ModelAndView ogrenciView = new ModelAndView("ogrenci_detay");
		Ogrenci ogrenci = ogrenciService.getById(id);
		ogrenciView.addObject("ogrenci", ogrenci);
		return ogrenciView;
	}

	@GetMapping(path = "guncelle")
	public ModelAndView guncelle(@RequestParam(name = "id") Long id)
	{
		ModelAndView ogrenciView = new ModelAndView("ogrenci_guncelle");
		Ogrenci ogrenci = ogrenciService.getById(id);
		ogrenciView.addObject("ogrenci", ogrenci);
		return ogrenciView;
	}

	@PostMapping(path = "guncelle")
	public ModelAndView guncellePost(@ModelAttribute(value = "ogrenci") @Validated Ogrenci ogrenci)
	{
		ogrenciService.save(ogrenci);
		return new ModelAndView("redirect:/ogrenci");
	}

	@GetMapping(path = "kaydet")
	public ModelAndView kaydet()
	{
		ModelAndView ogrenciView = new ModelAndView("ogrenci_kaydet");
		Ogrenci ogrenci = new Ogrenci();
		ogrenciView.addObject("ogrenci", ogrenci);
		return ogrenciView;
	}

	@PostMapping(path = "kaydet")
	public ModelAndView kaydetPost(@ModelAttribute(name = "ogrenci") Ogrenci ogrenci)
	{
		ogrenciService.save(ogrenci);
		return new ModelAndView("redirect:/ogrenci");
	}

	@PostMapping(path = "sil")
	public ModelAndView sil(@ModelAttribute(name = "OGR_ID") Long id)
	{
		ogrenciService.deleteById(id);
		return new ModelAndView("redirect:/ogrenci");
	}
}