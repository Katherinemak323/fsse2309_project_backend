package com.example.fsse2309_project_backend.data.domainObject;

import com.example.fsse2309_project_backend.data.cartItem.cartEntity.CartItemEntity;
import com.example.fsse2309_project_backend.data.product.domainObject.ProductDetailData;
import com.example.fsse2309_project_backend.data.user.domainObject.UserDetailData;

public class CartItemDetailData {

    private Integer cid;
    private UserDetailData user;
    private ProductDetailData product;
    private Integer quantity;

    public CartItemDetailData (CartItemEntity entity){
        this.cid = entity.getCid();
        this.user = new UserDetailData(entity.getUser());
        this.product = new ProductDetailData(entity.getProduct());
        this.quantity = entity.getQuantity();
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public UserDetailData getUser() {
        return user;
    }

    public void setUser(UserDetailData user) {
        this.user = user;
    }

    public ProductDetailData getProduct() {
        return product;
    }

    public void setProduct(ProductDetailData product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
