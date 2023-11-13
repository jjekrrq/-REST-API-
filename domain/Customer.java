package com.example.interactionapi.domain;

import com.example.interactionapi.dto.CustomerDTO;
import com.example.interactionapi.dto.OrderDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
// DDD - 도메인이 직접 처리할 수 있는 것들은 도메인 영역 안에서 해결하는 것이 좋다.
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue
    @Column(name = "CUSTOMER_ID")
    private Integer id;

    @Column(name = "CUSTOMER_NAME", nullable = false)
    private String name;

    @Column(name = "CUSTOMER_ADDRESS", nullable = false)
    private String address;

    @OneToMany(mappedBy = "customer")
    @Column(name = "CUSTOMER_ORDER")
    private List<Buy> buys = new ArrayList<>();

    public CustomerDTO makeCustomerToDto(){
        List<OrderDTO> orderDTOS = buys.stream().map(Buy::makeOrderToDto).toList();
        return CustomerDTO.builder()
                .id(this.id)
                .name(this.name)
                .address(this.address)
                .orderDTOList(orderDTOS)
                .build();
    }
    public String getName(){
        return this.name;
    }
    public String getAddress(){
        return this.address;
    }

    public void update(Customer customer){
        this.id = id;
        this.name = name;
        this.address = address;
    }
    public List<Buy> getCustomer(){
        return Collections.unmodifiableList(buys);
    }
}
