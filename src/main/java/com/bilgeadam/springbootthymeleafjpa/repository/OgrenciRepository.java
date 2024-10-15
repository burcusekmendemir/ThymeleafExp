package com.bilgeadam.springbootthymeleafjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bilgeadam.springbootthymeleafjpa.model.Ogrenci;

public interface OgrenciRepository extends JpaRepository<Ogrenci, Long>
{
	List<Ogrenci> findAllByNAMELikeIgnoreCase(String name);
}
