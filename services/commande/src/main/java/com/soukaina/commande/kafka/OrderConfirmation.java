package com.soukaina.commande.kafka;

import com.soukaina.commande.order.PaymentMethod;

import java.math.BigDecimal;

public record OrderConfirmation(
    String orderReference,
    BigDecimal totalAmount,
    PaymentMethod paymentMethod
) {
}
