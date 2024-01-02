package com.example.fsse2309_project_backend.service.impl;

import com.example.fsse2309_project_backend.data.product.domainObject.ProductDetailData;
import com.example.fsse2309_project_backend.data.product.entity.ProductEntity;
import com.example.fsse2309_project_backend.exception.product.ProductNotFoundException;
import com.example.fsse2309_project_backend.repository.ProductRepository;
import com.example.fsse2309_project_backend.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    public ProductServiceImpl (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

//    private List<ProductEntity> productEntityList = new ArrayList<>();


    @Override
    public List<ProductDetailData> getAllProduct() {
        List <ProductDetailData> productDetailDataList = new ArrayList<>();

        for (ProductEntity entity: productRepository.findAll()) {
            productDetailDataList.add(new ProductDetailData(entity));
        }
        return productDetailDataList;
    }

    @Override
    public ProductDetailData getProductByPid(Integer pid){
        return new ProductDetailData(getEntityByPid(pid));
    }

    @Override
    public ProductEntity getEntityByPid(Integer pid){
        return productRepository.findById(pid).orElseThrow(ProductNotFoundException::new);
    }


    @Override
    public boolean isEnoughStock(Integer pid, Integer quantity) {
        ProductEntity productEntity = getEntityByPid(pid);
        return  productEntity.getStock() >= quantity;
    }
}
