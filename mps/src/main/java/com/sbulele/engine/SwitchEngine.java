package com.sbulele.engine;

import com.sbulele.model.PaymentRequest;
import com.sbulele.model.PaymentResponse;
import com.sbulele.model.PaymentStatus;
import com.sbulele.provider.PaymentProvider;
import com.sbulele.router.SwitchRouter;

public class SwitchEngine {

    private final SwitchRouter router;

    public SwitchEngine(SwitchRouter router) {
        this.router = router;
    }

    public PaymentResponse handlePayment(PaymentRequest request) {

        // Basic validation
        if (request.getAmount() <= 0) {
            return new PaymentResponse(PaymentStatus.FAILED, "Invalid amount");
        }

        // Get the correct provider
        PaymentProvider provider = router.route(request.getProviderCode());

        if (provider == null) {
            return new PaymentResponse(PaymentStatus.FAILED, "Unknown provider");
        }

        // Process payment via the provider
        return provider.process(request);
    }
}
