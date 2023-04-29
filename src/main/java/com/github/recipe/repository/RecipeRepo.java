package com.github.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.github.recipe.model.Recipe;

import java.util.List;

public interface RecipeRepo extends JpaRepository<Recipe, Long> {

    List<Recipe> findByNameContainingIgnoreCase(String name);
}

