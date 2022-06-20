package com.example.coffeebe.domain.entities.business;

import com.example.coffeebe.domain.entities.BaseEntity;
import com.example.coffeebe.domain.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "product_order")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "data")
    private String data;

    @Column(name = "status")
    private Status status;

}
