package com.example.coffeebe.domain.entities.business;

import com.example.coffeebe.domain.entities.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@SuperBuilder
@Table(name = "product")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "detail")
    private String detail;

    @Column(name = "price")
    private Long price;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnore
    private Category category;

    @Column(name = "status")
    private String status;

    @Column(name = "image_list")
    private String imageList;

    @Column(name = "view")
    private Integer view;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "inventory")
    private Integer inventory;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Discount> discounts;

    @Column(name = "description", columnDefinition = "text")
    private String description;

}
