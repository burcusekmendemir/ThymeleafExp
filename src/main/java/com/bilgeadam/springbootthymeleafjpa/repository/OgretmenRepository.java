package com.bilgeadam.springbootthymeleafjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bilgeadam.springbootthymeleafjpa.model.Ogretmen;

public interface OgretmenRepository extends JpaRepository<Ogretmen, Long>
{
	List<Ogretmen> findAllByNAMELikeIgnoreCase(String name);
}