package com.soukaina.commande.orderline;

public record OrderLineRequest (
        Integer id,
        Integer orderId,
        Integer productId,
        double quantity
) {
}
