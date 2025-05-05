package com.zele.ishop.service;


import com.zele.ishop.dto.product.CreateProductRequest;
import com.zele.ishop.dto.product.ProductDto;
import com.zele.ishop.dto.shopowner.ShopOwnerDto;
import com.zele.ishop.dto.user.*;
import com.zele.ishop.mapper.ProductMapper;
import com.zele.ishop.repository.ProductRepository;
import com.zele.ishop.repository.ShopOwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.zele.ishop.mapper.ShopOwnerMapper;

import java.util.List;

@Service
@AllArgsConstructor
public class ShopOwnerService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductService productService;
    private ShopOwnerRepository shopOwnerRepository;
    private ShopOwnerMapper shopOwnerMapper;

    public List<ShopOwnerDto> getAllShopOwners(String sort) {
        return shopOwnerRepository.findAll(Sort.by(sort)).stream().map(shopOwnerMapper::toDto).toList();
    }

    public ResponseEntity<ShopOwnerDto> getShopOwner(Long shopOwnerId) {
        var shopOwner = shopOwnerRepository.findById(shopOwnerId).orElse(null);
        if (shopOwnerRepository.findById(shopOwnerId).isEmpty()) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
        return ResponseEntity.status(HttpStatus.OK).body(shopOwnerMapper.toDto(shopOwner));
    }

    public ResponseEntity<ShopOwnerDto> registerShopOwner(RegisterShopOwnerRequest request) {
        var shopOwner = shopOwnerMapper.toEntity(request);
        if (
                shopOwnerRepository.findByEmail(shopOwner.getEmail()) != null || shopOwnerRepository.findByUsername(shopOwner.getUsername()) != null
        ) {return ResponseEntity.status(HttpStatus.FORBIDDEN).build();}
        shopOwnerRepository.save(shopOwner);
        return ResponseEntity.status(HttpStatus.CREATED).body(shopOwnerMapper.toDto(shopOwner));
    }

    public ResponseEntity<ShopOwnerDto> updateShopOwner(UserUpdateRequest request, Long shopOwnerId) {
        var shopOwner = shopOwnerRepository.findById(shopOwnerId).orElse(null);
        if (shopOwner == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
        shopOwnerMapper.update(request, shopOwner);
        shopOwnerRepository.save(shopOwner);
        return ResponseEntity.status(HttpStatus.OK).body(shopOwnerMapper.toDto(shopOwner));
    }

    public ResponseEntity<Void> deleteShopOwner(Long shopOwnerId) {
        var shopOwner = shopOwnerRepository.findById(shopOwnerId).orElse(null);
        if (shopOwner == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
        shopOwnerRepository.delete(shopOwner);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity<Void> changePassword(Long shopOwnerId, ShopOwnerChangePasswordRequest request) {
        var shopOwner = shopOwnerRepository.findById(shopOwnerId).orElse(null);
        if (shopOwner == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (!request.getOldPassword().equals(shopOwner.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        shopOwner.setPassword(request.getNewPassword());
        shopOwnerRepository.save(shopOwner);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity<ShopOwnerDto> shopOwnerLogin(
             UserLoginRequest request
    ) {
        var shopOwner = shopOwnerRepository.findByUsername(request.getUsername());
        if (shopOwner == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
        if (!shopOwner.getPassword().equals(request.getPassword())) {return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(shopOwnerMapper.toDto(shopOwner));
    }

    public ResponseEntity<ProductDto> addProduct(
            Long shopOwnerId,
            CreateProductRequest request
    ) {
        return productService.addProductToShopOwner(shopOwnerId, request);
    }

    public List<ProductDto> getProducts(Long shopOwnerId) {
        return productService.showProductListByShopOwnerId(shopOwnerId);
    }

//    public ResponseEntity<ProductDto> deleteProduct(Long shopOwnerId, Long productId) {
//        return productService.deleteProductById(shopOwnerId, productId);
//    }
}
