package com.example.coffeebe.app.controllers.business;

import com.example.coffeebe.app.controllers.BaseController;
import com.example.coffeebe.app.dtos.request.impl.CategoryFilterDto;
import com.example.coffeebe.app.dtos.request.impl.TransactionFilterDto;
import com.example.coffeebe.app.dtos.responses.CategoryResponse;
import com.example.coffeebe.app.dtos.responses.TransactionResponse;
import com.example.coffeebe.domain.entities.business.Category;
import com.example.coffeebe.domain.entities.business.Transaction;
import com.example.coffeebe.domain.services.impl.business.CategoryService;
import com.example.coffeebe.domain.services.impl.business.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/transaction")
public class TransactionController extends BaseController<Transaction, Long, TransactionResponse, TransactionFilterDto> {

	@Autowired
    TransactionService transactionService;

    public TransactionController() {
        super(TransactionResponse.class, TransactionFilterDto.class);
    }

}
