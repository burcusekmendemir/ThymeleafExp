package com.bilgeadam.springbootthymeleafjpa.model;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class CustomUser implements UserDetails // extends User // yapılabilir
{
	private static final long serialVersionUID = 2180906674715983048L;

	@Id
	@Column(length = 50, nullable = false)
	private String username = "";

	@Column(length = 150, nullable = false)
	private String password = "";

	private boolean enabled = true;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles;

	@Override
	public List<Role> getAuthorities()
	{
		return roles;
	}

	@Override
	public String getPassword()
	{
		return password;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	@Override
	public String getUsername()
	{
		return username;
	}

	@Override
	public boolean isEnabled()
	{
		return enabled;
	}

	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}
}