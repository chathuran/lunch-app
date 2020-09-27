package com.example.recipe.lunch.repository;

import com.example.recipe.lunch.dao.FridgeIng;
import com.example.recipe.lunch.dao.Ingredient;
import com.example.recipe.lunch.dao.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface FridgeIngRepo extends JpaRepository<FridgeIng,String> {

    FridgeIng findByIngredient(Ingredient ingredient);

    /**
     * Return ingredient in fridge before Best Before date
     * @param bestBefore
     * @return
     */
    List<FridgeIng> findByBestBeforeGreaterThanEqual(Date bestBefore);

    /**
     * Return Ingredient in fridge with in Best Before date and Use by date
     * @param bestBefore
     * @param useBy
     * @return
     */
    List<FridgeIng> findByBestBeforeLessThanAndUseByGreaterThanEqual(Date bestBefore,Date useBy);
}
