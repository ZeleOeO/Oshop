package com.zele.ishop.service;

import com.zele.ishop.dto.product.ProductCreateRequest;
import com.zele.ishop.dto.product.ProductUpdateRequest;
import com.zele.ishop.dto.product.ProductUserViewDto;
import com.zele.ishop.entity.Role;
import com.zele.ishop.mapper.ProductMapper;
import com.zele.ishop.repository.ProductRepository;
import com.zele.ishop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShopOwnerService {
    private final UserRepository userRepository;
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ResponseEntity<ProductUserViewDto> addProduct(Long shopOwnerId, ProductCreateRequest request) {
        var shopOwner = userRepository.findById(shopOwnerId).orElse(null);
        if (shopOwner == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
        if (shopOwner.getRole().equals(Role.CUSTOMER)) {return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}
        var product = productMapper.productCreateRequestToProduct(request);
        product.setShopOwner(shopOwner);
        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.productToProductUserViewDto(product));
    }

    public ResponseEntity<ProductUserViewDto> updateProduct(Long shopOwnerId, Long productId, ProductUpdateRequest request) {
        var shopOwner = userRepository.findById(shopOwnerId).orElse(null);
        if (shopOwner == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
        if (shopOwner.getRole().equals(Role.CUSTOMER)) {return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}
        var product = productRepository.findById(productId).orElse(null);
        if (product == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
        if (!product.getShopOwner().getId().equals(shopOwnerId)) {return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}
        productMapper.update(request, product);
        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.productToProductUserViewDto(product));
    }

    public ResponseEntity<Void> deleteProduct(Long shopOwnerId, Long productId) {
        var shopOwner = userRepository.findById(shopOwnerId).orElse(null);
        if (shopOwner == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
        if (shopOwner.getRole().equals(Role.CUSTOMER)) {return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}
        var product = productRepository.findById(productId).orElse(null);
        if (product == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
        if (!product.getShopOwner().getId().equals(shopOwnerId)) {return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}
        productRepository.delete(product);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
