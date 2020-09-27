package com.example.recipe.lunch.service;

import com.example.recipe.lunch.dao.Recipe;
import com.example.recipe.lunch.repository.FridgeIngRepo;
import com.example.recipe.lunch.repository.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LunchService {

    @Autowired
    private RecipeService recipeService;

    /**
     * Available Lunch option for today based on Ingredient availabe in the fridge and that available ingredients' expiration dates
     * @param date
     * @return
     */
    public List<Recipe> getAvailableLunchOptionsToday(Date date) {


        List<Recipe> availableRecipesToday = recipeService.findAllIngredientAvailableRecipes(date);
        return availableRecipesToday;
    }
}
