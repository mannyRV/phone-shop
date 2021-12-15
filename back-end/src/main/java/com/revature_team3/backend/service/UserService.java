package com.revature_team3.backend.service;

import com.revature_team3.backend.entity.Customer;
import com.revature_team3.backend.entity.Employee;
import com.revature_team3.backend.entity.Manager;

public interface UserService {

    void register(Customer customer);
    void register(Employee employee);
    void register(Manager manager);

}
