package de.stoxygen.repository;

import de.stoxygen.model.Notification;
import de.stoxygen.model.NotificationSeverity;
import de.stoxygen.model.NotificationStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface NotificationRepository extends CrudRepository<Notification, Long> {
    List<Notification> findByNotificationSeverityAndNotificationStatus(NotificationSeverity notificationSeverity, NotificationStatus notificationStatus);

}
