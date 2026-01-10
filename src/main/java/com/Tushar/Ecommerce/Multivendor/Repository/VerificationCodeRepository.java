package com.Tushar.Ecommerce.Multivendor.Repository;

import com.Tushar.Ecommerce.Multivendor.Modal.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode ,Long> {

    VerificationCode findByEmail(String email);
}
