package com.Tushar.Ecommerce.Multivendor.Service.impl;

import com.Tushar.Ecommerce.Multivendor.Modal.Address;
import com.Tushar.Ecommerce.Multivendor.Modal.Seller;
import com.Tushar.Ecommerce.Multivendor.Repository.AdressRespository;
import com.Tushar.Ecommerce.Multivendor.Repository.SellerRepository;
import com.Tushar.Ecommerce.Multivendor.Service.SellerService;
import com.Tushar.Ecommerce.Multivendor.SpringSecurity.JwtProvider;
import com.Tushar.Ecommerce.Multivendor.domain.AccountStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

    private final JwtProvider jwtProvider;

    private final PasswordEncoder passwordEncoder;

    private final AdressRespository adressRespository;

    @Override
    public Seller getSellerProfile(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwt(jwt);

        return this.getSellerByEmail(email);
    }

    @Override
    public Seller createSeller(Seller seller) throws Exception {
        Seller sellerExists = sellerRepository.findByEmail(seller.getEmail());
        if(sellerExists != null){
            throw new Exception("Account with this Email Id already exits choose different email id");
        }
        Address savedAdress = adressRespository.save(seller.getPickupaddress());
        Seller newseller = new Seller();
        newseller.setName(seller.getName());
        newseller.setEmail(seller.getEmail());
        newseller.setPassword(passwordEncoder.encode(seller.getPassword()));
        newseller.setPickupaddress(savedAdress);
        newseller.setGSTIN(seller.getGSTIN());
        newseller.setRole(seller.getRole());
        newseller.setMobile(seller.getMobile());
        newseller.setBankDetails(seller.getBankDetails());
        newseller.setBuisnessDetails(seller.getBuisnessDetails());

        return sellerRepository.save(newseller);

    }

    @Override
    public Seller getSellerById(Long id) throws Exception {
        return sellerRepository.findById(id)
                .orElseThrow(()-> new Exception("seller not found with id: " + id));
    }

    @Override
    public Seller getSellerByEmail(String email) throws Exception {
        Seller seller = sellerRepository.findByEmail(email);
        if(seller == null){
            throw new Exception("Seller not found with email: " + email);
        }
        return seller;
    }

    @Override
    public List<Seller> getAllSellers(AccountStatus status) {
        return sellerRepository.findByAccountStatus(status);
    }

    @Override
    public Seller updateSeller(Long id, Seller seller) throws Exception {
        Seller existingSeller = this.getSellerById(id);

        if(seller.getName() != null){
            existingSeller.setName(seller.getName());
        }
        if(seller.getMobile() != null){
            existingSeller.setMobile(seller.getMobile());
        }
        if(seller.getBankDetails() != null
                && seller.getBankDetails().getAccountHolderName() != null
                && seller.getBankDetails().getAccountNumber() != null
                && seller.getBankDetails().getIfscCode() != null){

            existingSeller.getBankDetails().setAccountHolderName(seller.getBankDetails().getAccountHolderName());
            existingSeller.getBankDetails().setAccountNumber(seller.getBankDetails().getAccountNumber());
            existingSeller.getBankDetails().setIfscCode(seller.getBankDetails().getIfscCode());
        }
        if(seller.getBuisnessDetails() != null && seller.getBankDetails().getBankName() != null){
            existingSeller.getBuisnessDetails().setBuisnessName(seller.getBuisnessDetails().getBuisnessName());
        }
        if(seller.getPickupaddress() != null && seller.getPickupaddress().getAddress()!=null
        && seller.getPickupaddress().getCity() != null && seller.getPickupaddress().getState() != null
        && seller.getPickupaddress().getMobile_number() != null){
            existingSeller.getPickupaddress().setAddress(seller.getPickupaddress().getAddress());
            existingSeller.getPickupaddress().setCity(seller.getPickupaddress().getCity());
            existingSeller.getPickupaddress().setState(seller.getPickupaddress().getState());
            existingSeller.getPickupaddress().setPinCode(seller.getPickupaddress().getPinCode());
            existingSeller.getPickupaddress().setMobile_number(seller.getPickupaddress().getMobile_number());
        }
            return sellerRepository.save(existingSeller);
    }

    @Override
    public void deleteSeller(Long id) throws Exception {
        Seller seller = getSellerById(id);
        sellerRepository.delete(seller);
    }

    @Override
    public Seller VerifySeller(String email, String otp) throws Exception {
        Seller seller = getSellerByEmail(email);
        seller.setEmailVerified(true);
        return sellerRepository.save(seller);

    }

    @Override
    public Seller updateSellerAccountStatus(Long sellerid, AccountStatus status) throws Exception {
        Seller seller = getSellerById(sellerid);
        seller.setAccountStatus(status);
        return sellerRepository.save(seller);
    }
}
