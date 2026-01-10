package com.Tushar.Ecommerce.Multivendor.Repository;

import com.Tushar.Ecommerce.Multivendor.Modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
