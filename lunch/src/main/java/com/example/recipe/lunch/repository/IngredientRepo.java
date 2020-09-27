package com.example.recipe.lunch.repository;

import com.example.recipe.lunch.dao.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepo extends JpaRepository<Ingredient,String> {
}
