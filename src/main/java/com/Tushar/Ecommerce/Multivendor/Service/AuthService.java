package com.Tushar.Ecommerce.Multivendor.Service;

import com.Tushar.Ecommerce.Multivendor.Request.LoginRequest;
import com.Tushar.Ecommerce.Multivendor.Response.AuthResponse;
import com.Tushar.Ecommerce.Multivendor.Response.SignupRequest;

public interface AuthService {

    String createUser(SignupRequest req) throws Exception;

    void sentLoginOtp(String email) throws Exception;

    AuthResponse singin(LoginRequest req);

}
