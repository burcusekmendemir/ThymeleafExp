package com.bilgeadam.springbootthymeleafjpa.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bilgeadam.springbootthymeleafjpa.model.Konu;
import com.bilgeadam.springbootthymeleafjpa.service.KonuService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "konu")
public class KonuController
{
	private KonuService konuService;

	public KonuController(KonuService konuService)
	{
		this.konuService = konuService;
	}

	@ModelAttribute("requestURI")
	public String requestURI(final HttpServletRequest request)
	{
		return request.getRequestURI();
	}

	@GetMapping(path = { "", "/" })
	public ModelAndView konu()
	{
		ModelAndView konuView = new ModelAndView("konular");
		List<Konu> liste = konuService.getAll();
		konuView.addObject("konu_list", liste);
		return konuView;
	}

	@GetMapping(path = "detay")
	public ModelAndView detay(@RequestParam(name = "id") Long id)
	{
		ModelAndView konuView = new ModelAndView("konu_detay");
		Konu konu = konuService.getById(id);
		konuView.addObject("konu", konu);
		return konuView;
	}

	@GetMapping(path = "guncelle")
	public ModelAndView guncelle(@RequestParam(name = "id") Long id)
	{
		ModelAndView konuView = new ModelAndView("konu_guncelle");
		Konu konu = konuService.getById(id);
		konuView.addObject("konu", konu);
		return konuView;
	}

	@PostMapping(path = "guncelle")
	public ModelAndView guncellePost(@ModelAttribute(value = "konu") Konu konu)
	{
		konuService.save(konu);
		return new ModelAndView("redirect:/konu");
	}

	@GetMapping(path = "kaydet")
	public ModelAndView kaydet()
	{
		ModelAndView konuView = new ModelAndView("konu_kaydet");
		Konu konu = new Konu();
		konuView.addObject("konu", konu);
		return konuView;
	}

	@PostMapping(path = "kaydet")
	public ModelAndView kaydetPost(@ModelAttribute(name = "konu") Konu konu)
	{
		konuService.save(konu);
		return new ModelAndView("redirect:/konu");
	}

	@PostMapping(path = "sil")
	public ModelAndView sil(@ModelAttribute(name = "KONU_ID") Long id)
	{
		konuService.deleteById(id);
		return new ModelAndView("redirect:/konu");
	}
}
