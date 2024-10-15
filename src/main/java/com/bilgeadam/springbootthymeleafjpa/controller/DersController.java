package com.bilgeadam.springbootthymeleafjpa.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bilgeadam.springbootthymeleafjpa.model.Ders;
import com.bilgeadam.springbootthymeleafjpa.model.Konu;
import com.bilgeadam.springbootthymeleafjpa.model.Ogretmen;
import com.bilgeadam.springbootthymeleafjpa.service.DersService;
import com.bilgeadam.springbootthymeleafjpa.service.KonuService;
import com.bilgeadam.springbootthymeleafjpa.service.OgretmenService;

import jakarta.servlet.http.HttpServletRequest;

//localhost:8080/ders
@Controller
@RequestMapping(path = "ders")
public class DersController
{
	private DersService dersService;

	private OgretmenService ogretmenService;

	private KonuService konuService;

	public DersController(DersService dersService, OgretmenService ogretmenService, KonuService konuService)
	{
		this.dersService = dersService;
		this.ogretmenService = ogretmenService;
		this.konuService = konuService;
	}

	@ModelAttribute("requestURI")
	public String requestURI(final HttpServletRequest request)
	{
		return request.getRequestURI();
	}

	@GetMapping(path = { "", "/" })
	public ModelAndView ders()
	{
		ModelAndView dersView = new ModelAndView("dersler");
		List<Ders> liste = dersService.getAll();
		dersView.addObject("ders_list", liste);
		return dersView;
	}

	@GetMapping(path = "detay")
	public ModelAndView detay(@RequestParam(name = "id") Long id)
	{
		ModelAndView dersView = new ModelAndView("ders_detay");
		Ders ders = dersService.getById(id);
		dersView.addObject("ders", ders);
		return dersView;
	}

	@GetMapping(path = "guncelle")
	public ModelAndView guncelle(@RequestParam(name = "id") Long id)
	{
		ModelAndView dersView = new ModelAndView("ders_guncelle");
		Ders ders = dersService.getById(id);
		dersView.addObject("ders", ders);
		List<Ogretmen> ogretmen_list = ogretmenService.getAll();
		dersView.addObject("ogretmen_list", ogretmen_list);
		List<Konu> konu_list = konuService.getAll();
		dersView.addObject("konu_list", konu_list);
		return dersView;
	}

	@PostMapping(path = "guncelle")
	public ModelAndView guncellePost(@ModelAttribute(value = "ders") Ders ders)
	{
		dersService.save(ders);
		return new ModelAndView("redirect:/ders");
	}

	@GetMapping(path = "kaydet")
	public ModelAndView kaydet()
	{
		ModelAndView dersView = new ModelAndView("ders_kaydet");
		Ders ders = new Ders();
		dersView.addObject("ders", ders);
		List<Ogretmen> ogretmen_list = ogretmenService.getAll();
		dersView.addObject("ogretmen_list", ogretmen_list);
		List<Konu> konu_list = konuService.getAll();
		dersView.addObject("konu_list", konu_list);
		return dersView;
	}

	@PostMapping(path = "kaydet")
	public ModelAndView kaydetPost(@ModelAttribute(name = "ders") Ders ders)
	{
		dersService.save(ders);
		return new ModelAndView("redirect:/ders");
	}

	@PostMapping(path = "sil")
	public ModelAndView sil(@ModelAttribute(name = "DERS_ID") Long id)
	{
		dersService.deleteByID(id);
		return new ModelAndView("redirect:/ders");
	}
}
