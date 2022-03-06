package com.vmart.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vmart.model.User;

public class UserDetailsImpl implements UserDetails {
	
	/*
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private boolean enabled;
	private List<GrantedAuthority> authorities;

	public UserDetailsImpl(User user) {
		super();
		this.username = user.getName();
		this.password = user.getPassword();
		this.enabled = user.isEnabled();
		this.authorities = Arrays.stream(user.getRole().split(","))
									.map(SimpleGrantedAuthority::new)
									.collect(Collectors.toList());
	}

	public UserDetailsImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}

}

