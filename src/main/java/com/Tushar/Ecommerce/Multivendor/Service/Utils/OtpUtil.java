package com.Tushar.Ecommerce.Multivendor.Service.Utils;

public class OtpUtil {
    public static String generateOtp() {
        int otpLength = 6;
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < otpLength; i++) {
            int digit = (int) (Math.random() * 10);
            otp.append(digit);
        }
        return otp.toString();
    }
}
