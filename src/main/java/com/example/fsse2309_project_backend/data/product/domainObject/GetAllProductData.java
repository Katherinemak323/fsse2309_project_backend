package com.example.fsse2309_project_backend.data.product.domainObject;

import com.example.fsse2309_project_backend.data.product.entity.ProductEntity;


import java.math.BigDecimal;

public class GetAllProductData {


    private Integer pid;

    private String name;

    private String description;

    private String imageUrl;

    private Integer stock;

    private BigDecimal price;

    public GetAllProductData (ProductEntity entity){
        this.pid = entity.getPid();
        this.name = entity.getName();
        this.description = entity.getDescription();;
        this.imageUrl = entity.getImageUrl();
        this.stock = entity.getStock();;
        this.price = entity.getPrice();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
