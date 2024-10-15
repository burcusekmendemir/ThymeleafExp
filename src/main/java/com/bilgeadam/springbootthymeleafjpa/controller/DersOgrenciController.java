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
import com.bilgeadam.springbootthymeleafjpa.model.DersOgrenci;
import com.bilgeadam.springbootthymeleafjpa.model.Ogrenci;
import com.bilgeadam.springbootthymeleafjpa.service.DersOgrenciService;
import com.bilgeadam.springbootthymeleafjpa.service.DersService;
import com.bilgeadam.springbootthymeleafjpa.service.OgrenciService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "dersogrenci")
public class DersOgrenciController
{
	private DersOgrenciService dersOgrenciService;

	private OgrenciService ogrenciService;

	private DersService dersService;

	public DersOgrenciController(DersOgrenciService dersOgrenciService, OgrenciService ogrenciService, DersService dersService)
	{
		this.dersOgrenciService = dersOgrenciService;
		this.ogrenciService = ogrenciService;
		this.dersService = dersService;
	}

	@ModelAttribute("requestURI")
	public String requestURI(final HttpServletRequest request)
	{
		return request.getRequestURI();
	}

	@GetMapping(path = { "", "/" })
	public ModelAndView dersOgrenci()
	{
		ModelAndView dersOgrenciView = new ModelAndView("dersogrenciler");
		List<DersOgrenci> liste = dersOgrenciService.getAll();
		dersOgrenciView.addObject("dersogrenci_list", liste);
		return dersOgrenciView;
	}

	@GetMapping(path = "detay")
	public ModelAndView detay(@RequestParam(name = "id") Long id)
	{
		ModelAndView dersOgrenciView = new ModelAndView("dersogrenci_detay");
		DersOgrenci dersOgrenci = dersOgrenciService.getById(id);
		dersOgrenciView.addObject("dersogrenci", dersOgrenci);
		return dersOgrenciView;
	}

	@GetMapping(path = "guncelle")
	public ModelAndView guncelle(@RequestParam(name = "id") Long id)
	{
		ModelAndView dersOgrenciView = new ModelAndView("dersogrenci_guncelle");
		DersOgrenci dersOgrenci = dersOgrenciService.getById(id);
		dersOgrenciView.addObject("dersogrenci", dersOgrenci);
		List<Ders> ders_list = dersService.getAll();
		dersOgrenciView.addObject("ders_list", ders_list);
		List<Ogrenci> ogrenci_list = ogrenciService.getAll();
		dersOgrenciView.addObject("ogrenci_list", ogrenci_list);
		return dersOgrenciView;
	}

	@PostMapping(path = "guncelle")
	public ModelAndView guncellePost(@ModelAttribute(value = "dersogrenci") DersOgrenci dersOgrenci)
	{
		dersOgrenciService.save(dersOgrenci);
		return new ModelAndView("redirect:/dersogrenci");
	}

	@GetMapping(path = "kaydet")
	public ModelAndView kaydet()
	{
		ModelAndView dersOgrenciView = new ModelAndView("dersogrenci_kaydet");
		DersOgrenci dersOgrenci = new DersOgrenci();
		dersOgrenciView.addObject("dersogrenci", dersOgrenci);
		List<Ders> ders_list = dersService.getAll();
		dersOgrenciView.addObject("ders_list", ders_list);
		List<Ogrenci> ogrenci_list = ogrenciService.getAll();
		dersOgrenciView.addObject("ogrenci_list", ogrenci_list);
		return dersOgrenciView;
	}

	@PostMapping(path = "kaydet")
	public ModelAndView kaydetPost(@ModelAttribute(name = "dersogrenci") DersOgrenci dersogrenci)
	{
		dersOgrenciService.save(dersogrenci);
		return new ModelAndView("redirect:/dersogrenci");
	}

	@PostMapping(path = "sil")
	public ModelAndView sil(@ModelAttribute(name = "DERSOGRENCI_ID") Long id)
	{
		dersOgrenciService.deleteById(id);
		return new ModelAndView("redirect:/dersogrenci");
	}
}
