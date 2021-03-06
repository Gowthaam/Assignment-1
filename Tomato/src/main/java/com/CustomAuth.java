package com;

import java.util.Collections;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuth implements AuthenticationProvider{
static String username = new String();
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		// TODO Auto-generated method stub
		 username = auth.getName();
		String pass = auth.getCredentials().toString();
		
		if(AppController.users.containsKey(username))
		{
		String password = AppController.users.get(username); 
			
		if(password.equals(pass))
			return new UsernamePasswordAuthenticationToken(username,pass,Collections.EMPTY_LIST);
		else
			throw new BadCredentialsException("authentication failed");
		
		}
		else
			throw new BadCredentialsException("authentication failed");
	}

	@Override
	public boolean supports(Class<?> auth) {
		// TODO Auto-generated method stub
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}
	

}
