package com.revature_team3.backend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="ownedproducts")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OwnedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String image_path;
    @ManyToOne
    @JoinColumn(name="customer_id")
    @JsonIgnore
    private Customer customer;
    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;
}
