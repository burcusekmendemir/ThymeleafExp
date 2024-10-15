package com.bilgeadam.springbootthymeleafjpa.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "OGRETMEN")
public class Ogretmen
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long ID;

	@Column(length = 100, name = "NAME")
	private String NAME;

	@Column(name = "IS_GICIK")
	private boolean IS_GICIK;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "ogretmen")
	private Set<Ders> dersler = new HashSet<Ders>();

	public Ogretmen()
	{
	}

	public Ogretmen(String nAME, boolean iS_GICIK)
	{
		this.NAME = nAME;
		this.IS_GICIK = iS_GICIK;
	}

	public Ogretmen(Long iD, String nAME, boolean iS_GICIK)
	{

		this.ID = iD;
		this.NAME = nAME;
		this.IS_GICIK = iS_GICIK;
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

	public boolean isIS_GICIK()
	{
		return IS_GICIK;
	}

	public void setIS_GICIK(boolean iS_GICIK)
	{
		IS_GICIK = iS_GICIK;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(ID, IS_GICIK, NAME);
	}

	public void setDersler(Set<Ders> dersler)
	{
		this.dersler = dersler;
	}

	public Set<Ders> getDersler()
	{
		return dersler;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ogretmen other = (Ogretmen) obj;
		return Objects.equals(ID, other.ID) && IS_GICIK == other.IS_GICIK && Objects.equals(NAME, other.NAME);
	}

	@Override
	public String toString()
	{
		return "Ogretmen [ID=" + ID + ", NAME=" + NAME + ", IS_GICIK=" + IS_GICIK + "]";
	}
}
