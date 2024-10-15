package com.bilgeadam.springbootthymeleafjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bilgeadam.springbootthymeleafjpa.model.Konu;

public interface KonuRepository extends JpaRepository<Konu, Long>
{
	List<Konu> findAllByNAMELikeIgnoreCase(String string);
}
