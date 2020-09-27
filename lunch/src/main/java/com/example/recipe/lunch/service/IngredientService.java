package com.example.recipe.lunch.service;


import com.example.recipe.lunch.dao.Ingredient;
import com.example.recipe.lunch.repository.IngredientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepo ingredientRepo;

    /**
     * Save ingredient
     * @param fridgeIng
     * @return
     */
    public Ingredient saveIngredient(Ingredient fridgeIng){
        return ingredientRepo.save(fridgeIng);
    }


    /**
     * Reduren All ingredient
     * @return
     */
    public List<Ingredient> getAllIngredient(){
        return ingredientRepo.findAll();
    }
}
