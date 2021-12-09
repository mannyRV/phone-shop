package com.revature_team3.backend.Web;

import com.revature_team3.backend.Entity.ShopProduct;
import com.revature_team3.backend.Repository.ShopProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
public class ShopProductController {

    @Autowired
    private ShopProductRepository shopProductRepository;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/phone-shop/products",
            produces = {"application/json","application/xml"}
    )
    public Collection<ShopProduct> getAllShopProducts() {
        return shopProductRepository.findAll();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/phone-shop/products/{productId}",
            produces = {"application/json","application/xml"}
    )
    public ResponseEntity<?> getById(@PathVariable(name = "productId") int productId) {
        Optional<ShopProduct> optionalProduct = shopProductRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        else {
            return ResponseEntity.ok(optionalProduct.get());
        }
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/phone-shop/products",
            consumes = {"application/json","application/xml"}
    )
    public ResponseEntity<?> createProduct(@RequestBody ShopProduct product) {
        product = shopProductRepository.save(product);
        return ResponseEntity.status(201).body(product);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/phone-shop/products/{productId}"
    )
    public ResponseEntity<?> updateProduct(
            @PathVariable(name="productId") int productId,
            @RequestBody ShopProduct shopProduct
    ) {
        shopProduct.setId(productId);
        shopProduct=shopProductRepository.save(shopProduct);
        return ResponseEntity.ok(shopProduct);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/phone-shop/products/{productId}"
    )
    public ResponseEntity<?> deleteProduct(@PathVariable(name="productId") int productId) {
        shopProductRepository.deleteById(productId);
        return ResponseEntity.ok().build();
    }
}
