package com.example.coffeebe.app.controllers.business;

import com.example.coffeebe.app.controllers.BaseController;
import com.example.coffeebe.app.dtos.request.impl.TransactionFilterDto;
import com.example.coffeebe.app.dtos.responses.CustomPage;
import com.example.coffeebe.app.dtos.responses.TransactionResponse;
import com.example.coffeebe.domain.entities.business.Transaction;
import com.example.coffeebe.domain.services.impl.business.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/order")
public class TransactionController extends BaseController<Transaction, Long, TransactionResponse, TransactionFilterDto> {

	@Autowired
    TransactionService transactionService;

    public TransactionController() {
        super(TransactionResponse.class, TransactionFilterDto.class);
    }

    @GetMapping("/user")
    CustomPage<TransactionResponse> getPageTransactionByUser(HttpServletRequest request, Pageable pageable){
        return transactionService.findAllByUser(pageable);
    }

}
