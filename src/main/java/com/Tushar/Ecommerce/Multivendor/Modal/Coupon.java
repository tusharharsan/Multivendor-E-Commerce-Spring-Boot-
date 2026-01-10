package com.Tushar.Ecommerce.Multivendor.Modal;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String code;

    private double discountPercentage;

    private LocalDate validityStartDate;

    private LocalDate validityEndDate;

    private double minOrderValue;

    private boolean isActive =true;

    @ManyToMany(mappedBy = "usedCoupon")
    private Set<User> usedByUser = new HashSet<>();

}
