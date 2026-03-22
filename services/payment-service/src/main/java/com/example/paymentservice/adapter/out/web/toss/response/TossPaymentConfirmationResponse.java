package com.example.paymentservice.adapter.out.web.toss.response;

import java.util.List;

public record TossPaymentConfirmationResponse(
    String version,
    String paymentKey,
    String type,
    String orderId,
    String orderName,
    String mId,
    String currency,
    String method,
    int totalAmount,
    int balanceAmount,
    String status,
    String requestedAt,
    String approvedAt,
    boolean useEscrow,
    String lastTransactionKey,
    int suppliedAmount,
    int vat,
    boolean cultureExpense,
    int taxFreeAmount,
    int taxExemptionAmount,
    List<Cancel> cancels,
    Card card,
    VirtualAccount virtualAccount,
    MobilePhone mobilePhone,
    GiftCertificate giftCertificate,
    Transfer transfer,
    Receipt receipt,
    Checkout checkout,
    EasyPay easyPay,
    String country,
    TossFailureResponse failure,
    CashReceipt cashReceipt,
    List<CashReceipt> cashReceipts,
    Discount discount
) {
}
