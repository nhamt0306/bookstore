package edu.hcmute.bookstore.repository;

import edu.hcmute.bookstore.model.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    Page<OrderEntity> findAllByUserEntitiesId(Long UserId, Pageable pageable);

    List<OrderEntity> getAllByUserEntitiesIdAndOrdStatus(Long userEntities_id, String status);

    List<OrderEntity> getAllByUserEntitiesIdAndOrdStatusNot(Long userEntities_id, String status);

}
