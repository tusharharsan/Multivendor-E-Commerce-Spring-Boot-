package com.Tushar.Ecommerce.Multivendor.Repository;

import com.Tushar.Ecommerce.Multivendor.Modal.Seller;
import com.Tushar.Ecommerce.Multivendor.domain.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller ,Long> {
    Seller findByEmail(String email);
    List<Seller> findByAccountStatus(AccountStatus accountStatus);
}
