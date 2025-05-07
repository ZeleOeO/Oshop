package com.zele.ishop.controllers;

import com.zele.ishop.dto.product.ProductCreateRequest;
import com.zele.ishop.dto.product.ProductUpdateRequest;
import com.zele.ishop.dto.product.ProductUserViewDto;
import com.zele.ishop.entity.Product;
import com.zele.ishop.service.ProductService;
import com.zele.ishop.service.ShopOwnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ShopOwnerService shopOwnerService;

    @GetMapping("/all")
    public List<ProductUserViewDto> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductUserViewDto> getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/new-{id}")
    public ResponseEntity<ProductUserViewDto> addProduct(
            @PathVariable Long id,
            @RequestBody ProductCreateRequest request
    ){
        return shopOwnerService.addProduct(id, request);
    }

    @PutMapping("/update-{shopOwnerId}/{productId}")
    public ResponseEntity<ProductUserViewDto> updateProduct(
            @PathVariable Long shopOwnerId,
            @PathVariable Long productId,
            @RequestBody ProductUpdateRequest request
    ) {
        return shopOwnerService.updateProduct(shopOwnerId, productId, request);
    }

   @DeleteMapping("/delete-{shopOwnerId}/{productId}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long shopOwnerId,
            @PathVariable Long productId
   ) {
        return shopOwnerService.deleteProduct(shopOwnerId, productId);
   }
}
