package edu.hcmute.bookstore.controller;

import edu.hcmute.bookstore.config.EmailTemplate;
import edu.hcmute.bookstore.config.LocalVariable;
import edu.hcmute.bookstore.dto.EmailRecoveryDTO;
import edu.hcmute.bookstore.dto.OtpSendMailResponseDTO;
import edu.hcmute.bookstore.dto.RecoveryPasswordDTO;
import edu.hcmute.bookstore.dto.SuccessResponseDTO;
import edu.hcmute.bookstore.exception.BadRequest;
import edu.hcmute.bookstore.model.UserEntity;
import edu.hcmute.bookstore.service.EmailSenderService;
import edu.hcmute.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Map;

@RestController
@RequestMapping("/recoveryPassword")
public class PasswordRecoveryController {

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    UserService userService;

    @GetMapping(path = "/getOtp")
    public OtpSendMailResponseDTO sendOtpRecoveryCodeToUserEmail(@RequestBody EmailRecoveryDTO email) {
        if(email.getEmail() == null) {
            throw new BadRequest("change password by address for user fail! need ?email= param");
        }
        UserEntity user = userService.findByUserEmail(email.getEmail()).get(); //Kiem tra user voi email khoi phuc
        String otpCode = LocalVariable.GetOTP();
        try {
            sendRecoveryEmail(email.getEmail(), user.getUsername(), otpCode);
        } catch (MessagingException e) {
            throw new BadRequest("gmail send fail");
        }

        return new OtpSendMailResponseDTO("Valid", otpCode);
    }

    public void sendRecoveryEmail(String addressGmail, String username, String otpCode) throws MessagingException {
        emailSenderService.sendAsHTML(
                addressGmail,
                "Bạn đã Yêu cầu khôi phục mật khẩu cho " + username,
                EmailTemplate.TemplateRecoveryPassword(username, otpCode)
        );
    }
// Tạo table, username, email và otp --> Lưu otp và email,
// sau khi người dùng xác thực otp thì check dưới db
// Nếu otp đúng thì cho người dùng nhập password --> Call API recovery/{email} để đổi password;


    @PutMapping(path = "/recovery/{email}")
    public SuccessResponseDTO recoveryPassword(@PathVariable String email, @RequestBody RecoveryPasswordDTO userChangePassword) {
        //Check otp

        //Change password
        userService.changeUserPasswordByEmail(userChangePassword.getPassword(), email);
        return new SuccessResponseDTO(HttpStatus.OK, "Change password success");
    }
}
