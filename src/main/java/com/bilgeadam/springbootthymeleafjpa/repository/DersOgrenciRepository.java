package com.bilgeadam.springbootthymeleafjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bilgeadam.springbootthymeleafjpa.model.DersOgrenci;

public interface DersOgrenciRepository extends JpaRepository<DersOgrenci, Long>
{
}
