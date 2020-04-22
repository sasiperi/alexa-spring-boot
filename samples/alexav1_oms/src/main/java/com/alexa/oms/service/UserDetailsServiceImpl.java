package com.alexa.oms.service;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alexa.oms.model.Customer;
import com.alexa.oms.repository.CustomerRepository;

import static com.alexa.oms.util.AlexaOmsMiscConstants.ROLE_USER;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private CustomerRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException
    {
        Customer user = userRepository.findByEmail(userId);

        if (user != null)
        {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_USER));
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
        }
        else
        {
            throw new UsernameNotFoundException("User " + userId + " was not found in the " + "database");
        }
       
    }
}