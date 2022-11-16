package edu.hcmute.bookstore.service;

import edu.hcmute.bookstore.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserEntity> findByUsername(String username);//Tìm User thông qua username
    Boolean existsByUsername(String username); //Kiểm tra username có trong db chưa?
    Boolean existsByEmail(String email);
    UserEntity save(UserEntity user);
    void deleteById(Long id);
    Optional<UserEntity> findById(Long id);
    List<UserEntity> findAll();
    Optional<UserEntity> findByUserEmail(String email);//Tìm User thông qua username
    Boolean upRole(String email);
    Boolean downRole(String email);
    UserEntity changeUserPasswordByEmail(String email, String newPassword);
    String getCheckValidEmailOTP(String username, String emailRegister);
    void deleteByEmail(String email);
}
