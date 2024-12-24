package com.soukaina.commande.order;

import com.soukaina.commande.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id, // In case we want to update our order
        String reference,
        @Positive(message = "Order amount should be positive")
        BigDecimal amount,
        @NotNull(message = "Payment method should be specified")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer amount should be specified")
        @NotEmpty(message = "Customer amount should be specified")
        @NotBlank(message = "Customer amount should be specified")
        String customerId,
        @NotEmpty(message = "You should purchase at least one product")
        List<PurchaseRequest> products
) {
}