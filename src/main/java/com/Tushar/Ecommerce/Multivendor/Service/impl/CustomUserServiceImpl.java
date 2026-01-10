package com.Tushar.Ecommerce.Multivendor.Service.impl;

import com.Tushar.Ecommerce.Multivendor.Modal.User;
import com.Tushar.Ecommerce.Multivendor.Repository.UserRespository;
import com.Tushar.Ecommerce.Multivendor.domain.USER_ROLE;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CustomUserServiceImpl implements UserDetailsService {

    private UserRespository userRespository;
    private static final String SELLER_PREFIX = "seller_";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.startsWith(SELLER_PREFIX)){

        }else{
            User user =userRespository.findByEmail(username);
            if(user != null){
                return buildUserDetails(user.getEmail() , user.getPassword() ,user.getRole());
            }
        }
        return null;
    }

    private UserDetails buildUserDetails(String email, String password, USER_ROLE role) {
        if(role==null) role = USER_ROLE.ROLE_CUSTOMER;
        List<GrantedAuthority> authorityList = new ArrayList<>();

        authorityList.add(new SimpleGrantedAuthority("ROLE_"+role));

        return new org.springframework.security.core.userdetails.User(email , password , authorityList);
    }


}
