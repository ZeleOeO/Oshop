package com.zele.ishop.controllers;

import com.zele.ishop.dto.product.CreateProductRequest;
import com.zele.ishop.dto.product.ProductDto;
import com.zele.ishop.dto.user.UserDto;
import com.zele.ishop.dto.user.*;
import com.zele.ishop.service.ShopOwnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/shopowner")
@AllArgsConstructor
public class ShopOwnerController {
    private final ShopOwnerService shopOwnerService;

    @GetMapping("/all")
    public List<UserDto> getAllShopOwners(@RequestParam(required = false, defaultValue = "",name = "sort") String sort) {
        if(!Set.of("username", "email", "password").contains(sort)) {sort="username";}
        return shopOwnerService.getAllShopOwners(sort);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getShopOwnerById(@PathVariable Long id) {
        return shopOwnerService.getShopOwner(id);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> createShopOwner(
            @RequestBody UserRegisterRequest request
    ) {
        return shopOwnerService.registerShopOwner(request);
    }


    @PostMapping("/login")
    public ResponseEntity<UserDto> loginShopOwner(
            @RequestBody UserLoginRequest request
    ) {
        return shopOwnerService.shopOwnerLogin(request);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<UserDto> updateShopOwner(
            @PathVariable Long id,
            @RequestBody UserUpdateRequest request
    )
    {
        return shopOwnerService.updateShopOwner(request, id);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteShopOwner(@PathVariable Long id) {
        return shopOwnerService.deleteShopOwner(id);
    }

    @PostMapping("/{id}/change-password")
    public ResponseEntity<Void> changePassword(
            @PathVariable Long id,
            @RequestBody UserChangePasswordRequest request
    ) {
        return shopOwnerService.changePassword(id, request);
    }

    @PostMapping("/{id}/add-product")
    public ResponseEntity<ProductDto> addProduct(
            @PathVariable Long id,
            @RequestBody CreateProductRequest request
    ) {
        return shopOwnerService.uploadProduct(id, request);
    }

    @GetMapping("/{id}/all-products")
    public List<ProductDto> getAllProducts(
            @PathVariable Long id
    ) {
        return shopOwnerService.getProducts(id);
    }

//    @DeleteMapping("/{shopOwner_Id}/delete/{product_id}")
//    public ResponseEntity<ProductDto> deleteProduct(
//            @PathVariable Long shopOwner_Id,
//            @PathVariable Long product_id
//    ) {
//        return shopOwnerService.deleteProduct(shopOwner_Id, product_id);
//    }
}
