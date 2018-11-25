package de.stoxygen.services;

import de.stoxygen.model.*;
import de.stoxygen.repository.NotificationRepository;
import de.stoxygen.repository.TransmissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sun.net.smtp.SmtpProtocolException;

import java.util.Date;
import java.util.List;

@Service
public class MessageScheduler {
    private static final Logger logger = LoggerFactory.getLogger(MessageScheduler.class);

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private TransmissionRepository transmissionRepository;

    @Autowired
    private MailService mailService;

    @Scheduled(fixedRate = 600000)
    private void handleNormalNotifications() {
        logger.info("Start - handleNormalNotifications");
        List<Notification> notificationList = notificationRepository.findByNotificationSeverityAndNotificationStatus(
                NotificationSeverity.NORMAL, NotificationStatus.NEW);

        for(Notification notification : notificationList) {
            // Get transmission to notification
            Transmission transmission = transmissionRepository.findByNotification(notification);

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

        }
        logger.info("End - handleNormalNotification");
    }
}
