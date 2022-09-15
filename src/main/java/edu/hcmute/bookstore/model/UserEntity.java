package edu.hcmute.bookstore.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String user_name;
    private String user_phone;
    private String user_email;
    private String user_address;
    private String user_gender;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Timestamp user_dob;
    private String user_status = "Active";
    private String user_role = "ROLE_USER";


    // Constructor, Getter and Setter

    public UserEntity() {
    }

    public UserEntity(Long id, String user_name, String user_phone, String user_email, String user_address, String user_gender, Timestamp user_dob, String user_status, String user_role) {
        this.id = id;
        this.user_name = user_name;
        this.user_phone = user_phone;
        this.user_email = user_email;
        this.user_address = user_address;
        this.user_gender = user_gender;
        this.user_dob = user_dob;
        this.user_status = user_status;
        this.user_role = user_role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public Timestamp getUser_dob() {
        return user_dob;
    }

    public void setUser_dob(Timestamp user_dob) {
        this.user_dob = user_dob;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }
}
