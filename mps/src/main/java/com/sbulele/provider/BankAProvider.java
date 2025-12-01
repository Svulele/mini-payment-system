package com.sbulele.provider;

import com.sbulele.model.PaymentRequest;
import com.sbulele.model.PaymentResponse;
import com.sbulele.model.PaymentStatus;

public class BankAProvider implements PaymentProvider {

    @Override
    public PaymentResponse process(PaymentRequest request) {
        if (request.getAmount() > 5000) {
            return new PaymentResponse(PaymentStatus.FAILED, "BankA limit exceeded");
        }
        return new PaymentResponse(PaymentStatus.SUCCESS, "BankA processed payment");
    }
}
