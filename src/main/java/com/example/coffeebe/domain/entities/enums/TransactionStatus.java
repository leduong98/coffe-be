package com.example.coffeebe.domain.entities.enums;

public enum TransactionStatus {
    WAIT_FOR_APPROVE("Chờ xác nhận"),
    APPROVED("ĐÃ xác nhận"),
    TRANSPORT("Đang giao hàng"),
    SUCCESSFUL_TRANSPORT("Giao hàng thành công"),
    RECEIVED( "Đã nhận hàng"),
    CANCEL("Đã hủy")
    ;

    public final String val;

    private TransactionStatus(String label) {
        val = label;
    }
}