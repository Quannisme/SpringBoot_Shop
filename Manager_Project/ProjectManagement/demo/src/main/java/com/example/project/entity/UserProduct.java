package com.example.project.entity;

import com.example.project.entity.login_register.User;
import com.example.project.entity.product.Product;
import com.example.project.entity.product.Size;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class UserProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean status;
    private LocalDate time;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_User")
    User user;

    @ManyToOne
    @JoinColumn(name = "id_product")
    @JsonBackReference
    Product product;
    @ManyToOne
    @JoinColumn(name = "id_Size")
    @JsonBackReference
    Size size;

    public UserProduct() {
    }

    public UserProduct(int id, boolean status, LocalDate time, User user, Product product, Size size) {
        this.id = id;
        this.status = status;
        this.time = time;
        this.user = user;
        this.product = product;
        this.size = size;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public String getUserName(){
        return user.getName();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
