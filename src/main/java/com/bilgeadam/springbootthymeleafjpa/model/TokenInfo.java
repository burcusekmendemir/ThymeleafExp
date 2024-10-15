package com.bilgeadam.springbootthymeleafjpa.model;

import java.util.List;
import java.util.stream.Collectors;

public class TokenInfo
{
	private String username;
	private String token;
	private List<String> authorities;

	public TokenInfo()
	{
	}

	public TokenInfo(String username, List<Role> authorities, String token)
	{
		this.username = username;
		this.token = token;
		this.authorities = authorities.stream().map(item -> item.getAuthority()).collect(Collectors.toList());
	}

	public List<String> getAuthorities()
	{
		// yetkileri , ile bölerek string ifadeye toplasın
		return authorities;
	}

	public void setAuthorities(List<Role> authorities)
	{
		this.authorities = authorities.stream().map(item -> item.getAuthority()).collect(Collectors.toList());
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}
}