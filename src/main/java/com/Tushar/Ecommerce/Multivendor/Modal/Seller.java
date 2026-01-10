package com.Tushar.Ecommerce.Multivendor.Modal;

import com.Tushar.Ecommerce.Multivendor.domain.AccountStatus;
import com.Tushar.Ecommerce.Multivendor.domain.USER_ROLE;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String mobile;

    @Column(unique = true , nullable = false)
    private String email;
    private String password;

    @Embedded
    private BuisnessDetails buisnessDetails = new BuisnessDetails();

    @Embedded
    private BankDetails bankDetails = new BankDetails();

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    private String GSTIN;

    private USER_ROLE role = USER_ROLE.ROLE_SELLER;

    private boolean isEmailVerified = false;

    private AccountStatus accountStatus = AccountStatus.PENDING_VERIFICATION;



}
