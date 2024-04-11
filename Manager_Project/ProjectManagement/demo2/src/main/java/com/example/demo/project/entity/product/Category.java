package com.example.demo.project.entity.product;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {
    @Id
    private int idCategory;
    @NotNull
    private String nameCategory;
    @OneToMany(mappedBy ="category", cascade = CascadeType.ALL)
    private List<Product>productsList;

    public Category() {
    }

    public Category(int idCategory, String nameCategory) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
