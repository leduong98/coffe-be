package com.example.coffeebe.domain.services.impl.business;

import com.example.coffeebe.app.dtos.request.DTO;
import com.example.coffeebe.app.dtos.request.FilterDto;
import com.example.coffeebe.app.dtos.request.impl.OrderDto;
import com.example.coffeebe.app.dtos.request.impl.TransactionDto;
import com.example.coffeebe.app.dtos.responses.CustomPage;
import com.example.coffeebe.app.dtos.responses.TransactionResponse;
import com.example.coffeebe.domain.entities.author.User;
import com.example.coffeebe.domain.entities.business.Discount;
import com.example.coffeebe.domain.entities.business.Order;
import com.example.coffeebe.domain.entities.business.Product;
import com.example.coffeebe.domain.entities.business.Transaction;
import com.example.coffeebe.domain.services.BaseService;
import com.example.coffeebe.domain.services.impl.BaseAbtractService;
import com.example.coffeebe.domain.utils.Constant;
import com.example.coffeebe.domain.utils.exception.CustomErrorMessage;
import com.example.coffeebe.domain.utils.exception.CustomException;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Log4j2
public class TransactionService extends BaseAbtractService implements BaseService<Transaction, Long> {

    @Override
    public CustomPage<Transaction> findAll(Pageable pageable) {
        return null;
    }

    public CustomPage<TransactionResponse> findAllByUser(Pageable pageable){
        User user = getUser();
        Page<Transaction> transactionPage = transactionRepository.findAllByUser(user.getId(), pageable);
        CustomPage<TransactionResponse> responsePage = new CustomPage<>();
        responsePage.setData(transactionPage.getContent().stream().map(ele -> modelMapper.map(ele, TransactionResponse.class)).collect(Collectors.toList()));
        responsePage.setMetadata(new CustomPage.Metadata(transactionPage));
        return responsePage;
    }

    @Override
    public Transaction findById(HttpServletRequest request, Long id) {
        return null;
    }

    @Override
    public Transaction create(HttpServletRequest request, DTO dto) {
        User user = getUser();
        TransactionDto transactionDto = modelMapper.map(dto, TransactionDto.class);
        List<Product> products = productRepository.findAllById(transactionDto.getOrders().
                parallelStream().map(OrderDto::getProductID).collect(Collectors.toList()));
        List<Long> discountIds = transactionDto.getOrders().
                parallelStream().map(OrderDto::getDiscountId).collect(Collectors.toList());
        List<Discount> discounts = discountIds.isEmpty() ? new ArrayList<>() : discountRepository.findAllById(discountIds);

        Map<Long, Product> mapProduct = new HashMap<>();
        Map<Long, Discount> mapDiscount = new HashMap<>();
        products.forEach(ele -> {
            mapProduct.put(ele.getId(), ele);
        });
        discounts.forEach(ele -> {
            mapDiscount.put(ele.getId(), ele);
        });

        Transaction transaction = new Transaction();
        transaction.setPayment(transactionDto.getPayment());
        transaction.setUserEmail(transactionDto.getEmail());
        transaction.setUserPhone(transactionDto.getPhone());
        transaction.setAddress(transactionDto.getAddress());
        transaction.setPaymentInfo(transactionDto.getPaymentInfo());
        transaction.setPayment(Constant.OFFLINE);
        transaction.setUser(user);
        List<Order> orders = new ArrayList<>();
        transactionDto.getOrders().forEach(ele -> {
            Order order = new Order();
            Product product = (mapProduct.get(ele.getProductID()));
            if (ele.getQuantity() > product.getQuantity()) {
                throw new CustomException(HttpStatus.BAD_REQUEST, CustomErrorMessage.INVENTORY_NOT_ENOUGH);
            }
            order.setProduct(product);
            order.setQuantity(ele.getQuantity());
            product.setQuantity(product.getQuantity() - ele.getQuantity());
            long amount;
            if (ele.getDiscountId() != null) {
                Discount discount = mapDiscount.get(ele.getDiscountId());
                if (discount.getProduct().getId().longValue() != ele.getProductID().longValue()) {
                    throw new CustomException(HttpStatus.BAD_REQUEST, CustomErrorMessage.PRODUCT_INVALID);
                }
                Long now = (new Date()).getTime();
                if (discount.getStartDate().getTime() <= now && discount.getEndDate().getTime() >= now) {
                    amount = product.getPrice() * ele.getQuantity() * (100 - discount.getDiscount()/100);
                    order.setAmount(amount);
                } else {
                    throw new CustomException(HttpStatus.BAD_REQUEST, CustomErrorMessage.DISCOUNT_EXPIRED_TIME);
                }
            } else {
                amount = product.getPrice() * ele.getQuantity();
            }
            order.setAmount(amount);
            orders.add(order);
        });
        transaction.setOrderSelf(orders);
        productRepository.saveAll(mapProduct.values());
        transaction = transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public Transaction update(HttpServletRequest request, Long id, DTO dto) {
        return null;
    }

    @Override
    public boolean delete(HttpServletRequest request, Long id) {
        return false;
    }

    @Override
    public Page<Transaction> filter(FilterDto<Transaction> dto, Pageable pageable) {
        return null;
    }

    @Override
    public List<Transaction> filter(HttpServletRequest request) {
        return null;
    }


}