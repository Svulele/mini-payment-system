package com.sbulele.provider;

import com.sbulele.model.PaymentRequest;
import com.sbulele.model.PaymentResponse;
import com.sbulele.model.PaymentStatus;

public class BankBProvider implements PaymentProvider {

    @Override
    public PaymentResponse process(PaymentRequest request) {
        if (request.getAmount() > 10000) {
            return new PaymentResponse(PaymentStatus.FAILED, "BankB limit exceeded");
        }
        return new PaymentResponse(PaymentStatus.SUCCESS, "BankB processed payment");
    }
}
