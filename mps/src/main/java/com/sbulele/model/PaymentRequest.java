package com.sbulele.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentRequest {

    private String customerId;
    private String providerCode;
    private double amount;

    // No-arg constructor required by Jackson
    public PaymentRequest() {
    }

    // All-args constructor (keeps your tests happy)
    public PaymentRequest(String customerId, String providerCode, double amount) {
        this.customerId = customerId;
        this.providerCode = providerCode;
        this.amount = amount;
    }

    // Standard getters
    public String getCustomerId() {
        return customerId;
    }

    // Keep the method your engine/tests expect
    public String getProviderCode() {
        return providerCode;
    }

    public double getAmount() {
        return amount;
    }

    // Setters needed by Jackson to populate fields from JSON.
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    // We provide two setters so both JSON shapes will work:
    //  - "providerCode": "BANKA"
    //  - "provider": "BANKA"
    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    // Map "provider" JSON key into providerCode
    @JsonProperty("provider")
    public void setProvider(String provider) {
        this.providerCode = provider;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
