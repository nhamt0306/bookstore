package edu.hcmute.bookstore.repository;

import edu.hcmute.bookstore.model.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    List<AddressEntity> getAllByUserEntityId(Long userId);
}
