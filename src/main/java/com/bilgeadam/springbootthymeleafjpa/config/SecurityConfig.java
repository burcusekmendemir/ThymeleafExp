package com.bilgeadam.springbootthymeleafjpa.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

@Configuration
public class SecurityConfig
{
	@Bean
	@Profile(value = "test")
	public SecurityFilterChain filterChainProd(HttpSecurity httpSecurity, AuthenticationConfiguration authenticationConfiguration) throws Exception
	{
		// custom ortamlarda gereksizdir, basic authentication ile çalışır
		// httpSecurity.httpBasic(Customizer.withDefaults());
		// herşey açılsın
		// httpSecurity.authorizeHttpRequests(custom -> custom.anyRequest().permitAll());
		// sadece /ogretmen ile başlayan endpointler
		// httpSecurity.authorizeHttpRequests(custom -> custom.requestMatchers("/ogretmen/*").authenticated().anyRequest().permitAll());
		// sadece post olan endpointler
		// httpSecurity.authorizeHttpRequests(custom -> custom.requestMatchers(HttpMethod.POST).authenticated().anyRequest().permitAll());
		// sadece /ogretmen olan endpointler sadece admin yetkisinie sahip ise açılsın
		// httpSecurity.authorizeHttpRequests(custom -> custom.requestMatchers("/ogretmen/*").hasRole("USER").anyRequest().permitAll());
		// herşey kapansın
		//httpSecurity.authorizeHttpRequests(custom -> custom.anyRequest().authenticated());

		httpSecurity.authorizeHttpRequests(custom -> custom.requestMatchers("/").permitAll());
		httpSecurity.authorizeHttpRequests(custom -> custom.requestMatchers("/systemlogin").permitAll());
		httpSecurity.authorizeHttpRequests(custom -> custom.requestMatchers("/wellcome").permitAll());
		httpSecurity.authorizeHttpRequests(custom -> custom.requestMatchers("/ogretmen").permitAll());
		httpSecurity.authorizeHttpRequests(custom -> custom.requestMatchers("/ders").permitAll());
		httpSecurity.authorizeHttpRequests(custom -> custom.requestMatchers("/ogrenci").permitAll());
		httpSecurity.authorizeHttpRequests(custom -> custom.requestMatchers("/konu").permitAll());
		httpSecurity.authorizeHttpRequests(custom -> custom.requestMatchers("/dersogrenci").permitAll());
		httpSecurity.authorizeHttpRequests(custom -> custom.requestMatchers("/ogretmen/**").permitAll());
		httpSecurity.authorizeHttpRequests(custom -> custom.requestMatchers("/ders/**").permitAll());
		httpSecurity.authorizeHttpRequests(custom -> custom.requestMatchers("/ogrenci/**").permitAll());
		httpSecurity.authorizeHttpRequests(custom -> custom.requestMatchers("/konu/**").permitAll());
		httpSecurity.authorizeHttpRequests(custom -> custom.requestMatchers("/dersogrenci/**").permitAll());
		httpSecurity.authorizeHttpRequests(custom -> custom.requestMatchers("/ogretmen/sil").hasRole("ADMIN"));
		httpSecurity.authorizeHttpRequests(custom -> custom.requestMatchers("/ders/sil").hasRole("ADMIN"));
		httpSecurity.authorizeHttpRequests(custom -> custom.requestMatchers("/ogrenci/sil").hasRole("ADMIN"));
		httpSecurity.authorizeHttpRequests(custom -> custom.requestMatchers("/konu/sil").hasRole("ADMIN"));
		httpSecurity.authorizeHttpRequests(custom -> custom.requestMatchers("/dersogrenci/sil").hasRole("ADMIN"));
		httpSecurity.authorizeHttpRequests(cus -> cus.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll());

		// CSRF bir şekilde konfigüre edilmeli
		httpSecurity.csrf(custom -> custom.disable());
		// mvc olduğu için if_required
		// httpSecurity.sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));

		//login ve logout successhandler' lar ile giriş çıkış eventi yakalanabilir.
		httpSecurity.exceptionHandling(cus -> cus.accessDeniedPage("/systemlogin"));
		httpSecurity.formLogin(custom -> custom.loginPage("/systemlogin").loginProcessingUrl("/login").defaultSuccessUrl("/ogretmen").failureHandler(loginFailHandler()));
		httpSecurity.logout(custom -> custom.logoutSuccessUrl("/ogretmen"));
		return httpSecurity.build();
	}

	private AuthenticationFailureHandler loginFailHandler(){
		return new AuthenticationFailureHandler(){

			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
				if (exception!=null){
					if (exception.getClass() == DisabledException.class){
						response.sendRedirect("/systemlogin?err=1");
					} else if (exception.getClass() == BadCredentialsException.class) {
						response.sendRedirect("/systemlogin?err=2");
					} else if (exception.getClass() == InternalAuthenticationServiceException.class) {
						response.sendRedirect("/systemlogin?err=3");
					}else {
						response.sendRedirect("/systemlogin?err=4");
					}
				}
			}
		};
	}

	@Bean
	@Profile(value = "test")
	public SecurityFilterChain filterChainTest(HttpSecurity httpSecurity, AuthenticationConfiguration authenticationConfiguration) throws Exception
	{
		httpSecurity.authorizeHttpRequests(custom -> custom.anyRequest().permitAll());
		httpSecurity.csrf(custom -> custom.disable());
		httpSecurity.sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return httpSecurity.build();
	}

	@Bean
	PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}