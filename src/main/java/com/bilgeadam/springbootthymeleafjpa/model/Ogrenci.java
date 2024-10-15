package com.bilgeadam.springbootthymeleafjpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "OGRENCI")
public class Ogrenci
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long ID;

	@Column(name = "NAME")
	private String NAME;

	@Column(unique = true, name = "OGR_NUMBER")
	private Long OGR_NUMBER;

	@Column(name = "YEAR")
	private Long YEAR;

	public Ogrenci()
	{
	}

	public Ogrenci(String nAME, Long oGR_NUMBER, Long yEAR)
	{
		NAME = nAME;
		OGR_NUMBER = oGR_NUMBER;
		YEAR = yEAR;
	}

	public Ogrenci(Long iD, String nAME, Long oGR_NUMBER, Long yEAR)
	{
		ID = iD;
		NAME = nAME;
		OGR_NUMBER = oGR_NUMBER;
		YEAR = yEAR;
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

	public Long getOGR_NUMBER()
	{
		return OGR_NUMBER;
	}

	public void setOGR_NUMBER(Long oGR_NUMBER)
	{
		OGR_NUMBER = oGR_NUMBER;
	}

	public Long getYEAR()
	{
		return YEAR;
	}

	public void setYEAR(Long yEAR)
	{
		YEAR = yEAR;
	}
}
