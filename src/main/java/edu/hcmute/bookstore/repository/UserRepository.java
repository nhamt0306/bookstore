package edu.hcmute.bookstore.repository;

import edu.hcmute.bookstore.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserUsername(String username);//Tìm User thông qua username
    Boolean existsByUserUsername(String username); //Kiểm tra username có trong db chưa?
    Boolean existsByUserEmail(String email);
    UserEntity save(UserEntity user);
}
