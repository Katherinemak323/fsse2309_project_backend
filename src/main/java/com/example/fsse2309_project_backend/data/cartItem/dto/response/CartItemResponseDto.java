package com.example.fsse2309_project_backend.data.cartItem.dto.response;


import com.example.fsse2309_project_backend.data.domainObject.CartItemDetailData;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CartItemResponseDto {

    private Integer pid;
    public String name;
    @JsonProperty("image_url")
    private String imageUrl;
    private BigDecimal price;
    @JsonProperty("cart_quantity")
    private Integer cartQuantity;

    private Integer stock;

    public CartItemResponseDto (CartItemDetailData data){
        this.pid = data.getProduct().getPid();
        this.name = data.getProduct().getName();
        this.imageUrl = data.getProduct().getImageUrl();
        this.price = data.getProduct().getPrice();
        this.stock = data.getProduct().getStock();
        this.cartQuantity = data.getQuantity();
    }


    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(Integer cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}

