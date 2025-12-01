package com.sbulele.provider;

import com.sbulele.model.PaymentRequest;
import com.sbulele.model.PaymentResponse;

public interface PaymentProvider {
    PaymentResponse process(PaymentRequest request);
}
