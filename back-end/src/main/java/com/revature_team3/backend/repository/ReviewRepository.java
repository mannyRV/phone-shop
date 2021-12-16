package com.revature_team3.backend.repository;

import com.revature_team3.backend.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {

    @Query("from Review r where r.shopProduct=:product_id")
    List<Review> getAllByProductId(int product_id);


}
