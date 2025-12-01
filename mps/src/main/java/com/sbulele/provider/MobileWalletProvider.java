package com.sbulele.provider;

import com.sbulele.model.PaymentRequest;
import com.sbulele.model.PaymentResponse;
import com.sbulele.model.PaymentStatus;

public class MobileWalletProvider implements PaymentProvider {

    @Override
    public PaymentResponse process(PaymentRequest request) {
        return new PaymentResponse(PaymentStatus.SUCCESS, "Mobile Wallet processed payment");
    }
}
