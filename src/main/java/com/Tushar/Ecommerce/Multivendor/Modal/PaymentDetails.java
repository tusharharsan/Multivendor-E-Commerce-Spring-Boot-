package com.Tushar.Ecommerce.Multivendor.Modal;

import com.Tushar.Ecommerce.Multivendor.domain.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetails {
    private String paymentId;

    private String RazorPaymentLinkId;

    private String razorPaymentLinkReferenceId;

    private String razorPaymentLinkStatus;

    private String razorpayPaymentId;

    private PaymentStatus status = PaymentStatus.PENDING;

}
