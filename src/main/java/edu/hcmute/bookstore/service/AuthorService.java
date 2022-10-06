package edu.hcmute.bookstore.service;

import edu.hcmute.bookstore.model.AuthorEntity;

import java.util.List;

public interface AuthorService {
    List<AuthorEntity> getAllAuthor();
    AuthorEntity save(AuthorEntity authorEntity);
    AuthorEntity findAuthorById(Long id);
    List<AuthorEntity> findAllAuthorByName(String name);
    void deleteAuthorById(Long id);
}
