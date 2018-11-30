package de.stoxygen.services;

import com.rabbitmq.client.Channel;
import de.stoxygen.RestfulClient;
import de.stoxygen.model.*;
import de.stoxygen.repository.NotificationRepository;
import de.stoxygen.repository.TransmissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class EventReceiver {
    private static final Logger logger = LoggerFactory.getLogger(EventReceiver.class);

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private TransmissionRepository transmissionRepository;

    @Autowired
    private RestfulClient restfulClient;

    @Autowired
    private TransmissionService transmissionService;

    @Autowired
    private MailService mailService;


    @RabbitListener(queues = "notification")
    public void receiveNotifications(final NotificationMessage message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        logger.info("Received <{}>", message.toString());

        try {
            // Save notification at first
            Notification notification = new Notification(message.getSender(),
                    NotificationCategory.valueOf(message.getCategory()), NotificationSeverity.valueOf(message.getSeverity()),
                    message.getContent(), "", NotificationStatus.NEW);
            notificationRepository.save(notification);
            logger.debug("Saved notification. NotificationId: {}", notification.getNotificationsId());

            // Get receiver information by username
            List<UserNotificationData> userNotificationDataList = restfulClient.getUserNotificationData(message.getUsername(),
                    NotificationSeverity.valueOf(message.getSeverity()));


            // Add notification to transmission table
            for (UserNotificationData userNotificationData : userNotificationDataList) {
                logger.debug("{}", userNotificationData.toString());
                Transmission transmission = new Transmission(userNotificationData.getReceiver_address(),
                        userNotificationData.getChannel(), TransmissionStatus.ACKNOWLEDGED, notification);
                logger.debug("{}", transmission.toString());
                transmissionRepository.save(transmission);
                transmissionService.createTransmission(transmission.getTransmissionsId(), transmission.getReceiver(),
                        transmission.getReceiver(), transmission.getNotification().getNotificationsId());
            }

            logger.info("Acknowledge message on queue notification.");
            channel.basicAck(tag, true);
        } catch (IOException e) {
            logger.error("Something bad happend during acknowledge of a message from the queue. Message: {}", e.getMessage());
            e.printStackTrace();
        } catch (HttpClientErrorException e) {
            logger.error("Something bad happend due to handle message. We will reject it without requeue. Message: {}", e.getMessage());
            channel.basicReject(tag, false);
        }
    }

    @RabbitListener(queues = "transmission")
    public void receiveTransmissions(final TransmissionMessage message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        logger.info("Received Transmission <{}>", message.toString());

        try {
            Transmission transmission = transmissionRepository.findOne(message.getTransmissionsId());
            Notification notification = notificationRepository.findOne(message.getNotificationsId());
            if (transmission.getChannel().equals("email")) {
                try {
                    logger.debug("Prepare to send mail notification to {}", transmission.getReceiver());

                    mailService.sendMail("Test Notification", notification.getContent(), transmission.getReceiver());

                    notification.setNotificationStatus(NotificationStatus.PROCESSED);
                    transmission.setSent(new Date());
                    transmission.setTransmissionStatus(TransmissionStatus.SENT);

                    notificationRepository.save(notification);
                    transmissionRepository.save(transmission);
                    logger.debug("Save new status of notification and transmission.");

                } catch (MailException e) {
                    logger.warn("Something went wrong to sent a mail to {}. ErrorMessage: {}", transmission.getReceiver(), e.getMessage());
                    notification.setNotificationStatus(NotificationStatus.PROCESSED);
                    transmission.setTransmissionStatus(TransmissionStatus.FAILED);

                    notificationRepository.save(notification);
                    transmissionRepository.save(transmission);
                }
            }

            logger.info("Acknowledge message on queue transmission");
            channel.basicAck(tag, true);

        } catch (IOException e) {
            logger.error("Something bad happend during acknowledge of a message from the queue. Message: {}", e.getMessage());
            e.printStackTrace();
        }
    }
}
