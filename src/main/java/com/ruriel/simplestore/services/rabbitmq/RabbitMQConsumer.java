package com.ruriel.simplestore.services.rabbitmq;

import com.ruriel.simplestore.entities.Purchase;
import com.ruriel.simplestore.services.PurchaseService;
import com.ruriel.simplestore.services.rabbitmq.payload.PurchasePayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMQConsumer {

    private final PurchaseService purchaseService;

    private final ModelMapper modelMapper;
    @RabbitListener(queues = {"${rabbitmq.queue}"})
    public void receive(@Payload PurchasePayload payload) {
        log.trace("Receiving " + payload);
        var purchase = modelMapper.map(payload, Purchase.class);
        purchaseService.patch(purchase.getId(), purchase);
    }
}
