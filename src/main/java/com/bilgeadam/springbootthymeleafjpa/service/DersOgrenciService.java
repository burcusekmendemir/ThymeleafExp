package com.bilgeadam.springbootthymeleafjpa.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.bilgeadam.springbootthymeleafjpa.model.DersOgrenci;
import com.bilgeadam.springbootthymeleafjpa.repository.DersOgrenciRepository;

@Service
public class DersOgrenciService
{
	private DersOgrenciRepository dersOgrRepository;

	public DersOgrenciService(DersOgrenciRepository dersOgrRepository)
	{
		this.dersOgrRepository = dersOgrRepository;
	}

	public DersOgrenci save(DersOgrenci dersOgr)
	{
		return dersOgrRepository.save(dersOgr);
	}

	public List<DersOgrenci> getAll()
	{
		return dersOgrRepository.findAll(Sort.by(Order.asc("ID")));
	}

	public void deleteById(Long id)
	{
		dersOgrRepository.deleteById(id);
	}

	public DersOgrenci getById(Long id)
	{
		return dersOgrRepository.findById(id).orElse(null);
	}

}
