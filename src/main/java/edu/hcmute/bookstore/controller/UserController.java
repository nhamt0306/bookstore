package edu.hcmute.bookstore.controller;

import edu.hcmute.bookstore.mapper.JwtResponse;
import edu.hcmute.bookstore.mapper.SignInForm;
import edu.hcmute.bookstore.model.UserEntity;
import edu.hcmute.bookstore.security.principal.UserPrinciple;
import edu.hcmute.bookstore.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")   //Để ghép AuthController với các controller khác
@RequestMapping
@RestController
public class UserController {
    @Autowired
    UserServiceImpl userService;

    //Get all user
    @GetMapping("admin/users/getAllUser")
    public ResponseEntity<?> getAllUser(){
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("admin/users/uprole")
    public ResponseEntity<?> upRoleUser(@RequestParam(value = "email", required = false) String email){
        String username = userService.findByUsername(email).get().getUsername();
        if (userService.upRole(email)){
            return ResponseEntity.ok("Update Role User "+username+" Success");
        }
        return ResponseEntity.ok("User "+username+" already have this role!!!");
    }

    @PostMapping("admin/users/downrole")
    public ResponseEntity<?> downRoleUser(@RequestParam(value = "email", required = false) String email){
        String username = userService.findByUsername(email).get().getUsername();
        if (userService.downRole(email)){
            return ResponseEntity.ok("Update Role User "+username+" Success");
        }
        return ResponseEntity.ok("User "+username+" already have this role!!!");
    }

    @DeleteMapping("admin/users/delete")
    public ResponseEntity<?> deleteUser(@RequestParam(value = "email", required = false) String email)
    {
        if (userService.existsByEmail(email))
        {
            String username = userService.findByUsername(email).get().getUsername();
            Long userId = userService.findByUserEmail(email).get().getId();
            userService.deleteById(userId);
            return ResponseEntity.ok("Delete "+username+" success!");
        }
        return ResponseEntity.ok("Username unavailable!");
    }
}
