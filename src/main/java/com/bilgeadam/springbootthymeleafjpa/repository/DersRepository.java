package com.bilgeadam.springbootthymeleafjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bilgeadam.springbootthymeleafjpa.model.Ders;

public interface DersRepository extends JpaRepository<Ders, Long>
{
}
