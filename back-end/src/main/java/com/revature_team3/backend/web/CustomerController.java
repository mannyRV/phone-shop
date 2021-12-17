package com.revature_team3.backend.web;

import com.revature_team3.backend.entity.Customer;
import com.revature_team3.backend.repository.CustomerRepository;
import com.revature_team3.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"*"})
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserService customerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
            value = "/phone-shop/customers/{customerEmail}",
            produces = {"application/json","application/xml"}
    )
    public ResponseEntity<?> getByEmail(@PathVariable(name = "customerEmail") String customerEmail) {
        Customer customer = customerRepository.findByEmail(customerEmail);
        return ResponseEntity.ok(customer);
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


    //NOT NEEDED IN FRONT-END
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/phone-shop/customers/{customerId}"
    )
    public ResponseEntity<?> updateCustomer(
            @PathVariable(name="customerId") int customerId,
            @RequestBody Customer customer
    ) {
        Optional<Customer> oldCustomer = customerRepository.findById(customerId);
        if (oldCustomer.isEmpty()) {
            throw new RuntimeException("User cannot be found");
        } else {
            Customer newCustomer = oldCustomer.get();
            String bcryptHashString = passwordEncoder.encode(customer.getPassword());
            newCustomer.setPassword(bcryptHashString);
            newCustomer.setBalance(customer.getBalance());
            newCustomer.setEmail(customer.getEmail());
            newCustomer.setName(customer.getName());
            newCustomer.setAuthorities(customer.getAuthorities());
            newCustomer = customerRepository.save(newCustomer);
            return ResponseEntity.ok(newCustomer);
        }
    }


    //NOT NEEDED IN FRONT-END
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/phone-shop/customers/{customerId}"
    )
    public ResponseEntity<?> deleteCustomer(@PathVariable(name="customerId") int customerId) {
        customerRepository.deleteById(customerId);
        return ResponseEntity.ok().build();
    }
}
