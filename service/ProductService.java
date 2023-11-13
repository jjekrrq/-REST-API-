package com.example.interactionapi.service;

import com.example.interactionapi.domain.Product;
import com.example.interactionapi.dto.ProductDTO;
import com.example.interactionapi.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor  // final 등
public class ProductService {
    private final ProductRepository productRepository;

    // CREATE , 상품 정보 저장하기.
    @Transactional
    public String createProduct(ProductDTO productDTO){
        Product product = Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .count(productDTO.getCount())
                .build();
        productRepository.save(product);
        return "저장 성공";
    }
    // READ , 상품 이름으로 상품 가져오기
    public Product findProductByName(String name){
        return productRepository.findProductByName(name)
                .orElseThrow(()-> new IllegalArgumentException("잘못된 상품 이름입니다."));
    }

    // UPDATE , 상품 업데이트 하기
    @Transactional
    public String updateProduct(ProductDTO productDTO){
        Product product = productRepository.findById(productDTO.getId())
                .orElseThrow(()-> new IllegalArgumentException("잘못된 상품 이름입니다"));
        product.update(Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .count(productDTO.getCount())
                .build());
        return "수정 완료";
    }
    //DELETE
    @Transactional
    public String deleteProduct(ProductDTO productDTO){
        Product product = findProductByName(productDTO.getName());
        productRepository.delete(product);
        return "삭제 완료";
    }
}
