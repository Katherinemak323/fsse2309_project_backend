package com.example.fsse2309_project_backend.service.impl;

import com.example.fsse2309_project_backend.data.cartItem.cartEntity.CartItemEntity;
import com.example.fsse2309_project_backend.data.domainObject.CartItemDetailData;
import com.example.fsse2309_project_backend.data.product.entity.ProductEntity;
import com.example.fsse2309_project_backend.data.user.domainObject.FirebaseUserData;
import com.example.fsse2309_project_backend.data.user.entity.UserEntity;
import com.example.fsse2309_project_backend.exception.cartItem.CartItemNotFoundException;
import com.example.fsse2309_project_backend.exception.cartItem.InvalidStockException;
import com.example.fsse2309_project_backend.exception.cartItem.StockNotEnoughException;
import com.example.fsse2309_project_backend.repository.CartItemRepository;
import com.example.fsse2309_project_backend.service.CartItemService;
import com.example.fsse2309_project_backend.service.ProductService;
import com.example.fsse2309_project_backend.service.UserService;

import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    private Logger logger = LoggerFactory.getLogger(CartItemServiceImpl.class);
    private UserService userService;
    private CartItemRepository cartItemRepository;
    private ProductService productService;

    public CartItemServiceImpl(UserService userService, CartItemRepository cartItemRepository, ProductService productService) {
        this.userService = userService;
        this.cartItemRepository = cartItemRepository;
        this.productService = productService;
    }


    @Override
    public boolean addCartItem(int pid, int quantity, FirebaseUserData firebaseUserData) {
        try {
            UserEntity userEntity = getUserEntity(firebaseUserData);
            ProductEntity productEntity = productService.getEntityByPid(pid);

            Optional<CartItemEntity> optionalCartItemEntity = cartItemRepository.findByUser_UidAndProduct_Pid(userEntity.getUid(), pid);
            CartItemEntity cartItemEntity;

            if (optionalCartItemEntity.isPresent()) {                                //如果係exist就比個uid pid佢
                cartItemEntity = optionalCartItemEntity.get();
                if (!isValidStock(productEntity, cartItemEntity.getQuantity() + quantity)) {
                    throw new StockNotEnoughException();
                }
                cartItemEntity.setQuantity(cartItemEntity.getQuantity() + quantity);

            } else {
                cartItemEntity = new CartItemEntity(userEntity, productEntity, quantity);
                if (!isValidStock(productEntity, cartItemEntity.getQuantity() + quantity)) {
                    throw new StockNotEnoughException();
                }cartItemRepository.save(cartItemEntity);
            }
            return true;
        } catch (Exception ex) {
            logger.warn("Put Cart Item Failed");
            throw ex;
        }
    }




    @Override
    public List<CartItemDetailData> getAllByFirebaseUserData(FirebaseUserData firebaseUserData){
        UserEntity userEntity = getUserEntity(firebaseUserData);
        return getDataListByUser(userEntity);

    }


    @Override
    public CartItemDetailData patchCartQuantity(int pid, int quantity, FirebaseUserData firebaseUserData) {
        try {
            UserEntity userEntity = getUserEntity(firebaseUserData);
            ProductEntity productEntity = productService.getEntityByPid(pid);

            CartItemEntity cartItemEntity = getEntityByUidAndPid(userEntity.getUid(), productEntity.getPid());

            if(!isValidStock(productEntity, quantity)){
                throw new StockNotEnoughException();
            }
            cartItemEntity.setQuantity(quantity);
            return new CartItemDetailData(cartItemRepository.save(cartItemEntity));
        } catch (Exception ex) {
            logger.warn("Patch Cart Quantity Failed");
            throw ex;
        }
    }



    @Transactional
    @Override
    public boolean deleteCartItemByPid(FirebaseUserData firebaseUserData,
                                       Integer pid){
        UserEntity userEntity = getUserEntity(firebaseUserData);
        getEntityByUidAndPid(userEntity.getUid(), pid);

        cartItemRepository.deleteByUser_UidAndProduct_Pid(userEntity.getUid(), pid);
        return true;
    }

    public UserEntity getUserEntity(FirebaseUserData data){
        return userService.getEntityByFirebaseUserData(data);
    }



    public CartItemEntity getEntityByUidAndPid (Integer uid, Integer pid){
//        Optional<CartItemEntity> optionalCartItemEntity = cartItemRepository.findByUser_UidAndProduct_Pid(uid, pid);
//
//        if(optionalCartItemEntity.isPresent()) {
//            return optionalCartItemEntity.get();                                    //有野就return object
//        } else {
//            return null;
        return cartItemRepository.findByUser_UidAndProduct_Pid(uid, pid)
                .orElseThrow(CartItemNotFoundException::new);
    }


    public List<CartItemEntity> getEntityListByUser(UserEntity user) {
        return cartItemRepository.findAllByUser(user);
    }


    public List<CartItemDetailData> getDataListByUser (UserEntity user) {
        List<CartItemDetailData> dataList = new ArrayList<>();

        for (CartItemEntity entity : getEntityListByUser(user)) {
            dataList.add(new CartItemDetailData(entity));
        }
        return dataList;
    }


    @Override
    public boolean isValidStock(ProductEntity productEntity, Integer quantity) {
        if (quantity > 0 && productEntity.getStock() >= quantity) {
            return true;
        } else {
            throw new InvalidStockException();
        }
    }

    @Override
    public CartItemDetailData[] getAllFirebaseUserData(FirebaseUserData firebaseUser) {
        return new CartItemDetailData[0];
    }


    public CartItemDetailData patchCartQuantity(FirebaseUserData firebaseUser, int pid, int quantity) {
        return null ;
    }


}

