package edu.hcmute.bookstore.controller;

import edu.hcmute.bookstore.service.UserService;
import edu.hcmute.bookstore.service.impl.ProductServiceImpl;
import edu.hcmute.bookstore.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadImageController {
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    UserServiceImpl userService;
    @PostMapping("/product/{id}/image")
    public ResponseEntity<?> uploadCommentImg(@PathVariable long id,
                                              @RequestParam(value = "image", required = false) MultipartFile image) {
        if(!image.getContentType().equals("image/png") && !image.getContentType().equals("image/jpeg")) {
            return new ResponseEntity<>("File khong hop le!", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(productService.uploadImage(id, image));
    }

    @PostMapping("/user/profile/avatar")
    public ResponseEntity<?> uploadAvatarImg(@RequestParam(value = "image", required = false) MultipartFile image) {
        if(!image.getContentType().equals("image/png") && !image.getContentType().equals("image/jpeg")) {
            return new ResponseEntity<>("File khong hop le!", HttpStatus.BAD_REQUEST);
        }

        userService.uploadAvatar(image);
        return ResponseEntity.ok("upload avatar success!");
    }
}
