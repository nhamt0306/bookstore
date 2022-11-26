package edu.hcmute.bookstore.service;

import edu.hcmute.bookstore.model.CommentEntity;

import java.util.List;

public interface CommentService {
    CommentEntity save(CommentEntity commentEntity);
    void deleteById(Long id);
    List<CommentEntity> getAllByProductId(Long productId);
    Integer getTotalCommentByUser(Long userId);
    List<CommentEntity> getAllCommentByRating(Long rating, Long productId);
}
