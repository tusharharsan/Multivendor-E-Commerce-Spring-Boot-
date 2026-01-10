package com.Tushar.Ecommerce.Multivendor.Service.impl;

import com.Tushar.Ecommerce.Multivendor.Modal.Cart;
import com.Tushar.Ecommerce.Multivendor.Modal.User;
import com.Tushar.Ecommerce.Multivendor.Modal.VerificationCode;
import com.Tushar.Ecommerce.Multivendor.Repository.CartRepository;
import com.Tushar.Ecommerce.Multivendor.Repository.UserRespository;
import com.Tushar.Ecommerce.Multivendor.Repository.VerificationCodeRepository;
import com.Tushar.Ecommerce.Multivendor.Request.LoginRequest;
import com.Tushar.Ecommerce.Multivendor.Response.AuthResponse;
import com.Tushar.Ecommerce.Multivendor.Response.SignupRequest;
import com.Tushar.Ecommerce.Multivendor.Service.AuthService;
import com.Tushar.Ecommerce.Multivendor.Service.EmailService;
import com.Tushar.Ecommerce.Multivendor.Service.Utils.OtpUtil;
import com.Tushar.Ecommerce.Multivendor.SpringSecurity.JwtProvider;
import com.Tushar.Ecommerce.Multivendor.domain.USER_ROLE;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRespository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final CartRepository cartRepository;

    private final JwtProvider jwtProvider;

    private final VerificationCodeRepository verificationCodeRepository;

    private final EmailService emailService;

    private final CustomUserServiceImpl customUserService;

    @Override
    public String createUser(SignupRequest req) throws Exception {

        VerificationCode verificationCode = verificationCodeRepository.findByEmail(req.getEmail());

        if(verificationCode == null || !verificationCode.getOtp().equals(req.getOtp())){
            throw new Exception("wrong otp...");
        }

        User user = userRepository.findByEmail(req.getEmail());
        if (user == null) {
            User createdUser = new User();
            createdUser.setEmail(req.getEmail());
            createdUser.setFullName(req.getFullName());
            createdUser.setRole(USER_ROLE.ROLE_CUSTOMER);
            createdUser.setPhoneNumber("1234567890");
            createdUser.setPassword(passwordEncoder.encode(req.getOtp()));

            user = userRepository.save(createdUser);

            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(USER_ROLE.ROLE_CUSTOMER.toString()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(req.getEmail() , null , authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtProvider.generateToken(authentication);
    }

    @Override
    public void sentLoginOtp(String email) throws Exception {
        String SIGINING_PREFIX = "signin_";

        if(email.startsWith(SIGINING_PREFIX)){
            email = email.substring(SIGINING_PREFIX.length());

            User user = userRepository.findByEmail(email);
            if(user == null){
                throw new Exception("User not found with email - "+email);
            }

        }

        VerificationCode isExist = verificationCodeRepository.findByEmail(email);
        if(isExist != null){
            verificationCodeRepository.delete(isExist);
        }

        String otp = OtpUtil.generateOtp();

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setEmail(email);
        verificationCode.setOtp(otp);

        verificationCodeRepository.save(verificationCode);

        String subject = "E-Comm signup/login OTP";
        String body = "Your One Time Password(OTP) for signup/login is "+otp+". It is valid for 10 minutes.";

        emailService.sendVerificationOptEmail(email , otp ,subject , body);
    }

    @Override
    public AuthResponse singin(LoginRequest req) {
        String username = req.getEmail();
        String otp = req.getOtp();

        Authentication authentication = authenticate(username , otp);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Signin Successful");

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String rolename = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();

        authResponse.setRole(USER_ROLE.valueOf(rolename));
        return authResponse;
    }

    private Authentication authenticate(String username, String otp) {
        UserDetails user = customUserService.loadUserByUsername(username);
        if(user==null){
            throw new BadCredentialsException("Invalid username or password");
        }

        VerificationCode verificationCode = verificationCodeRepository.findByEmail(username);
        if(verificationCode == null || !verificationCode.getOtp().equals(otp)){
            throw new BadCredentialsException("Wrong OTP");
        }


        return new UsernamePasswordAuthenticationToken(user , null , user.getAuthorities());

    }
}
