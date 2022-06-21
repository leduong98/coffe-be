package com.example.coffeebe.domain.entities.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "detail")
    private String detail;

    @Column(name = "price")
    private Double price;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "status")
    private String status;

    @Column(name = "image_list")
    private String imageList;

    @Column(name = "view")
    private Integer view;

    @Column(name = "review")
    private Float review;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "inventory")
    private Integer inventory;

}
