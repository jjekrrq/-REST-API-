package com.example.interactionapi.controller;

import com.example.interactionapi.dto.OrderDTO;
import com.example.interactionapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/buy")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("customer/new")
    public String creatCustomereOrder(@RequestBody OrderDTO orderDTO){
        return orderService.createCustomerOrder(orderDTO);
    }
    @PostMapping("/product/new")
    public String createProductOrder(@RequestBody OrderDTO orderDTO){
        return orderService.createProductOrder(orderDTO);
    }
    @GetMapping("/customer/{name}")
    public OrderDTO findCustomerOrder(@PathVariable("name")String customerName){
        return orderService.findOrderByNameFromCustomerDto(customerName);
    }
    @GetMapping("/product/{name}")
    public OrderDTO findProdcutOrder(@PathVariable("name")String productName){
        return orderService.findOrderByNameFromProductDto(productName);
    }
    @PutMapping("/customer")
    public String updateCustomerFromOrder(@RequestBody OrderDTO orderDTO){
        return orderService.updateCustomerOrder(orderDTO);
    }
    @PutMapping("/product")
    public String updateProductFromOrder(@RequestBody OrderDTO orderDTO){
        return orderService.updateOrderProduct(orderDTO);
    }
    @DeleteMapping("")
    public String deleteOrder(@RequestBody OrderDTO orderDTO){
        return orderService.deleteOrderById(orderDTO);
    }
}
