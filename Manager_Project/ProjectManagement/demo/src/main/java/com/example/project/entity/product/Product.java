package com.example.project.entity.product;

import com.example.project.entity.UserProduct;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    private String title;
    @NotNull
    private String img;
    @NotNull
    private String description;
    @NotNull
    private double prices;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "idCategory")
    private Category category;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "product")
    @JsonManagedReference
    Set<UserProduct> userProduct;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Size> sizes = new HashSet<>();

    public Product() {
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product(int id, String title, String img, String description, double prices, Category category) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.description = description;
        this.prices = prices;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrices() {
        return prices;
    }

    public void setPrices(double prices) {
        this.prices = prices;
    }

    public Set<UserProduct> getUserProduct() {
        return userProduct;
    }

    public void setUserProduct(Set<UserProduct> userProduct) {
        this.userProduct = userProduct;
    }

    public Set<Size> getSizes() {
        return sizes;
    }

    public void setSizes(Set<Size> sizes) {
        this.sizes = sizes;
    }
    public String getFormat(){
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        String formattedTotal = formatter.format(prices) + " VND";
        return formattedTotal;
    }
}
