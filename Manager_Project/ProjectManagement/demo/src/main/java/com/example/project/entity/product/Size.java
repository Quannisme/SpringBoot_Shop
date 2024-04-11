package com.example.project.entity.product;

import com.example.project.entity.UserProduct;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Size {
    @Id
    @GeneratedValue
    private int id;
    private String sizeLabel; // S, M, L
    @OneToMany(mappedBy = "size")
    @JsonManagedReference
    Set<UserProduct> size;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "product_id")
    private Product product;

    public Size() {
    }

    public Size(int id, String sizeLabel, Set<UserProduct> size, Product product) {
        this.id = id;
        this.sizeLabel = sizeLabel;
        this.size = size;
        this.product = product;
    }

    public Set<UserProduct> getSize() {
        return size;
    }

    public void setSize(Set<UserProduct> size) {
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSizeLabel() {
        return sizeLabel;
    }

    public void setSizeLabel(String sizeLabel) {
        this.sizeLabel = sizeLabel;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
