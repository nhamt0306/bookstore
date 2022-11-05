package edu.hcmute.bookstore.controller;

import edu.hcmute.bookstore.config.LocalVariable;
import edu.hcmute.bookstore.dto.ChangePasswordDTO;
import edu.hcmute.bookstore.dto.PubliserDTO;
import edu.hcmute.bookstore.mapper.JwtResponse;
import edu.hcmute.bookstore.mapper.SignInForm;
import edu.hcmute.bookstore.model.PublisherEntity;
import edu.hcmute.bookstore.model.UserEntity;
import edu.hcmute.bookstore.security.principal.UserDetailService;
import edu.hcmute.bookstore.security.principal.UserPrinciple;
import edu.hcmute.bookstore.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;

@CrossOrigin(origins = "*")   //Để ghép AuthController với các controller khác
@RequestMapping
@RestController
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserDetailService userDetailService;
    @Autowired
    PasswordEncoder passwordEncoder;

    //Get all user
    @GetMapping("admin/users/getAllUser")
    public ResponseEntity<?> getAllUser(){
        return ResponseEntity.ok(userService.findAll());
    }

    //Get current user
    @GetMapping("/user/profile")
    public ResponseEntity<?> getCurUser(){
        UserEntity userEntity = userDetailService.getCurrentUser();
        UserEntity respone = new UserEntity(userEntity.getId(), userEntity.getUsername(), userEntity.getFullName(), userEntity.getUserPhone(), userEntity.getUserEmail(), userEntity.getPassword(), userEntity.getUserAddress(), userEntity.getUserGender(), userEntity.getUserDob(),userEntity.getUserStatus());
        return ResponseEntity.ok(respone);
    }

    @GetMapping("admin/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id){
        try {
            return ResponseEntity.ok(userService.findById(id));
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(LocalVariable.messageCannotFindUser + id, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/user/profile/change")
    public Object changeProfile(@RequestBody UserEntity userEntity) throws ParseException {
        UserEntity user = userService.findByUsername(userEntity.getUsername()).get();
        user.setUpdate_at(new Timestamp(System.currentTimeMillis()));
        if (userEntity.getFullName() != null)
        {
            user.setFullName(userEntity.getFullName());
        }
        if (userEntity.getUserAddress() != null)
        {
            user.setUserAddress(userEntity.getUserAddress());
        }
        if (userEntity.getUserPhone() != null)
        {
            user.setUserPhone(userEntity.getUserPhone());
        }
        if (userEntity.getUserDob() != null)
        {
            user.setUserDob(userEntity.getUserDob());
        }
        if (userEntity.getUserGender() != null)
        {
            user.setUserGender(userEntity.getUserGender());
        }
        userService.save(user);
        return "Change user profile success!";
    }

    @PostMapping("/user/changepassword")
    public Object changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) throws ParseException {
        UserEntity user = userDetailService.getCurrentUser();
        if (changePasswordDTO.getNewPassword().equals(changePasswordDTO.getRePassword()))
        {
            if (passwordEncoder.encode(changePasswordDTO.getCurPassword()).equals(user.getPassword()))
            {
                user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
            }
            else
            {
                return "Password is incorrect!";
            }
        }
        else
        {
            return "Re-password is incorrect!";
        }

        return "Change password success!";
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
