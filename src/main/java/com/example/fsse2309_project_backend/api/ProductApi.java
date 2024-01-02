package com.example.fsse2309_project_backend.api;

import com.example.fsse2309_project_backend.data.product.domainObject.ProductDetailData;
import com.example.fsse2309_project_backend.data.product.dto.response.GetAllProductResponseDto;
import com.example.fsse2309_project_backend.data.product.dto.response.ProductDetailResponseDto;
import com.example.fsse2309_project_backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/public/product")
@CrossOrigin ( "http://localhost:5173")
public class ProductApi {
    private ProductService productService;

    @Autowired
    public ProductApi (ProductService productService){
        this.productService = productService;
    }

    @GetMapping("")
    public List<GetAllProductResponseDto> getAllProduct() {
        List<GetAllProductResponseDto> responseDtoList = new ArrayList<>();

        for (ProductDetailData data : productService.getAllProduct()) {
            responseDtoList.add(new GetAllProductResponseDto (data));
        }
        return responseDtoList;
    }


    @GetMapping("/{pid}")
    public ProductDetailResponseDto getProductByPid (@PathVariable Integer pid){
        return new ProductDetailResponseDto(productService.getProductByPid(pid));
    }
}
