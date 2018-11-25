package de.stoxygen.repository;

import de.stoxygen.model.ReceiverGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ReceiverGroupRepository extends CrudRepository<ReceiverGroup, Long> {

}
