package com.sbulele.model;
public class PaymentRequest {
    private String customerId;
    private String providerCode;
    private double amount;

    public PaymentRequest(String customerId, String providerCode, double amount) {
        this.customerId = customerId;
        this.providerCode = providerCode;
        this.amount = amount;
    }

    public String getCustomerId() { return customerId; }
    public String getProviderCode() { return providerCode; }
    public double getAmount() { return amount; }
}

