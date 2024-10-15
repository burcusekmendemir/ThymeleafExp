package com.bilgeadam.springbootthymeleafjpa.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.bilgeadam.springbootthymeleafjpa.model.Ogrenci;
import com.bilgeadam.springbootthymeleafjpa.repository.OgrenciRepository;

@Service
public class OgrenciService
{
	private OgrenciRepository ogrenciRepository;

	public OgrenciService(OgrenciRepository ogrenciRepository)
	{
		this.ogrenciRepository = ogrenciRepository;
	}

	public Ogrenci save(Ogrenci ogrenci)
	{
		return ogrenciRepository.save(ogrenci);
	}

	public List<Ogrenci> getAll()
	{
		return ogrenciRepository.findAll(Sort.by(Order.asc("ID")));
	}

	public List<Ogrenci> getAllLike(String name)
	{
		return ogrenciRepository.findAllByNAMELikeIgnoreCase("%" + name + "%");
	}

	public void deleteById(Long id)
	{
		ogrenciRepository.deleteById(id);
	}

	public Ogrenci getById(Long id)
	{
		return ogrenciRepository.findById(id).orElse(null);
	}
}
