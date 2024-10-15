package com.bilgeadam.springbootthymeleafjpa.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.bilgeadam.springbootthymeleafjpa.model.CustomUser;
import com.bilgeadam.springbootthymeleafjpa.repository.UserRepository;

@Component
public class UserService implements UserDetailsService
{
	private UserRepository userRepository;

	public UserService(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		//return User.withUsername("numan").password("$2a$10$WDtUVEjZuuQ7YNOfweEOu.5BxWq/yRH/LIyU9jv0g.TYjP8DfiMfC").build();
		CustomUser user = userRepository.findById(username).get();
		return user;
	}

	public CustomUser save(CustomUser user)
	{
		return userRepository.save(user);
	}
}