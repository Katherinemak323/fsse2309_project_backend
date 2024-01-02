package com.example.fsse2309_project_backend.repository;


import com.example.fsse2309_project_backend.data.cartItem.cartEntity.CartItemEntity;
import com.example.fsse2309_project_backend.data.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends CrudRepository<CartItemEntity, Integer> {
    Optional<CartItemEntity> findByUser_UidAndProduct_Pid (Integer uid, Integer pid);

    List<CartItemEntity> findAllByUser(UserEntity user);

    void deleteByUser_UidAndProduct_Pid(Integer uid, Integer pid);


}
