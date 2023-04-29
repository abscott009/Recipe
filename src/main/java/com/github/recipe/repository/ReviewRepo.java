package com.github.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.github.recipe.model.Review;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Long> {

    List<Review> findByUsername(String username);
}
