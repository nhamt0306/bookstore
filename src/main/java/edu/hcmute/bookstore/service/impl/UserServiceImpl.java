package edu.hcmute.bookstore.service.impl;

import edu.hcmute.bookstore.config.EmailTemplate;
import edu.hcmute.bookstore.config.LocalVariable;
import edu.hcmute.bookstore.exception.BadRequest;
import edu.hcmute.bookstore.exception.ResourceNotFoundException;
import edu.hcmute.bookstore.model.ProductEntity;
import edu.hcmute.bookstore.model.RoleEntity;
import edu.hcmute.bookstore.model.UserEntity;
import edu.hcmute.bookstore.repository.RoleRepository;
import edu.hcmute.bookstore.repository.UserRepository;
import edu.hcmute.bookstore.security.principal.UserDetailService;
import edu.hcmute.bookstore.service.CloudinaryService;
import edu.hcmute.bookstore.service.EmailSenderService;
import edu.hcmute.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    UserDetailService userDetailService;
    @Autowired
    CloudinaryService cloudinaryService;


    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByUserEmail(email);
    }


    @Override
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findByUserEmail(String email) {
        return userRepository.findByUserEmail(email);
    }

    // up role user to admin
    @Override
    public Boolean upRole(String email) {
        // Get user
        Optional<UserEntity> curUser = userRepository.findByUserEmail(email);
        // Get admin role
        Optional<RoleEntity> roleAdmin = roleRepository.findById(Long.parseLong("1"));
        // Get role cur user
        Set<RoleEntity> roleUser = curUser.get().getRoles();
        // Check if role's user not contain "admin" role --> user only have role "user"
        if (!roleUser.contains(roleAdmin.get()))
        {
            roleUser.add(roleAdmin.get());
            curUser.get().setRoles(roleUser);
            curUser.get().setUpdate_at(new Timestamp(System.currentTimeMillis()));
            userRepository.save(curUser.get());
            return true;
        }
        return false;
    }

    // down role user to admin
    @Override
    public Boolean downRole(String email) {
        // Get user
        Optional<UserEntity> curUser = userRepository.findByUserEmail(email);
        // Get admin role
        Optional<RoleEntity> roleAdmin = roleRepository.findById(Long.parseLong("1"));
        // Get role cur user
        Set<RoleEntity> roleUser = curUser.get().getRoles();
        // Check if role's user not contain "admin" role --> user only have role "user"
        if (roleUser.contains(roleAdmin.get()))
        {
            roleUser.remove(roleAdmin.get());
            curUser.get().setRoles(roleUser);
            curUser.get().setUpdate_at(new Timestamp(System.currentTimeMillis()));
            userRepository.save(curUser.get());
            return true;
        }
        return false;
    }

    @Override
    public UserEntity changeUserPasswordByEmail(String email, String newPassword) {
        UserEntity userChangePassword = userRepository.findByUserEmail(email)
                .map(user -> {
                    user.setPassword(passwordEncoder.encode(newPassword));
                    return userRepository.save(user);
                }).orElseThrow(() -> new ResourceNotFoundException("Cannot found user with email = " + email));
        return userChangePassword;
    }

    @Override
    public String getCheckValidEmailOTP(String username, String emailRegister) {
        UserEntity userByUsername = userRepository.findByUsername(username).get();
        System.out.println(userByUsername);
        if(userByUsername != null) {
            throw new BadRequest("T??n ng?????i d??ng b??? tr??ng, vui l??ng nh???p l???i!");
        }
        Optional<UserEntity> userByEmail = userRepository.findByUserEmail(emailRegister);
        if(userByEmail.isPresent()) {
            throw new BadRequest("Email b??? tr??ng, vui l??ng nh???p l???i!");
        }
        String otpCode = LocalVariable.GetOTP();
        try {
            sendCheckEmailByOTP(emailRegister, username, otpCode);
        } catch (MessagingException e) {
            throw new BadRequest("gmail send fail");
        }
        return otpCode;
    }

    @Override
    public void deleteByEmail(String email) {
        userRepository.deleteByUserEmail(email);
    }

    @Override
    public UserEntity uploadAvatar(MultipartFile image) {
        UserEntity user = userDetailService.getCurrentUser();
        String imageUrl = cloudinaryService.uploadFile(image,String.valueOf(user.getId()),
                "BookStore"+ "/" + "Avatar");
        if(!imageUrl.equals("-1")) {
            user.setAvatar(imageUrl);
        }
        else if(user.getAvatar().equals("") || user.getAvatar().equals("-1"))
            user.setAvatar("");

        return userRepository.save(user);
    }

    public void sendCheckEmailByOTP(String addressGmail, String username, String otpCode) throws MessagingException {
        emailSenderService.sendAsHTML(
                addressGmail,
                "X??c th???c Gmail cho t??i kho???n " + username,
                EmailTemplate.TemplateCheckValidEmail(username, otpCode)
        );
    }
}
