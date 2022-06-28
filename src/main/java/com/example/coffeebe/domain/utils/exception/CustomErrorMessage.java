package com.example.coffeebe.domain.utils.exception;


public enum CustomErrorMessage {
    PRODUCT_NOT_FOUND("Product not found"),
    CATEGORY_NOT_FOUND("Category not found"),
    SLIDER_NOT_FOUND("Slider not found"),
    DISCOUNT_NOT_FOUND("Discount not found"),
    ORDER_NOT_FOUND("Oder not found"),
    TRANSACTION_NOT_FOUND("Transaction not found");


    public final String val;

    private CustomErrorMessage(String label) {
        val = label;
    }
}
