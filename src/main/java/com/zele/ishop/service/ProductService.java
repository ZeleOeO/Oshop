package com.zele.ishop.service;

import com.zele.ishop.dto.product.CreateProductRequest;
import com.zele.ishop.dto.product.ProductDto;
import com.zele.ishop.entity.Product;
import com.zele.ishop.mapper.ProductMapper;
import com.zele.ishop.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;
    private ProductMapper productMapper;
    private ShopOwnerRepository shopOwnerRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public ResponseEntity<ProductDto> getProductById(Long id) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
        return ResponseEntity.status(HttpStatus.OK).body(productMapper.toDto(product));
    }

    public ResponseEntity<ProductDto> addProductToShopOwner(
            Long shopOwnerId,
            CreateProductRequest request
    ) {
        var shopOwner = shopOwnerRepository.findById(shopOwnerId).orElse(null);
        if (shopOwner == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
        var product = productMapper.toEntity(request);
        product.setShopOwner(shopOwner);
        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.toDto(product));
    }

    public List<ProductDto> showProductListByShopOwnerId(Long shopOwnerId) {
        var product = productRepository.findAllByShopOwner_Id(shopOwnerId);
        if (product == null) {ResponseEntity.status(HttpStatus.NOT_FOUND).build(); return null;}
        return product.stream().map(productMapper::toDto).toList();
    }

//    public ResponseEntity<ProductDto> deleteProductById(Long productId, Long shopOwnerId) {
//        var product = productRepository.findByShopOwner_IdAndId(shopOwnerId, productId);
//        if (product == null) {ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
//        productRepository.delete(product);
//        return ResponseEntity.status(HttpStatus.OK).body(productMapper.toDto(product));
//
//    }
}
