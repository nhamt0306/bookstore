package edu.hcmute.bookstore.mapper;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.hcmute.bookstore.model.RoleEntity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class UserMapper {
    private Long id;
    private String username;
    private String fullName;
    private String userPhone;
    private String userEmail;
    private String password;
    private String userAddress;
    private String userGender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp userDob;
    Set<RoleEntity> roles = new HashSet<>();

    public UserMapper(Long id, String username, String fullName, String userPhone, String userEmail, String password, String userAddress, String userGender, Timestamp userDob) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.password = password;
        this.userAddress = userAddress;
        this.userGender = userGender;
        this.userDob = userDob;
    }

    public UserMapper(Long id, String username, String fullName, String userPhone, String userEmail, String password, String userAddress, String userGender, Timestamp userDob, Set<RoleEntity> roles) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.password = password;
        this.userAddress = userAddress;
        this.userGender = userGender;
        this.userDob = userDob;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public Timestamp getUserDob() {
        return userDob;
    }

    public void setUserDob(Timestamp userDob) {
        this.userDob = userDob;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
}
