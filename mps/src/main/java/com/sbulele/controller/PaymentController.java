package com.sbulele.controller;

import com.sbulele.model.PaymentRequest;
import com.sbulele.model.PaymentResponse;
import com.sbulele.engine.SwitchEngine;
import com.sbulele.router.SwitchRouter;
import com.sbulele.provider.BankAProvider;
import com.sbulele.provider.BankBProvider;
import com.sbulele.provider.MobileWalletProvider;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*") // allow React to call API
public class PaymentController {

    private final SwitchEngine engine;

    public PaymentController() {
        SwitchRouter router = new SwitchRouter();
        router.registerProvider("BANKA", new BankAProvider());
        router.registerProvider("BANKB", new BankBProvider());
        router.registerProvider("WALLET", new MobileWalletProvider());
        this.engine = new SwitchEngine(router);
    }

    @PostMapping
    public PaymentResponse handlePayment(@RequestBody PaymentRequest request) {
        System.out.println("Incoming payment: " + request.getCustomerId() + " provider=" + request.getProviderCode() + " amount=" + request.getAmount());
        return engine.handlePayment(request);
    }
    
}
