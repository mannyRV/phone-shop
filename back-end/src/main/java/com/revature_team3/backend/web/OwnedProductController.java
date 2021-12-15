package com.revature_team3.backend.web;

import com.revature_team3.backend.entity.Customer;
import com.revature_team3.backend.entity.OwnedProduct;
import com.revature_team3.backend.repository.CustomerRepository;
import com.revature_team3.backend.repository.OwnedProductRepository;
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

public class OwnedProductController {

    @Autowired
    private OwnedProductRepository ownedProductsRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/phone-shop/owned-products",
            produces = {"application/json", "application/xml"}
    )
    public Collection<OwnedProduct> getAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        Customer customer = customerRepository.findByEmail(email);
        return ownedProductsRepository.findAllByUserId(customer.getId());
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/phone-shop/owned-products/{productId}",
            produces = {"application/json","application/xml"}
    )
    public ResponseEntity<?> getById(@PathVariable(name = "productId") int productId) {
        Optional<OwnedProduct> ownedProduct = ownedProductsRepository.findById(productId);
        if (ownedProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(ownedProduct.get());
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/phone-shop/owned-products",
            consumes = {"application/json", "application/xml"}
    )
    public ResponseEntity<?> createOwnedProduct(@RequestBody OwnedProduct ownedProduct) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        Customer customer = customerRepository.findByEmail(email);
        ownedProduct.setCustomer(customer);
        ownedProduct = ownedProductsRepository.save(ownedProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(ownedProduct);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/phone-shop/owned-products/{productId}"
    )
    public ResponseEntity<?> updateOwnedProduct(
            @PathVariable(name = "productId") int productId,
            @RequestBody OwnedProduct ownedProduct
    ) {
        ownedProduct.setId(productId);
        ownedProduct = ownedProductsRepository.save(ownedProduct);
        return ResponseEntity.ok(ownedProduct);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/phone-shop/owned-products/{productId}"
    )
    public ResponseEntity<?> deleteOwnedProduct(@PathVariable(name = "productId") int productId) {
        ownedProductsRepository.deleteById(productId);
        return ResponseEntity.ok().build();
    }
}
