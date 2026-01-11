package com.Tushar.Ecommerce.Multivendor.Controller;


import com.Tushar.Ecommerce.Multivendor.Modal.VerificationCode;
import com.Tushar.Ecommerce.Multivendor.Repository.VerificationCodeRepository;
import com.Tushar.Ecommerce.Multivendor.Request.LoginRequest;
import com.Tushar.Ecommerce.Multivendor.Response.ApiResponse;
import com.Tushar.Ecommerce.Multivendor.Response.AuthResponse;
import com.Tushar.Ecommerce.Multivendor.Service.AuthService;
import com.Tushar.Ecommerce.Multivendor.Service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers")
public class SellerController {

    private final SellerService sellerService;

    private final VerificationCodeRepository verificationCodeRepository;

    private final AuthService authService;

//    @PostMapping("/sent/login-otp")
//    public ResponseEntity<ApiResponse> sentOtpHandler(@RequestBody VerificationCode req) throws Exception {
//        authService.sentLoginOtp(req.getEmail());
//
//        ApiResponse res = new ApiResponse();
//        res.setMessage("OTP sent Successfully to " + req.getEmail());
//        return new ResponseEntity<>(res , null, 201);
//    }


}
