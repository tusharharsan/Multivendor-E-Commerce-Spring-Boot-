package com.Tushar.Ecommerce.Multivendor.Service.impl;

import com.Tushar.Ecommerce.Multivendor.Modal.User;
import com.Tushar.Ecommerce.Multivendor.Repository.UserRespository;
import com.Tushar.Ecommerce.Multivendor.Service.UserService;
import com.Tushar.Ecommerce.Multivendor.SpringSecurity.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRespository userRespository;

    private final JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwt(jwt);

        User user = this.findUserByEmail(email);
        if(user==null){
            throw new Exception("user not found with email-"+email);
        }
        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRespository.findByEmail(email);
        if(user == null){
            throw new RuntimeException("User not found with email-"+email);
        }

        return user;
    }
}
