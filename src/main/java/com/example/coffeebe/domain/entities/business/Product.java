package com.example.coffeebe.domain.entities.business;

import com.example.coffeebe.domain.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    private String price;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "status")
    private Status status;

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
