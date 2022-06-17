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
@Table(name = "Order")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_Id")
    private Transaction transaction;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Product> product;

    @JoinColumn(name = "quantity")
    private Integer quantity;

    @JoinColumn(name = "amount")
    private Integer amount;

    @JoinColumn(name = "data")
    private String data;

    @JoinColumn(name = "status")
    private Status status;

}
