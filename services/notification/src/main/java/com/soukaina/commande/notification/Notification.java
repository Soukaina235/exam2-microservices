package com.soukaina.commande.notification;

import com.soukaina.commande.kafka.order.OrderConfirmation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {
    @Id
    private String id;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
}
