package com.Tushar.Ecommerce.Multivendor.Service;

import com.Tushar.Ecommerce.Multivendor.Request.LoginRequest;
import com.Tushar.Ecommerce.Multivendor.Response.AuthResponse;
import com.Tushar.Ecommerce.Multivendor.Response.SignupRequest;
import com.Tushar.Ecommerce.Multivendor.domain.USER_ROLE;

public interface AuthService {

    String createUser(SignupRequest req) throws Exception;

    void sentLoginOtp(String email , USER_ROLE role) throws Exception;

    AuthResponse singin(LoginRequest req);

}
