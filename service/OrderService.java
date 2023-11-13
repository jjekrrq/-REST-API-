package com.example.interactionapi.service;

import com.example.interactionapi.domain.Customer;
import com.example.interactionapi.domain.Buy;
import com.example.interactionapi.domain.Product;
import com.example.interactionapi.dto.OrderDTO;
import com.example.interactionapi.repository.BuyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerService customerService;
    private final ProductService productService;
    private final BuyRepository buyRepository;

    // CREATE
    @Transactional
    public String createCustomerOrder(OrderDTO orderDTO){
        if(orderDTO.getCustomerName() == null){
            Buy customerBuy = createOrderWithoutCustomer(orderDTO);
            buyRepository.save(customerBuy);
            return "저장 완료";
        }
        Buy customerBuy = createOrderWithCustomer(orderDTO);
        buyRepository.save(customerBuy);
        return "수정 완료";
    }

    @Transactional
    public String createProductOrder(OrderDTO orderDTO){
        if(orderDTO.getProductName() == null){
            Buy customerBuy = createOrderWithoutProduct(orderDTO);
            buyRepository.save(customerBuy);
            return "저장 완료";
        }
        Buy customerBuy = createOrderWithProduct(orderDTO);
        buyRepository.save(customerBuy);
        return "저장 완료";
    }
    private Buy createOrderWithoutCustomer(OrderDTO orderDTO){
        return Buy.builder()
                .build();
    }
    private Buy createOrderWithCustomer(OrderDTO orderDTO){
        Customer customer = findCustomerByName(orderDTO.getCustomerName());
        return Buy.builder()
                .customer(customer)
                .build();
    }
    private Buy createOrderWithoutProduct(OrderDTO orderDTO){
        return Buy.builder()
                .build();
    }
    private Buy createOrderWithProduct(OrderDTO orderDTO){
        Product product = findProductByName(orderDTO.getCustomerName());
        return Buy.builder()
                .product(product)
                .build();
    }
    private Customer findCustomerByName(String name){
        return customerService.findCustomerByName(name);
    }
    private Product findProductByName(String name){
        return productService.findProductByName(name);
    }

    //READ
    public OrderDTO findOrderByNameFromCustomerDto(String orderName){
        return findOrderByName(orderName).makeOrderToDto();
    }
    public OrderDTO findOrderByNameFromProductDto(String orderName){
        return findOrderByName(orderName).makeOrderToDto();
    }
    public Buy findOrderByName(String name){
        return buyRepository.findOrderByName(name)
                .orElseThrow(()->new IllegalArgumentException("잘못된 주문명 입니다."));
    }
    public Buy findOrderById(Integer id){
        return buyRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 ID"));
    }

    // UPDATE
    @Transactional
    public String updateCustomerOrder(OrderDTO orderDTO){
        Buy customerBuy = findOrderById(orderDTO.getId());
        if(orderDTO.getCustomerName() != null){
            updateOrderWithCustomer(orderDTO, customerBuy);
            buyRepository.save(customerBuy);
            return "수정 완료";
        }
        updateOrderWithoutCustomer(orderDTO, customerBuy);
        buyRepository.save(customerBuy);
        return "수정 완료";
    }
    @Transactional
    public String updateOrderProduct(OrderDTO orderDTO){
        Buy productBuy = findOrderById(orderDTO.getId());
        if(orderDTO.getProductName() != null){
            updateOrderWithProduct(orderDTO, productBuy);
            buyRepository.save(productBuy);
            return "수정 완료";
        }
        updateOrderWithoutProduct(orderDTO, productBuy);
        buyRepository.save(productBuy);
        return "수정 완료";
    }
    private void updateOrderWithCustomer(OrderDTO orderDTO, Buy buy){
        Customer customer = customerService.findCustomerByName(orderDTO.getCustomerName());
        buy.customerUpdate(Buy.builder()
                .customer(customer)
                .build());
    }
    private void updateOrderWithProduct(OrderDTO orderDTO, Buy buy){
        Product product = productService.findProductByName(orderDTO.getProductName());
        buy.productUpdate(Buy.builder()
                .product(product)
                .build());
    }
    private void updateOrderWithoutCustomer(OrderDTO orderDTO, Buy buy){
        buy.customerUpdate(Buy.builder()
                .id(orderDTO.getId())
                .build());
    }
    private void updateOrderWithoutProduct(OrderDTO orderDTO, Buy buy){
        buy.productUpdate(Buy.builder()
                .id(orderDTO.getId())
                .build());
    }

    //DELETE
    @Transactional
    public String deleteOrder(String orderName){
        Buy buy = findOrderByName(orderName);
        buyRepository.delete(buy);
        return "삭제 성공";
    }

    @Transactional
    public String deleteOrderById(OrderDTO orderDTO){
        buyRepository.deleteById(orderDTO.getId());
        return "삭제 성공";
    }
}
