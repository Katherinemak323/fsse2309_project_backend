package com.example.fsse2309_project_backend.api;

import com.example.fsse2309_project_backend.data.cartItem.dto.response.CartItemResponseDto;
import com.example.fsse2309_project_backend.data.cartItem.dto.response.SuccessResponseDto;
import com.example.fsse2309_project_backend.data.domainObject.CartItemDetailData;
import com.example.fsse2309_project_backend.service.CartItemService;
import com.example.fsse2309_project_backend.util.JwtUtil;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin("http://localhost:5173")
public class CartItemApi {

    private CartItemService cartItemService;

    public CartItemApi (CartItemService cartItemService){
        this.cartItemService = cartItemService;
    }

    @PutMapping ("/{pid}/{quantity}")
    public SuccessResponseDto addCartItem (JwtAuthenticationToken jwt,
                                           @PathVariable int pid,
                                           @PathVariable int quantity) {
        cartItemService.addCartItem (
                pid, quantity,
                JwtUtil.getFirebaseUser(jwt)
        );
        return new SuccessResponseDto();
    }

    @GetMapping()
    public List<CartItemResponseDto> getAllCartItem (JwtAuthenticationToken jwt) {
        List <CartItemResponseDto> dtoList = new ArrayList<>();

        for (CartItemDetailData data: cartItemService.getAllFirebaseUserData (JwtUtil.getFirebaseUser(jwt))){
            dtoList.add (new CartItemResponseDto(data));
        }
        return dtoList;
    }



    @PatchMapping("/{pid}/{quantity}")
    public CartItemResponseDto patchCartQuantity (JwtAuthenticationToken jwt,
                                                  @PathVariable int pid,
                                                  @PathVariable int quantity){
        return new CartItemResponseDto(
                cartItemService.patchCartQuantity(
                        JwtUtil.getFirebaseUser(jwt),
                        pid, quantity
                )
        );
    }

    @DeleteMapping("/{pid}")
    public SuccessResponseDto deleteCartItem (JwtAuthenticationToken jwt,
                                              @PathVariable Integer pid){
        cartItemService.deleteCartItemByPid(
                JwtUtil.getFirebaseUser(jwt),
                pid
        );
        return new SuccessResponseDto();
    }
}

