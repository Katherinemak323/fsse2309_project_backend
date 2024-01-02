package com.example.fsse2309_project_backend.service;

import com.example.fsse2309_project_backend.data.product.domainObject.ProductDetailData;
import com.example.fsse2309_project_backend.data.product.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    List<ProductDetailData> getAllProduct();

    ProductDetailData getProductByPid(Integer pid);

//    ProductEntity getEntityByPid(int pid);

    ProductEntity getEntityByPid(Integer pid);

    boolean isEnoughStock(Integer pid, Integer quantity);
}
