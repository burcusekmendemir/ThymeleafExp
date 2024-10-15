package com.bilgeadam.springbootthymeleafjpa.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bilgeadam.springbootthymeleafjpa.model.Ogretmen;
import com.bilgeadam.springbootthymeleafjpa.service.OgretmenService;

import jakarta.servlet.http.HttpServletRequest;

// localhost:8080/ogretmen
@Controller
@RequestMapping(path = "ogretmen")
public class OgretmenController
{
	private OgretmenService ogretmenService;

	public OgretmenController(OgretmenService ogretmenService)
	{
		this.ogretmenService = ogretmenService;
	}

	@ModelAttribute("requestURI")
	public String requestURI(final HttpServletRequest request)
	{
		return request.getRequestURI();
	}

	@GetMapping(path = { "", "/" })
	public ModelAndView ogretmen()
	{
		ModelAndView ogretmenView = new ModelAndView("ogretmenler");
		List<Ogretmen> liste = ogretmenService.getAll();
		ogretmenView.addObject("ogretmen_list", liste);
		return ogretmenView;
	}

	@GetMapping(path = "detay")
	public ModelAndView detay(@RequestParam(name = "id") Long id)
	{
		ModelAndView ogretmenView = new ModelAndView("ogretmen_detay");
		Ogretmen ogretmen = ogretmenService.getById(id);
		ogretmenView.addObject("ogretmen", ogretmen);
		return ogretmenView;
	}

	@GetMapping(path = "guncelle")
	public ModelAndView guncelle(@RequestParam(name = "id") Long id)
	{
		ModelAndView ogretmenView = new ModelAndView("ogretmen_guncelle");
		Ogretmen ogretmen = ogretmenService.getById(id);
		ogretmenView.addObject("ogretmen", ogretmen);
		return ogretmenView;
	}

	@PostMapping(path = "guncelle")
	public ModelAndView guncellePost(@ModelAttribute(value = "ogretmen") Ogretmen ogretmen)
	{
		ogretmenService.save(ogretmen);
		return new ModelAndView("redirect:/ogretmen");
	}

	@GetMapping(path = "kaydet")
	public ModelAndView kaydet()
	{
		ModelAndView ogretmenView = new ModelAndView("ogretmen_kaydet");
		Ogretmen ogretmen = new Ogretmen();
		ogretmenView.addObject("ogretmen", ogretmen);
		return ogretmenView;
	}

	@PostMapping(path = "kaydet")
	public ModelAndView kaydetPost(@ModelAttribute(name = "ogretmen") Ogretmen ogretmen)
	{
		ogretmenService.save(ogretmen);
		return new ModelAndView("redirect:/ogretmen");
	}

	@PostMapping(path = "sil")
	public ModelAndView sil(@ModelAttribute(name = "OGR_ID") Long id)
	{
		ogretmenService.deleteById(id);
		return new ModelAndView("redirect:/ogretmen");
	}
}