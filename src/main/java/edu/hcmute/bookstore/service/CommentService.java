package edu.hcmute.bookstore.service;

import edu.hcmute.bookstore.model.CommentEntity;

public interface CommentService {
    CommentEntity save(CommentEntity commentEntity);
    void deleteById(Long id);
}
