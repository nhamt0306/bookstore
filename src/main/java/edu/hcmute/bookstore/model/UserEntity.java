package edu.hcmute.bookstore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String userUsername;
    private String userPhone;
    private String userEmail;
    private String userPassword;
    private String userAddress;
    private String userGender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp userDob;
    private String userStatus = "Active";

    // Relationship with table OrderEntity (1:n)
    @OneToMany(mappedBy = "userEntities", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderEntity> orderEntities = new ArrayList<>();

    // Relationship with table CartEntity
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartId", referencedColumnName = "id")
    private CartEntity cartEntity; // mappedBy in table CartEntity

    // Relationship with table WishList
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<WishListEntity> wishListEntities = new ArrayList<>();

    //Ràng buộc quen hệ JPA data spring
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name= "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<RoleEntity> roles = new HashSet<>();


    // Constructor, Getter and Setter
    public UserEntity() {
    }

    public UserEntity(Long id, String userName, String userUsername, String userPhone, String userEmail, String userPassword, String userAddress, String userGender, Timestamp userDob, String userStatus) {
        this.id = id;
        this.userName = userName;
        this.userUsername = userUsername;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userAddress = userAddress;
        this.userGender = userGender;
        this.userDob = userDob;
        this.userStatus = userStatus;
    }

    public UserEntity(String name, String username, String email, String phonenumber, String encode) {
        this.userName = name;
        this.userUsername = userName;
        this.userEmail = email;
        this.userPhone = phonenumber;
        this.userPassword = encode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }


    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
}
