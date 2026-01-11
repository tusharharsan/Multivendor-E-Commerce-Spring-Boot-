package com.Tushar.Ecommerce.Multivendor.Controller;

import com.Tushar.Ecommerce.Multivendor.Modal.User;
import com.Tushar.Ecommerce.Multivendor.Modal.VerificationCode;
import com.Tushar.Ecommerce.Multivendor.Repository.UserRespository;
import com.Tushar.Ecommerce.Multivendor.Request.LoginOtpRequest;
import com.Tushar.Ecommerce.Multivendor.Request.LoginRequest;
import com.Tushar.Ecommerce.Multivendor.Response.ApiResponse;
import com.Tushar.Ecommerce.Multivendor.Response.AuthResponse;
import com.Tushar.Ecommerce.Multivendor.Response.SignupRequest;
import com.Tushar.Ecommerce.Multivendor.Service.AuthService;
import com.Tushar.Ecommerce.Multivendor.domain.USER_ROLE;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRespository userRepository;

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest req) throws Exception {
        String jwt = authService.createUser(req);

        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setMessage("Register Successfully");
        res.setRole(USER_ROLE.ROLE_CUSTOMER);
        return new ResponseEntity<>(res , null, 201);
    }

    @PostMapping("/sent/login-signup-otp")
    private ResponseEntity<ApiResponse> sentOtpHandler(@RequestBody LoginOtpRequest req) throws Exception {
        authService.sentLoginOtp(req.getEmail() ,req.getRole() );

        ApiResponse res = new ApiResponse();
        res.setMessage("OTP sent Successfully to " + req.getEmail());
        return new ResponseEntity<>(res , null, 201);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> LoginHandler(@RequestBody LoginRequest req) throws Exception {
        AuthResponse authResponse = authService.singin(req);

        ApiResponse res = new ApiResponse();
        res.setMessage("OTP sent Successfully to " + req.getEmail());
        return new ResponseEntity<>(authResponse , null, 201);
    }
}
