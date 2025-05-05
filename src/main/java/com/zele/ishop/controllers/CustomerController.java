package com.zele.ishop.controllers;

import com.zele.ishop.dto.user.*;
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
    public ResponseEntity<UserDto> createCustomer(@RequestBody UserRegisterRequest request) {
        return customerService.createCustomer(request);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> userLogin(@RequestBody UserLoginRequest request) {
        return customerService.loginCustomer(request);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<UserDto> updateCustomer(@PathVariable long id, @RequestBody UserUpdateRequest request) {
        return customerService.updateCustomer(request, id);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<UserDto> deleteCustomer(@PathVariable long id) {
        return customerService.deleteCustomer(id);
    }

    @PostMapping("/{id}/change-password")
    public ResponseEntity<UserDto> changePassword(@PathVariable long id, @RequestBody UserChangePasswordRequest request) {
        return customerService.changePassword(request, id);
    }
}
