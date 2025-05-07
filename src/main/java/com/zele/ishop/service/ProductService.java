package com.zele.ishop.service;

import com.zele.ishop.dto.product.ProductUserViewDto;
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
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductUserViewDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::productToProductUserViewDto)
                .toList();
    }

    public ResponseEntity<ProductUserViewDto> getProductById(Long id) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
        return ResponseEntity.status(HttpStatus.OK).body(productMapper.productToProductUserViewDto(product));
    }
}