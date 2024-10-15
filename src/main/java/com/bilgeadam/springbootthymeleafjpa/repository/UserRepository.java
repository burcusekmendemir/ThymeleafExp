package com.bilgeadam.springbootthymeleafjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bilgeadam.springbootthymeleafjpa.model.CustomUser;

public interface UserRepository extends JpaRepository<CustomUser, String>
{
}