package com.sbulele.model;

public class PaymentResponse {
    private PaymentStatus status;
    private String message;

    public PaymentResponse(PaymentStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public PaymentStatus getStatus() { return status; }
    public String getMessage() { return message; }
}
