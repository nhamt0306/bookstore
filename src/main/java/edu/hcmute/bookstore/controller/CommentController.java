package edu.hcmute.bookstore.controller;

import edu.hcmute.bookstore.config.LocalVariable;
import edu.hcmute.bookstore.dto.CategoryDTO;
import edu.hcmute.bookstore.dto.CommentDTO;
import edu.hcmute.bookstore.mapper.CommentMapper;
import edu.hcmute.bookstore.mapper.ProductMapper;
import edu.hcmute.bookstore.model.CategoryEntity;
import edu.hcmute.bookstore.model.CommentEntity;
import edu.hcmute.bookstore.model.ProductEntity;
import edu.hcmute.bookstore.model.UserEntity;
import edu.hcmute.bookstore.security.principal.UserDetailService;
import edu.hcmute.bookstore.service.impl.CommentServiceImpl;
import edu.hcmute.bookstore.service.impl.ProductServiceImpl;
import edu.hcmute.bookstore.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    CommentServiceImpl commentService;
    @Autowired
    UserDetailService userDetailService;
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/comment/product/{id}")
    public ResponseEntity<?> getAllCommentByProduct(@PathVariable long id){
        List<CommentMapper> commentMappers = new ArrayList<>();
        for(CommentEntity commentEntity : commentService.getAllByProductId(id))
        {
            UserEntity user= userService.findById(commentEntity.getUserId()).get();
            CommentMapper commentMapper = new CommentMapper(commentEntity.getId(), commentEntity.getComContent(), commentEntity.getComRating(), user.getFullName());
            commentMappers.add(commentMapper);
        }
        return ResponseEntity.ok(commentMappers);
    }

    @PostMapping("/user/comment/create")
    public Object createComment(@RequestBody CommentDTO commentDTO) throws ParseException {
        CommentEntity commentEntity1 = new CommentEntity();
        commentEntity1.setComContent(commentDTO.getComContent());
        commentEntity1.setComRating(commentDTO.getComRating());
        commentEntity1.setUserId(userDetailService.getCurrentUser().getId());
        commentEntity1.setProductEntity(productService.findProductById(commentDTO.getProductId()));
        commentEntity1.setUpdate_at(new Timestamp(System.currentTimeMillis()));
        commentEntity1.setCreate_at(new Timestamp(System.currentTimeMillis()));
        commentService.save(commentEntity1);
        return "Create comment success!";
    }

    @DeleteMapping("/user/comment/{id}")
    public ResponseEntity<?> deleteCommentById(@PathVariable long id)
    {
        try {
            commentService.deleteById(id);
            return ResponseEntity.ok("Delete comment success!");
        }
        catch (Exception e)
        {
            return ResponseEntity.ok("Comment is invalid!");
        }
    }
}