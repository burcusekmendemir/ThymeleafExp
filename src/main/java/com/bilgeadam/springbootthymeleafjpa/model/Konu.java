package com.bilgeadam.springbootthymeleafjpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "KONU")
public class Konu
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long ID;

	@Column(nullable = false, length = 150, name = "NAME")
	private String NAME;

	public Konu()
	{
	}

	public Konu(String nAME)
	{
		this.NAME = nAME;
	}

	public Konu(Long iD, String nAME)
	{
		this.ID = iD;
		this.NAME = nAME;
	}

	public Long getID()
	{
		return ID;
	}

	public void setID(Long iD)
	{
		ID = iD;
	}

	public String getNAME()
	{
		return NAME;
	}

	public void setNAME(String nAME)
	{
		NAME = nAME;
	}

}
