package com.bilgeadam.springbootthymeleafjpa.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.bilgeadam.springbootthymeleafjpa.model.Ogretmen;
import com.bilgeadam.springbootthymeleafjpa.repository.OgretmenRepository;

@Service
public class OgretmenService
{
	private OgretmenRepository ogretmenRepository;
	// private final java.util.logging.Logger defaultLogger = java.util.logging.Logger.getLogger("OgretmenService");
	//private final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
	private final org.slf4j.Logger slf4jLogger = org.slf4j.LoggerFactory.getLogger("OgretmenService");
	//private final org.apache.logging.log4j.Logger log4jLogger = LogManager.getLogger(this.getClass());
	//private final org.apache.logging.log4j.Logger log4jLogger = LogManager.getLogger("OgretmenService");

	public OgretmenService(OgretmenRepository ogretmenRepository)
	{
		this.ogretmenRepository = ogretmenRepository;
	}

	public Ogretmen save(Ogretmen ogretmen)
	{
		slf4jLogger.info("Öğretmen kayıt");
		return ogretmenRepository.save(ogretmen);
	}

	public List<Ogretmen> getAll()
	{
		slf4jLogger.info("Öğretmen getall");
		return ogretmenRepository.findAll(Sort.by(Order.asc("ID")));
	}

	public List<Ogretmen> getAllLike(String name)
	{
		return ogretmenRepository.findAllByNAMELikeIgnoreCase("%" + name + "%");
	}

	public void deleteById(Long id)
	{
		slf4jLogger.warn("Öğretmen deletebyid {}", id);
		ogretmenRepository.deleteById(id);
	}

	public Ogretmen getById(Long id)
	{
		slf4jLogger.info("Öğretmen getbyid {} ", id);
		return ogretmenRepository.findById(id).orElse(null);

	}
}
