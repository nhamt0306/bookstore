package edu.hcmute.bookstore.service.impl;

import edu.hcmute.bookstore.model.CommentEntity;
import edu.hcmute.bookstore.repository.CommentRepository;
import edu.hcmute.bookstore.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Override
    public CommentEntity save(CommentEntity commentEntity) {
        return commentRepository.save(commentEntity);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
