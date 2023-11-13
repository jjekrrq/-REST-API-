package com.example.interactionapi.controller;

import com.example.interactionapi.dto.CustomerDTO;
import com.example.interactionapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/new")
    public String createCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.createCustomer(customerDTO);
    }
    @GetMapping("/{name}")
    public CustomerDTO findCustomerByName(@PathVariable("name") String customerName){
        return customerService.findCustomerByName(customerName).makeCustomerToDto();
    }
    @PutMapping("")
    public String updateCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.updateCustomer(customerDTO);
    }
    @DeleteMapping("")
    public String deleteCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.deleteCustomer(customerDTO);
    }
}
