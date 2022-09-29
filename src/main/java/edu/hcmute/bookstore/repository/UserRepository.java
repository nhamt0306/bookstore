package edu.hcmute.bookstore.repository;

import edu.hcmute.bookstore.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);//Tìm User thông qua username
    Boolean existsByUsername(String username); //Kiểm tra username có trong db chưa?
    Boolean existsByUserEmail(String email);
    Optional<UserEntity> findByUserEmail(String email);//Tìm User thông qua email
    UserEntity save(UserEntity user);
}
