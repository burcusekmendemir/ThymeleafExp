package com.bilgeadam.springbootthymeleafjpa.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.bilgeadam.springbootthymeleafjpa.model.Konu;
import com.bilgeadam.springbootthymeleafjpa.repository.KonuRepository;

@Service
public class KonuService
{
	private KonuRepository konuRepository;

	public KonuService(KonuRepository konuRepository)
	{
		this.konuRepository = konuRepository;
	}

	public Konu save(Konu konu)
	{
		return konuRepository.save(konu);
	}

	public List<Konu> getAll()
	{
		return konuRepository.findAll(Sort.by(Order.asc("ID")));
	}

	public List<Konu> getAllLike(String name)
	{
		return konuRepository.findAllByNAMELikeIgnoreCase("%" + name + "%");
	}

	public void deleteById(Long id)
	{
		konuRepository.deleteById(id);
	}

	public Konu getById(Long id)
	{
		return konuRepository.findById(id).orElse(null);
	}

}
