package edu.hcmute.bookstore.service.impl;

import edu.hcmute.bookstore.config.LocalVariable;
import edu.hcmute.bookstore.model.AuthorEntity;
import edu.hcmute.bookstore.model.CategoryEntity;
import edu.hcmute.bookstore.repository.AuthorRepository;
import edu.hcmute.bookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    @Override
    public List<AuthorEntity> getAllAuthor() {
        return authorRepository.findAll();
    }

    @Override
    public AuthorEntity save(AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);
    }

    @Override
    public AuthorEntity findAuthorById(Long id) {
        return authorRepository.findById(id).get();
    }

    @Override
    public List<AuthorEntity> findAllAuthorByName(String name) {
        return authorRepository.findByAutNameContaining(name);
    }

    @Override
    public void deleteAuthorById(Long id) {
        AuthorEntity authorEntity = authorRepository.findById(id).get();
        authorEntity.setAutStatus(LocalVariable.disableStatus);
        authorEntity.setUpdate_at(new Timestamp(System.currentTimeMillis()));
        authorRepository.save(authorEntity);
    }
}
