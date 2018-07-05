package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired 
	CustomAuth custauth;
	
	@Override
	public void configure (AuthenticationManagerBuilder auth) throws Exception
	{
		
		auth.authenticationProvider(custauth);
        auth.inMemoryAuthentication()
            .withUser("gowtham")
            .password("gowtham")
            .roles("ADMIN");
		
	}
	
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.httpBasic()
	            .and()
	            .authorizeRequests()
	            .antMatchers("/home","/login").permitAll()
	            .anyRequest().authenticated()
	            .and()
	            .formLogin()
	            .loginPage("/login.jsp")
	            .defaultSuccessUrl("/homepage.html")
	            .failureUrl("/login.html?error=true")
	            .and()
	            .logout().logoutSuccessUrl("/login.html");
                
	            
	    }
	     
	
	
}
