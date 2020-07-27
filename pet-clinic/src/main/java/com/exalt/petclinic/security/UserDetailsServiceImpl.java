package com.exalt.petclinic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.exalt.petclinic.model.Employee;
import com.exalt.petclinic.repository.EmployeeRepository;

public class UserDetailsServiceImpl implements UserDetailsService{

	 @Autowired
	    private EmployeeRepository employeeRepository;
	     
	    @Override
	    public UserDetails loadUserByUsername(String email)
	            throws UsernameNotFoundException {
	        Employee user = employeeRepository.findEmailNQ(email);
	         
	        if (user == null) {
	            throw new UsernameNotFoundException("Could not find user");
	        }
	         
	        return new MyUserDetails(user);
	    }
	 
	}