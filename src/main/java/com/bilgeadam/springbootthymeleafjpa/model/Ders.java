package com.bilgeadam.springbootthymeleafjpa.model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "DERS")
public class Ders
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long ID;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_OGRETMEN"), name = "OGRETMEN_ID")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Ogretmen ogretmen;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_KONU"), name = "KONU_ID")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Konu konu;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ders")
	private Set<DersOgrenci> dersogrenciler = new HashSet<DersOgrenci>();

	public Ders()
	{
	}

	public Ders(Long iD, Ogretmen ogretmen, Konu konu)
	{
		ID = iD;
		this.ogretmen = ogretmen;
		this.konu = konu;
	}

	public Ders(Ogretmen ogretmen, Konu konu)
	{
		this.ogretmen = ogretmen;
		this.konu = konu;
	}

	public Long getID()
	{
		return ID;
	}

	public void setID(Long iD)
	{
		ID = iD;
	}

	public Ogretmen getOgretmen()
	{
		return ogretmen;
	}

	public void setOgretmen(Ogretmen ogretmen)
	{
		this.ogretmen = ogretmen;
	}

	public Konu getKonu()
	{
		return konu;
	}

	public void setKonu(Konu konu)
	{
		this.konu = konu;
	}

	public Set<DersOgrenci> getDersogrenciler()
	{
		return dersogrenciler;
	}

	public void setDersogrenciler(Set<DersOgrenci> dersogrenciler)
	{
		this.dersogrenciler = dersogrenciler;
	}
}
