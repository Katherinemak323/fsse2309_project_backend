package com.example.fsse2309_project_backend.repository;
import com.example.fsse2309_project_backend.data.product.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository <ProductEntity, Integer>{
}
