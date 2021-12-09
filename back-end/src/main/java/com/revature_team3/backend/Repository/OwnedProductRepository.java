package com.revature_team3.backend.Repository;

import com.revature_team3.backend.Entity.OwnedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnedProductRepository extends JpaRepository<OwnedProduct,Integer> {
}
