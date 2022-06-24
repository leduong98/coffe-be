package com.example.coffeebe.domain.utils.exception;


public enum CustomErrorMessage {
    PRODUCT_NOT_FOUND("Product not found"),
    CATEGORY_NOT_FOUND("Categoty not found");

    public final String val;

    private CustomErrorMessage(String label) {
        val = label;
    }
}
