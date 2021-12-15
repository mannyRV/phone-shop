package com.revature_team3.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="employees")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String name;
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "emp_roles",foreignKey = @ForeignKey(name="employee_id"))
    private List<String> authorities;
}
