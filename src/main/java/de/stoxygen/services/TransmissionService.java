package de.stoxygen.services;

import de.stoxygen.model.*;
import de.stoxygen.repository.NotificationRepository;
import de.stoxygen.repository.TransmissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Handle transmission of notification and alerts on rabbitmq.
 */
@Service
public class TransmissionService {
    private static final Logger logger = LoggerFactory.getLogger(TransmissionService.class);

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private TransmissionRepository transmissionRepository;



    private final AmqpTemplate rabbitTemplate;
    private final Exchange exchange;

    public TransmissionService(AmqpTemplate rabbitTemplate, Exchange exchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }


    public void createTransmission(Long transmissionsId, String receiver, String channel, Long notificationsId) {
        String routingKey = "notification.transmit";
        final TransmissionMessage transmissionMessage = new TransmissionMessage(transmissionsId, receiver, channel, notificationsId);
        rabbitTemplate.convertAndSend(exchange.getName(), routingKey, transmissionMessage);

    }
}
