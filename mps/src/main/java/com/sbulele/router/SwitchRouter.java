package com.sbulele.router;

import com.sbulele.provider.PaymentProvider;
import java.util.HashMap;
import java.util.Map;

public class SwitchRouter {

    private final Map<String, PaymentProvider> providers = new HashMap<>();

    // Register a provider with a code, e.g., "BANKA"
    public void registerProvider(String code, PaymentProvider provider) {
        providers.put(code, provider);
    }

    // Route a payment request to the correct provider
    public PaymentProvider route(String providerCode) {
        return providers.get(providerCode);
    }
}
