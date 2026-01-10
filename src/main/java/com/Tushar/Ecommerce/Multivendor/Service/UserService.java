package com.Tushar.Ecommerce.Multivendor.Service;

import com.Tushar.Ecommerce.Multivendor.Modal.User;

public interface UserService {
    public User findUserByJwtToken(String jwt) throws Exception;

    public User findUserByEmail(String email);
}
