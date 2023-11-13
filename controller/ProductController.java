package com.example.interactionapi.controller;

import com.example.interactionapi.dto.ProductDTO;
import com.example.interactionapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/new")
    public String createProduct(@RequestBody ProductDTO productDTO){
        return productService.createProduct(productDTO);
    }
    @GetMapping("/{name}")
    public ProductDTO findProductByName(@PathVariable("name") String name){
        return productService.findProductByName(name).makeProductToDto();
    }
    @PutMapping("")
    public String updateProduct(@RequestBody ProductDTO productDTO){
        return productService.updateProduct(productDTO);
    }
    @DeleteMapping("")
    public String deleteProduct(@RequestBody ProductDTO productDTO){
        return productService.deleteProduct(productDTO);
    }
}
