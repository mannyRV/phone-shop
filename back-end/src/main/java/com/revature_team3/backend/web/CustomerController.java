package com.revature_team3.backend.web;

import com.revature_team3.backend.entity.Customer;
import com.revature_team3.backend.repository.CustomerRepository;
import com.revature_team3.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserService customerService;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/phone-shop/customers",
            produces = {"application/json","application/xml"}
    )
    public List getAllCustomers() {
        return customerRepository.findAll();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/phone-shop/customers/{customerId}",
            produces = {"application/json","application/xml"}
    )
    public ResponseEntity<?> getById(@PathVariable(name = "customerId") int customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        else {
            return ResponseEntity.ok(optionalCustomer.get());
        }
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/phone-shop/customers",
            consumes = {"application/json","application/xml"}
    )
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        customerService.register(customer);
        return ResponseEntity.status(201).body(customer);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/phone-shop/customers/{customerId}"
    )
    public ResponseEntity<?> updateCustomer(
            @PathVariable(name="customerId") int customerId,
            @RequestBody Customer customer
    ) {
        customer.setId(customerId);
        customer= (Customer) customerRepository.save(customer);
        return ResponseEntity.ok(customer);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/phone-shop/customer/{customerId}"
    )
    public ResponseEntity<?> deleteCustomer(@PathVariable(name="customerId") int customerId) {
        customerRepository.deleteById(customerId);
        return ResponseEntity.ok().build();
    }
}
