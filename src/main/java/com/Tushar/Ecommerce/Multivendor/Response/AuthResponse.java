package com.Tushar.Ecommerce.Multivendor.Response;

import com.Tushar.Ecommerce.Multivendor.domain.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private USER_ROLE role;
}
