package de.stoxygen.repository;

import de.stoxygen.model.Notification;
import de.stoxygen.model.Transmission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TransmissionRepository extends CrudRepository<Transmission, Long> {
    Transmission findByNotification(Notification notification);

}
