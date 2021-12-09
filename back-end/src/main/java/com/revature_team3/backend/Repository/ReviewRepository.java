package com.revature_team3.backend.Repository;

import com.revature_team3.backend.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
}
