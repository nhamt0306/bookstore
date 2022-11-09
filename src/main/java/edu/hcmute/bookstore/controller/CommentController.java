package edu.hcmute.bookstore.controller;

import edu.hcmute.bookstore.config.LocalVariable;
import edu.hcmute.bookstore.dto.CategoryDTO;
import edu.hcmute.bookstore.model.CategoryEntity;
import edu.hcmute.bookstore.model.CommentEntity;
import edu.hcmute.bookstore.security.principal.UserDetailService;
import edu.hcmute.bookstore.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;

@RestController
public class CommentController {
    @Autowired
    CommentServiceImpl commentService;
    @Autowired
    UserDetailService userDetailService;

    @PostMapping("/user/comment/create")
    public Object createComment(@RequestBody CommentEntity commentEntity) throws ParseException {
        CommentEntity commentEntity1 = new CommentEntity();
        commentEntity1.setComContent(commentEntity.getComContent());
        commentEntity1.setComRating(commentEntity.getComRating());
        commentEntity1.setUserId(userDetailService.getCurrentUser().getId());
        commentEntity1.setUpdate_at(new Timestamp(System.currentTimeMillis()));
        commentEntity1.setCreate_at(new Timestamp(System.currentTimeMillis()));
        return commentService.save(commentEntity1);
    }

    @DeleteMapping("/user/comment/{id}")
    public ResponseEntity<?> deleteCommentById(@PathVariable long id)
    {
        commentService.deleteById(id);
        return ResponseEntity.ok(LocalVariable.messageDeleteCatSuccess);
    }
}
