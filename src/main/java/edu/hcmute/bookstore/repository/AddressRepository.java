package edu.hcmute.bookstore.repository;

import edu.hcmute.bookstore.model.AddressEntity;
import edu.hcmute.bookstore.model.CartProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    List<AddressEntity> getAllByUserEntityId(Long userId);
    @Query(value = "SELECT * FROM bookstore.addresses where user_id = :UserID and add_default =true;",nativeQuery = true)
    AddressEntity getDefaultAddressForUser(@Param("UserID") Long UserID);
}
