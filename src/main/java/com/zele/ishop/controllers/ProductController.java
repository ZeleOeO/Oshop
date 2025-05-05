//package com.zele.ishop.controllers;
//
//import com.zele.ishop.dto.Product.CreateProductRequest;
//import com.zele.ishop.dto.Product.ProductDto;
//import com.zele.ishop.service.ProductService;
//import com.zele.ishop.service.ShopOwnerService;
//import lombok.AllArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@AllArgsConstructor
//public class ProductController {
//   private ProductService productService;
//
//    @PostMapping("/{id}/add-product")
//    public ResponseEntity<ProductDto> addProduct(
//            @PathVariable Long id,
//            @RequestBody CreateProductRequest request
//    ) {
//        return productService.addProduct(id, request);
//    }
//
//}
