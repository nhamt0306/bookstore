package edu.hcmute.bookstore.service.impl;

import edu.hcmute.bookstore.model.UserEntity;
import edu.hcmute.bookstore.repository.UserRepository;
import edu.hcmute.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUserUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUserUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByUserEmail(email);
    }


    @Override
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }
}
