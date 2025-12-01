package com.sbulele;

import com.sbulele.model.PaymentRequest;
import com.sbulele.model.PaymentResponse;
import com.sbulele.engine.SwitchEngine;
import com.sbulele.router.SwitchRouter;
import com.sbulele.provider.BankAProvider;
import com.sbulele.provider.BankBProvider;
import com.sbulele.provider.MobileWalletProvider;


import java.util.List;

public class DemoApplication {

    public static void main(String[] args) {

        // Setup router and engine
        SwitchRouter router = new SwitchRouter();
        router.registerProvider("BANKA", new BankAProvider());
        router.registerProvider("BANKB", new BankBProvider());
        router.registerProvider("WALLET", new MobileWalletProvider());

        SwitchEngine engine = new SwitchEngine(router);

        // List of payment requests
        List<PaymentRequest> requests = List.of(
            new PaymentRequest("CUST001", "BANKA", 3000),
            new PaymentRequest("CUST002", "BANKB", 15000),
            new PaymentRequest("CUST003", "WALLET", 500),
            new PaymentRequest("CUST004", "BANKA", -10),
            new PaymentRequest("CUST005", "UNKNOWN", 100)
        );

        // Process each payment
        for (PaymentRequest request : requests) {
            PaymentResponse response = engine.handlePayment(request);
            System.out.println(request.getCustomerId() + ": " 
                + response.getStatus() + " - " + response.getMessage());
        }
    }
}
