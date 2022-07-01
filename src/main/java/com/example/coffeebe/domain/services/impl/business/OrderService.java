package com.example.coffeebe.domain.services.impl.business;

import com.example.coffeebe.app.dtos.request.DTO;
import com.example.coffeebe.app.dtos.request.FilterDto;
import com.example.coffeebe.app.dtos.request.impl.OrderDto;
import com.example.coffeebe.domain.entities.business.Order;
import com.example.coffeebe.domain.services.BaseService;
import com.example.coffeebe.domain.services.impl.BaseAbtractService;
import com.example.coffeebe.domain.utils.exception.CustomErrorMessage;
import com.example.coffeebe.domain.utils.exception.CustomException;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Log4j2
public class OrderService extends BaseAbtractService implements BaseService<Order, Long> {
    @Override
    public Page<Order> findAll() throws Exception {
        Page<Order> orders = orderRepository.findAll(pageable);
        if (orders.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, CustomErrorMessage.ORDER_NOT_FOUND);
        }
        return null;
    }

    @Override
    public Order findById(HttpServletRequest request, Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, CustomErrorMessage.ORDER_NOT_FOUND)
        );

        return order;
    }

    @Override
    public Order create(HttpServletRequest request, DTO dto) {
        OrderDto orderDto = modelMapper.map(dto, OrderDto.class);
        Order order = Order.builder()
                .quantity(orderDto.getQuantity())
//                .amount(orderDto.getAmount())
//                .data(orderDto.getData())
//                .status(orderDto.getStatus())
//                .product(getProductById(orderDto.getProductID()))
//                .transaction(getTransactionById(orderDto.getTransactionId()))
                .build();

        return orderRepository.save(order);
    }

    @Override
    public Order update(HttpServletRequest request, Long id, DTO dto) {
        Order order = findById(request, id);
        OrderDto orderDto = modelMapper.map(dto, OrderDto.class);
        order.setQuantity(orderDto.getQuantity());
//        order.setAmount(orderDto.getAmount());
//        order.setData(orderDto.getData());
//        order.setStatus(orderDto.getStatus());
        order.setProduct(getProductById(orderDto.getProductID()));
     //   order.setTransaction(getTransactionById(orderDto.getTransactionId()));

        return orderRepository.save(order);
    }

    @Override
    public boolean delete(HttpServletRequest request, Long id) {
        Order order = findById(request, id);
        orderRepository.delete(order);
        return true;
    }

    @Override
    public Page<Order> filter(FilterDto<Order> dto, Pageable pageable) {
        return null;
    }

    @Override
    public List<Order> filter(HttpServletRequest request) {
        return null;
    }
}
