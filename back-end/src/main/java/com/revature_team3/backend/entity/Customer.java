package com.revature_team3.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="customers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;
    private double balance;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "cust_roles",foreignKey = @ForeignKey(name="customer_id"))
    private List<String> authorities;
}
