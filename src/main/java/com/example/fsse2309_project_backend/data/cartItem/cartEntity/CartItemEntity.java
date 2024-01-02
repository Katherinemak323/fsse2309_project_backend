package com.example.fsse2309_project_backend.data.cartItem.cartEntity;



import com.example.fsse2309_project_backend.data.product.entity.ProductEntity;
import com.example.fsse2309_project_backend.data.user.entity.UserEntity;
import jakarta.persistence.*;

@Entity
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;

    @ManyToOne
    @JoinColumn (name = "uid", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn (name ="pid", nullable = false)
    private ProductEntity product;

    @Column(nullable = false)
    private Integer quantity;

    public CartItemEntity(UserEntity userEntity, ProductEntity productEntity, int quantity){
        this.user = userEntity;
        this.product = productEntity;
        this.quantity = quantity;
    }

    public CartItemEntity() {

    }


    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
