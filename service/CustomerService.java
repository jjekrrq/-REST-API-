package com.example.interactionapi.service;

import com.example.interactionapi.domain.Customer;
import com.example.interactionapi.dto.CustomerDTO;
import com.example.interactionapi.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    // CREATE , 구매자 정보 저장
    @Transactional
    public String createCustomer(CustomerDTO customerDTO){
        Customer customer = Customer.builder()
                .name(customerDTO.getName())
                .address(customerDTO.getAddress())
                .build();
        customerRepository.save(customer);
        return "저장 완료";
    }
    // READ , 구매자 정보 이름으로 찾기, 오류나면 "잘못된 구매자 이름임" 발생
    public Customer findCustomerByName(String customerName){
        return customerRepository.findCustomerByName(customerName)
                .orElseThrow(()-> new IllegalArgumentException("잘못된 구매자 이름임"));
    }

    // UPDATE , 구매자의 ID를 가지고 구매자 정보를 얻은 후 업데이트 ㄱㄱ
    @Transactional
    public String updateCustomer(CustomerDTO customerDTO){
        Customer customer = customerRepository.findById(customerDTO.getId())
                .orElseThrow(()-> new IllegalArgumentException("잘못된 구매자 이름임"));
        customer.update(Customer.builder()
                .id(customerDTO.getId())
                .name(customerDTO.getName())
                .address(customerDTO.getAddress())
                .build());
        return "수정 완료";
    }
    //DELETE
    @Transactional
    public String deleteCustomer(CustomerDTO customerDTO){
        Customer customer = findCustomerByName(customerDTO.getName());
        customerRepository.delete(customer);
        return "삭제 완료";
    }
}
