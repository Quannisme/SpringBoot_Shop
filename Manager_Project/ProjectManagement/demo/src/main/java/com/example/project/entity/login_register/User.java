package com.example.project.entity.login_register;

import com.example.project.entity.UserProduct;
import com.example.project.entity.product.Product;
import com.example.project.util.Gender;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private int id;
    @Column(name = "user_name" , columnDefinition = "varchar(45)")
    private String name;
    @Column(name = "user_userName")
    private String userName;
    @Column(name = "customer_gender")
    private Boolean gender;
    @Column(name = "user_email")
    private String email;
    @Column(name = "user_password" , columnDefinition = "varchar(45)")
    private String password;
    @Column(name = "user_birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDay;
    @Column(name = "user_idCard" , columnDefinition = "varchar(45)")
    private String idCard;
    @Column(name = "user_phone" , columnDefinition = "varchar(45)")
    private String phone;
    @Column(name = "user_address" , columnDefinition = "varchar(45)")
    private String address;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    Set<UserProduct> userProduct;

    public User() {
    }

    public User(int id, String name, String userName, Boolean gender, String email, String password, Date birthDay, String idCard, String phone, String address, Set<UserProduct> userProduct) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.birthDay = birthDay;
        this.idCard = idCard;
        this.phone = phone;
        this.address = address;
        this.userProduct = userProduct;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }
    @JsonManagedReference

    public Set<UserProduct> getUserProduct() {
        return userProduct;
    }

    public void setUserProduct(Set<UserProduct> userProduct) {
        this.userProduct = userProduct;
    }
}
