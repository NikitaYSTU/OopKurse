package com.example.drugs.code.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "drugs")
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String amount;
    private Boolean recipeRequired;
    private String purpose;
    private String price;
    private String type;

    public Drug() {
    }

    public Drug(Integer id, String name, String amount, Boolean recipeRequired, String purpose, String price, String type) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.recipeRequired = recipeRequired;
        this.purpose = purpose;
        this.price = price;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Boolean getRecipeRequired() {
        return recipeRequired;
    }

    public void setRecipeRequired(Boolean recipeRequired) {
        this.recipeRequired = recipeRequired;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}