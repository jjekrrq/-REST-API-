package com.example.interactionapi.domain;

import com.example.interactionapi.dto.OrderDTO;
import com.example.interactionapi.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "PRODUCT_ID")
    private Integer id;

    @Column(name = "PRODUCT_NAME", nullable = false)
    private String name;

    @Column(name = "PRODUCT_COUNT", nullable = false)
    private Integer count;

    @OneToMany(mappedBy = "product")
    @Column(name = "PRODUCT_ORDER")
    List<Buy> buys = new ArrayList<>();

    public String getName(){
        return this.name;
    }

    public ProductDTO makeProductToDto(){
        List<OrderDTO> orderDTOS = buys.stream().map(Buy::makeOrderToDto).toList();
        return ProductDTO.builder()
                .id(this.id)
                .name(this.name)
                .count(this.count)
                .orderDTOList(orderDTOS)
                .build();
    }
    public void update(Product product){
        this.id = product.id;
        this.name = product.name;
        this.count = product.count;
    }

    public List<Buy> getProduct(){
        return Collections.unmodifiableList(buys);
    }
}
