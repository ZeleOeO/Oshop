package com.zele.ishop.service;

import com.zele.ishop.dto.user.UserDto;
import com.zele.ishop.dto.user.UserLoginRequest;
import com.zele.ishop.dto.user.UserRegisterRequest;
import com.zele.ishop.dto.user.UserUpdateRequest;
import com.zele.ishop.entity.Customer;
import com.zele.ishop.mapper.CustomerMapper;
import com.zele.ishop.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public List<UserDto> getCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::toDto).toList();
    }

    public ResponseEntity<UserDto> getCustomer(Long id) {
        var customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
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

}
