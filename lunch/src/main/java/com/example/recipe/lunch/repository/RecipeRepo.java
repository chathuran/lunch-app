package com.example.recipe.lunch.repository;

import com.example.recipe.lunch.dao.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepo extends JpaRepository<Recipe,String> {
}
