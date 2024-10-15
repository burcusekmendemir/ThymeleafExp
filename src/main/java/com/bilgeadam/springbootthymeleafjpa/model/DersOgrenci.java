package com.bilgeadam.springbootthymeleafjpa.model;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "DERS_OGRENCI")
public class DersOgrenci
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long ID;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_DERS"), name = "DERS_ID")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Ders ders;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_OGRENCI"), name = "OGRENCI_ID")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Ogrenci ogrenci;

	@Column(name = "DEVAMSIZLIK")
	private Integer DEVAMSIZLIK;

	@Check(constraints = "NOTE <= 100")
	@Column(name = "NOTE")
	private Integer NOTE;

	public DersOgrenci()
	{
	}

	public DersOgrenci(Long iD, Ders ders, Ogrenci ogrenci, Integer dEVAMSIZLIK, Integer nOTE)
	{
		ID = iD;
		this.ders = ders;
		this.ogrenci = ogrenci;
		DEVAMSIZLIK = dEVAMSIZLIK;
		NOTE = nOTE;
	}

	public DersOgrenci(Ders ders, Ogrenci ogrenci, Integer dEVAMSIZLIK, Integer nOTE)
	{
		this.ders = ders;
		this.ogrenci = ogrenci;
		DEVAMSIZLIK = dEVAMSIZLIK;
		NOTE = nOTE;
	}

	public Long getID()
	{
		return ID;
	}

	public void setID(Long iD)
	{
		ID = iD;
	}

	public Ders getDers()
	{
		return ders;
	}

	public void setDers(Ders ders)
	{
		this.ders = ders;
	}

	public Ogrenci getOgrenci()
	{
		return ogrenci;
	}

	public void setOgrenci(Ogrenci ogrenci)
	{
		this.ogrenci = ogrenci;
	}

	public Integer getDEVAMSIZLIK()
	{
		return DEVAMSIZLIK;
	}

	public void setDEVAMSIZLIK(Integer dEVAMSIZLIK)
	{
		DEVAMSIZLIK = dEVAMSIZLIK;
	}

	public Integer getNOTE()
	{
		return NOTE;
	}

	public void setNOTE(Integer nOTE)
	{
		NOTE = nOTE;
	}
}
