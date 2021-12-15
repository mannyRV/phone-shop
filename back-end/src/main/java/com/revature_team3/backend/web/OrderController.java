package com.revature_team3.backend.web;

import com.revature_team3.backend.entity.Customer;
import com.revature_team3.backend.entity.Order;
import com.revature_team3.backend.repository.CustomerRepository;
import com.revature_team3.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;
import java.util.Optional;

public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/phone-shop/orders",
            produces = {"application/json", "application/xml"}
    )
    public Collection<Order> getAll() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        Customer customer = customerRepository.findByEmail(email);

        Collection<Order> orders = orderRepository.findAllByUserId(customer.getId());
        return orders;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/phone-shop/orders/{orderId}",
            produces = {"application/json","application/xml"}
    )
    public ResponseEntity<?> getById(@PathVariable(name = "orderId") int orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(order.get());

    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/phone-shop/orders",
            consumes = {"application/json", "application/xml"}
    )
    public ResponseEntity<?> createOrder(@RequestBody Order order) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        Customer customer = customerRepository.findByEmail(email);
        order.setCustomer(customer);

        order = orderRepository.save(order);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/phone-shop/orders/{orderId}"
    )
    public ResponseEntity<?> updateOrder(
            @PathVariable(name = "orderId") int orderId,
            @RequestBody Order order
    ) {
        order.setId(orderId);
        order = orderRepository.save(order);
        return ResponseEntity.ok(order);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/phone-shop/orders/{orderId}"
    )
    public ResponseEntity<?> deleteOrder(@PathVariable(name = "orderId") int orderId) {
        orderRepository.deleteById(orderId);
        return ResponseEntity.ok().build();
    }

}
