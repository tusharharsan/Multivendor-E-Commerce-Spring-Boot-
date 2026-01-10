package com.Tushar.Ecommerce.Multivendor.Repository;

import com.Tushar.Ecommerce.Multivendor.Modal.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart , Long> {

}
