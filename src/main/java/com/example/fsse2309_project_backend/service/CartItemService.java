package com.example.fsse2309_project_backend.service;


import com.example.fsse2309_project_backend.data.domainObject.CartItemDetailData;
import com.example.fsse2309_project_backend.data.product.entity.ProductEntity;
import com.example.fsse2309_project_backend.data.user.domainObject.FirebaseUserData;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CartItemService {
    boolean addCartItem(int pid, int quantity, FirebaseUserData firebaseUser);


    List<CartItemDetailData> getAllByFirebaseUserData(FirebaseUserData firebaseUserData);


    CartItemDetailData patchCartQuantity(int pid, int quantity, FirebaseUserData firebaseUserData);

    @Transactional
    boolean deleteCartItemByPid(FirebaseUserData firebaseUserData, Integer pid);

    boolean isValidStock(ProductEntity productEntity, Integer quantity);

    CartItemDetailData[] getAllFirebaseUserData(FirebaseUserData firebaseUser);

    CartItemDetailData patchCartQuantity(FirebaseUserData firebaseUser, int pid, int quantity);
}
