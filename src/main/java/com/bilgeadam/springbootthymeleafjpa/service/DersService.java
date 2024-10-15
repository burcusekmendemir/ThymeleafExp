package com.bilgeadam.springbootthymeleafjpa.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.bilgeadam.springbootthymeleafjpa.model.Ders;
import com.bilgeadam.springbootthymeleafjpa.repository.DersRepository;

@Service
public class DersService
{
	private DersRepository dersRepository;

	public DersService(DersRepository dersRepository)
	{
		this.dersRepository = dersRepository;
	}

	public Ders save(Ders ders)
	{
		return dersRepository.save(ders);
	}

	public void deleteByID(long id)
	{
		dersRepository.deleteById(id);
	}

	public Ders getById(long id)
	{
		return dersRepository.findById(id).orElse(null);
	}

	public List<Ders> getAll()
	{
		return dersRepository.findAll(Sort.by(Order.asc("ID")));
	}
}
