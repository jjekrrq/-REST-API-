package com.example.interactionapi.domain;

import com.example.interactionapi.dto.OrderDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Buy {
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Integer id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY) // 다대일 연관 관계의 fetchType 디폴트는 EAGER, EAGER은 일대일 연관 관계에 주로 사용.
    @JoinColumn(name = "CUSTOMER_ID")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Customer customer;

    public OrderDTO makeOrderToDto(){
        if(customer != null && product != null){
            return OrderDTO.builder()
                    .id(this.id)
                    .name(this.name)
                    .customerName(customer.getName())
                    .productName(product.getName())
                    .build();
        }
        if(customer == null && product != null){
            return OrderDTO.builder()
                    .id(this.id)
                    .name(this.name)
                    .productName(product.getName())
                    .build();
        }
        if(customer != null && product == null){
            return OrderDTO.builder()
                    .id(this.id)
                    .name(this.name)
                    .customerName(customer.getName())
                    .build();
        }
        return OrderDTO.builder()
                    .id(this.id)
                    .name(this.name)
                    .build();

    }

    public void customerUpdate(Buy buy){
        this.id = id;
        if(customer != null){
            changeCustomer(buy.customer);
            return;
        }
        this.product = product;
    }
    public void productUpdate(Buy buy){
        this.id = id;
        this.customer = customer;
        if(product != null){
            changeProduct(buy.product);
            return;
        }
    }
    public void changeCustomer(Customer customer){
        this.customer = customer;
        product.getProduct().add(this);
    }
    public void changeProduct(Product product){
        this.product = product;
        product.getProduct().add(this);
    }


}
