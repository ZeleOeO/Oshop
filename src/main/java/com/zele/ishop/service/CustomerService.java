package com.zele.ishop.service;

import com.zele.ishop.dto.product.ProductDto;
import com.zele.ishop.dto.user.*;
import com.zele.ishop.entity.Customer;
import com.zele.ishop.mapper.CustomerMapper;
import com.zele.ishop.mapper.ProductMapper;
import com.zele.ishop.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public List<UserDto> getCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::toDto).toList();
    }

    public ResponseEntity<UserDto> getCustomer(Long id) {
        var customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
        return ResponseEntity.status(HttpStatus.OK).body(customerMapper.toDto(customer));
    }

    public ResponseEntity<UserDto> createCustomer(UserRegisterRequest request) {
       var customer = customerMapper.toEntityFromRequest(request);
        if (
                customerRepository.findByEmail(request.getEmail()) != null ||
                        customerRepository.findByUsername(request.getUsername()) != null
        ) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(customerMapper.toDto(customer));
        }
        System.out.println(customer);
        System.out.println(request);
        System.out.println(customerRepository.findByEmail(request.getEmail()));
        customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerMapper.toDto(customer));
    }

    public ResponseEntity<UserDto> loginCustomer(UserLoginRequest request) {
        var customer = customerRepository.findByUsername(request.getUsername());
        if (customer == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
        if (!request.getPassword().equals(customer.getPassword())) {return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}
        return ResponseEntity.status(HttpStatus.OK).body(customerMapper.toDto(customer));
    }

    public ResponseEntity<UserDto> updateCustomer(UserUpdateRequest request, Long id) {
        var customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
        customerMapper.toUpdate(request, customer);
        customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.OK).body(customerMapper.toDto(customer));
    }

    public ResponseEntity<UserDto> deleteCustomer(Long id) {
        var customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
        customerRepository.delete(customer);
        return ResponseEntity.status(HttpStatus.OK).body(customerMapper.toDto(customer));
    }

    public ResponseEntity<UserDto> changePassword(UserChangePasswordRequest request, Long id) {
        var customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
        if (!request.getOldPassword().equals(customer.getPassword())) {return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}
        customer.setPassword(request.getNewPassword());
        customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.OK).body(customerMapper.toDto(customer));
    }

    @Transactional()
    public List<ProductDto> getCart(Long customerId) {
        var customer = customerRepository.findByIdWithCart(customerId).orElse(null);
        if (customer==null) {return List.of();}
        return new ArrayList<>(customer.getCart())
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Transactional
    public ResponseEntity<ProductDto> addProductToCart(Long customerId, Long productId) {
        var customer = customerRepository.findById(customerId).orElse(null);
        if (customer==null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
        var product = productRepository.findById(productId).orElse(null);
        customer.getCart().add(product);
        customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.OK).body(productMapper.toDto(product));
    }
}