package com.soukaina.commande.order;

import com.soukaina.commande.kafka.OrderConfirmation;
import com.soukaina.commande.kafka.OrderProducer;
import com.soukaina.commande.orderline.OrderLineRequest;
import com.soukaina.commande.orderline.OrderLineService;
import com.soukaina.commande.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
//    private final ProductClient productClient;
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    public Integer createOrder(OrderRequest request) {
//        // check if the customer exists -> using OpenFeign
//        var customer = customerClient.findCustomerById(request.customerId())
//                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with the provided ID:: " + request.customerId()));

        // purchase the product -> product microservice -> using rest template
//        var purchasedProducts = productClient.purchaseProducts(request.products());

        // persist order
        var order = repository.save(mapper.toOrder(request));

        // persist order lines
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod()
                )
        );

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("No order found with ID:: %s", orderId)
                ));
    }
}
