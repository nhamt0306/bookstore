package edu.hcmute.bookstore.controller;

import edu.hcmute.bookstore.config.EmailTemplate;
import edu.hcmute.bookstore.config.LocalVariable;
import edu.hcmute.bookstore.dto.EmailRecoveryDTO;
import edu.hcmute.bookstore.dto.OtpSendMailResponseDTO;
import edu.hcmute.bookstore.exception.BadRequest;
import edu.hcmute.bookstore.mapper.JwtResponse;
import edu.hcmute.bookstore.mapper.ResponseMessage;
import edu.hcmute.bookstore.mapper.SignInForm;
import edu.hcmute.bookstore.mapper.SignUpForm;
import edu.hcmute.bookstore.model.CartEntity;
import edu.hcmute.bookstore.model.RoleEntity;
import edu.hcmute.bookstore.model.RoleName;
import edu.hcmute.bookstore.model.UserEntity;
import edu.hcmute.bookstore.security.jwt.JwtProvider;
import edu.hcmute.bookstore.security.principal.UserDetailService;
import edu.hcmute.bookstore.security.principal.UserPrinciple;
import edu.hcmute.bookstore.service.EmailSenderService;
import edu.hcmute.bookstore.service.impl.CartServiceImpl;
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

import javax.mail.MessagingException;
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
    CartServiceImpl cartService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    private EmailSenderService emailSenderService;


    @PostMapping("/register")
    public Object register(@RequestBody SignUpForm signUpForm){
        if (userService.existsByUsername(signUpForm.getUsername())){
            return new ResponseEntity<>(new ResponseMessage("Tên tài khoản đã tồn tại! Vui lòng thử lại", "false"), HttpStatus.NOT_FOUND);
        }
        if (userService.existsByEmail(signUpForm.getEmail())){
            return new ResponseEntity<>(new ResponseMessage("Email đã tồn tại! Vui lòng thử lại", "false"), HttpStatus.NOT_FOUND);
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
                default:
                    RoleEntity userRole = roleService.findByName(RoleName.USER).orElseThrow(
                            ()-> new RuntimeException("Role không hợp lệ!")
                    );
                    roles.add(userRole);
            }
        });
        user.setRoles(roles);
        user.setCartEntity(new CartEntity(user.getId()));
        userService.save(user);
        cartService.save(user.getId());
        return new ResponseEntity<>(new ResponseMessage("Tạo user thành công!", "true"), HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SignInForm signInForm){
        // UsernamePasswordAuthenticationToken sẽ kiểm tra thông tin người dùng
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword())
        );
        // Set token lên hệ thống
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Tạo jwt token
        String token = jwtProvider.createToken(authentication);
        // Lấy ra thông tin người dùng hiện tại trên hệ thống
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        // Trả về kết quả.
        return ResponseEntity.ok(new JwtResponse(token, userPrinciple.getName(), userPrinciple.getAuthorities()));
    }

    @GetMapping(path = "/verifyEmail/getOtp")
    public OtpSendMailResponseDTO verifyEmail(@RequestBody EmailRecoveryDTO email)
    {
        if(email.getEmail() == null) {
            throw new BadRequest("need ?email= param");
        }
        UserEntity user = userService.findByUserEmail(email.getEmail()).get(); //Kiem tra user voi email khoi phuc
        String otpCode = LocalVariable.GetOTP();
        try {
            sendVerifyEmail(email.getEmail(), user.getUsername(), otpCode);
        } catch (MessagingException e) {
            throw new BadRequest("gmail send fail");
        }

        return new OtpSendMailResponseDTO("Valid", otpCode);
    }

    public void sendVerifyEmail(String addressGmail, String username, String otpCode) throws MessagingException {
        emailSenderService.sendAsHTML(
                addressGmail,
                "Bạn đã Yêu cầu xác thực email cho tài khoản " + username,
                EmailTemplate.TemplateCheckValidEmail(username, otpCode)
        );
    }


}
