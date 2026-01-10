package com.Tushar.Ecommerce.Multivendor.Repository;

import com.Tushar.Ecommerce.Multivendor.Modal.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller ,Long> {
    Seller findByEmail(String email);
}
