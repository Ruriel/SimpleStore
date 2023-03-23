package com.ruriel.simplestore.services.rabbitmq;

import com.ruriel.simplestore.services.rabbitmq.payload.PurchasePayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitMQSenderService {
    @Autowired
    private Queue queue;
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(PurchasePayload purchasePayload) {
        amqpTemplate.convertAndSend(queue.getName(), purchasePayload);
        log.info(String.format("Sending %s to the Queue %s.", purchasePayload.toString(), queue.getName()));
    }
}
