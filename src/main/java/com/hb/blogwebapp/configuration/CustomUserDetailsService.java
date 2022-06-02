package com.hb.blogwebapp.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hb.blogwebapp.dto.RoleDTO;
import com.hb.blogwebapp.dto.UserDTO;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDTO user = new UserDTO();
//		if (user == null) {
//			throw new UsernameNotFoundException(username + " not found");
//		}
		User userDetails = new User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user.getRoles()));
		return userDetails;
	}

	private Collection<? extends GrantedAuthority> getGrantedAuthorities(List<RoleDTO> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (RoleDTO role : roles) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		}
		return authorities;
	}

}
