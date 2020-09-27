package com.example.recipe.lunch.service;

import com.example.recipe.lunch.dao.FridgeIng;
import com.example.recipe.lunch.dao.Recipe;
import com.example.recipe.lunch.repository.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.NewThreadAction;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepo recipeRepo;

    @Autowired
    private FridgeIngService fridgeIngService;

    /**
     * Save recipe in to the database
     * @param recipe
     * @return
     */
    public Recipe saveRecipe(Recipe recipe){
        return recipeRepo.saveAndFlush(recipe);
    }

    /**
     * Retrieve Al recipes from database
     * @return
     */
    public List<Recipe> getAllRecipes(){
        return recipeRepo.findAll();
    }

    /**
     * Find ingredients that are available in the fridge and before expiration date
     * @param date
     * @return
     */
    public List<Recipe> findAllIngredientAvailableRecipes(Date date){
        HashMap<String,Recipe> hash_recipe =new HashMap<>();
        List<Recipe> allRecipes = getAllRecipes();
        List<Recipe> availableRecipesToday = new ArrayList<>();
        List<FridgeIng> bestBeforeIng= fridgeIngService.getBestBeforeIngredients(date);
        List<FridgeIng> useByBeforeIng= fridgeIngService.getUseByIngredient(date);
        List<FridgeIng> totalUsefulIng=bestBeforeIng ;

        for (FridgeIng fridgeIng:useByBeforeIng) {
            totalUsefulIng.add(fridgeIng);
        }

//      Retrieve recipes that can made from available ingredient on or before Best Before date
        for (Recipe recipe:allRecipes) {

            if(fridgeIngService.isEveryIngredientAvailableAndCanUse(recipe.getIngredients(),bestBeforeIng,date)){
                hash_recipe.put(recipe.getRecipeId(),recipe);
            }
        }
        //      Retrieve recipes that can made from available ingredient on or before Use by date  date
        for (Recipe recipe:allRecipes) {
            if(fridgeIngService.isEveryIngredientAvailableAndCanUse(recipe.getIngredients(),totalUsefulIng,date)){
                hash_recipe.put(recipe.getRecipeId(),recipe);
            }
        }
        hash_recipe.forEach((s, recipe) -> availableRecipesToday.add(recipe));
        return availableRecipesToday;
    }

}
