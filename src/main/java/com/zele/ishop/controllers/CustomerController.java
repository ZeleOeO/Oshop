package com.zele.ishop.controllers;

import com.zele.ishop.dto.user.UserDto;
import com.zele.ishop.dto.user.UserRegisterRequest;
import com.zele.ishop.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/all")
    public List<UserDto> getAllCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getCustomerById(@PathVariable long id) {
        return customerService.getCustomer(id);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> createCustomer(UserRegisterRequest request) {
        return customerService.createCustomer(request);
    }
}
