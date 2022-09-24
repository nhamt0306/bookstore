package edu.hcmute.bookstore.controller;

import edu.hcmute.bookstore.mapper.JwtResponse;
import edu.hcmute.bookstore.mapper.ResponseMessage;
import edu.hcmute.bookstore.mapper.SignInForm;
import edu.hcmute.bookstore.mapper.SignUpForm;
import edu.hcmute.bookstore.model.RoleEntity;
import edu.hcmute.bookstore.model.RoleName;
import edu.hcmute.bookstore.model.UserEntity;
import edu.hcmute.bookstore.security.jwt.JwtProvider;
import edu.hcmute.bookstore.security.principal.UserDetailService;
import edu.hcmute.bookstore.security.principal.UserPrinciple;
import edu.hcmute.bookstore.service.impl.RoleServiceImpl;
import edu.hcmute.bookstore.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*")   //Để ghép AuthController với các controller khác
@RequestMapping
@RestController
public class AuthenticationController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    RoleServiceImpl roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignUpForm signUpForm){
        if (userService.existsByUsername(signUpForm.getUsername())){
            return new ResponseEntity<>(new ResponseMessage("Tên tài khoản đã tồn tại! Vui lòng thử lại"), HttpStatus.OK);
        }
        if (userService.existsByEmail(signUpForm.getEmail())){
            return new ResponseEntity<>(new ResponseMessage("Email đã tồn tại! Vui lòng thử lại"), HttpStatus.OK);
        }


        UserEntity user = new UserEntity(signUpForm.getName(), signUpForm.getUsername(), signUpForm.getEmail(), signUpForm.getPhonenumber(), passwordEncoder.encode(signUpForm.getPassword()));
        Set<String> strRole =signUpForm.getRoles();
        Set<RoleEntity> roles = new HashSet<>();
        strRole.forEach(role -> {
            switch (role){
                case "ADMIN":
                    RoleEntity adminRole = roleService.findByName(RoleName.ADMIN).orElseThrow(
                            ()-> new RuntimeException("Role không hợp lệ!")
                    );
                    roles.add(adminRole);
                    break;
                case "SELLER":
                    RoleEntity sellerRole = roleService.findByName(RoleName.SELLER).orElseThrow(
                            ()-> new RuntimeException("Role không hợp lệ!")
                    );
                    roles.add(sellerRole);
                    break;
                default:
                    RoleEntity userRole = roleService.findByName(RoleName.USER).orElseThrow(
                            ()-> new RuntimeException("Role không hợp lệ!")
                    );
                    roles.add(userRole);
            }
        });
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(new ResponseMessage("Tạo user thành công!"), HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody SignInForm signInForm){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(token, userPrinciple.getName(), userPrinciple.getAuthorities()));
    }
}
