package com.Tushar.Ecommerce.Multivendor.Service;

import com.Tushar.Ecommerce.Multivendor.Modal.Seller;
import com.Tushar.Ecommerce.Multivendor.domain.AccountStatus;

import java.util.List;

public interface SellerService {
    Seller getSellerProfile(String jwt) throws Exception;
    Seller createSeller(Seller seller) throws Exception;
    Seller getSellerById(Long id) throws Exception;
    Seller getSellerByEmail(String email) throws Exception;
    List<Seller> getAllSellers(AccountStatus status);
    Seller updateSeller(Long id ,Seller seller) throws Exception;
    void deleteSeller(Long id) throws Exception;
    Seller VerifySeller(String email , String otp) throws Exception;
    Seller updateSellerAccountStatus(Long sellerid, AccountStatus status) throws Exception;

}
