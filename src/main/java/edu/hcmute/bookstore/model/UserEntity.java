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
    private String username;
    private String fullName;
    private String userPhone;
    private String userEmail;
    private String password;
    private String userAddress;
    private String userGender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp userDob;
    private String userStatus = "Active";

    // Relationship with table AddressEntity
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AddressEntity> addressEntityList = new ArrayList<>();

    // Relationship with table OrderEntity (1:n)
    @OneToMany(mappedBy = "userEntities", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderEntity> orderEntities = new ArrayList<>();

    // Relationship with table CartEntity
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartId", referencedColumnName = "id")
    private CartEntity cartEntity; // mappedBy in table CartEntity


    //Ràng buộc quan hệ JPA data spring
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name= "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<RoleEntity> roles = new HashSet<>();




    // Constructor, Getter and Setter
    public UserEntity() {
    }

    public UserEntity(Long id, String userName, String fullName, String userPhone, String userEmail, String password, String userAddress, String userGender, Timestamp userDob, String userStatus) {
        this.id = id;
        this.username = userName;
        this.fullName = fullName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.password = password;
        this.userAddress = userAddress;
        this.userGender = userGender;
        this.userDob = userDob;
        this.userStatus = userStatus;
    }

    public UserEntity(String name, String username, String email, String phonenumber, String encode) {
        this.username = username;
        this.fullName = name;
        this.userEmail = email;
        this.userPhone = phonenumber;
        this.password = encode;
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

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public CartEntity getCartEntity() {
        return cartEntity;
    }

    public void setCartEntity(CartEntity cartEntity) {
        this.cartEntity = cartEntity;
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
