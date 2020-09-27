package com.example.recipe.lunch.service;

import com.example.recipe.lunch.dao.FridgeIng;
import com.example.recipe.lunch.dao.Ingredient;
import com.example.recipe.lunch.dao.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.recipe.lunch.repository.FridgeIngRepo;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class FridgeIngService {

    @Autowired
    private FridgeIngRepo fridgeIngRepo;

    public FridgeIng saveFridgeIngredient(FridgeIng fridgeIng) {
        return fridgeIngRepo.save(fridgeIng);
    }

    /**
     * @param fridgeIngs
     * @return
     */
    @Transactional
    public List<FridgeIng> saveFridgeIngredients(List<FridgeIng> fridgeIngs) {
        return fridgeIngRepo.saveAll(fridgeIngs);
    }

    /**
     * Return available Ingredients in the fridge that ara best before date less then or equal to selected date
     *
     * @param date
     * @return
     */
    public List<FridgeIng> getBestBeforeIngredients(Date date) {
        return fridgeIngRepo.findByBestBeforeGreaterThanEqual(date);
    }

    /**
     * Return available Ingredient in the fridge that ara best before date less then or equal to selected date
     *
     * @param date
     * @return
     */
    public List<FridgeIng> getUseByIngredient(Date date) {
        return fridgeIngRepo.findByBestBeforeLessThanAndUseByGreaterThanEqual(date, date);
    }

    /**
     * Get All ingredient that are stored in the fridge
     *
     * @return
     */
    public List<FridgeIng> getAllFridgeIngredient() {
        return fridgeIngRepo.findAll();
    }

    /**
     * Check withe the available Ingredent in the fridge withe the ingredient which are needed in recipe
     * @param ingredients
     * @param availableToUseIng
     * @param date
     * @return
     */
    public boolean isEveryIngredientAvailableAndCanUse(List<Ingredient> ingredients, List<FridgeIng> availableToUseIng, Date date) {
        boolean isAvailable = false;
        System.out.println("Ingredient");
        for (Ingredient ingredient : ingredients) {
            System.out.println(ingredient.getTitle());
            isAvailable = isIngredientAvailableInFridge(availableToUseIng, ingredient);
            if (!isAvailable) {
                break;
            }
        }
        System.out.println("Availability :" + isAvailable);
        return isAvailable;
    }

    /**
     * Check specific ingredient is expired or not
     * @param availableToUseIng
     * @param ingredient
     * @return
     */
    public boolean isIngredientAvailableInFridge(List<FridgeIng> availableToUseIng, Ingredient ingredient) {
        boolean isAvailable = false;
        for (FridgeIng fridgeIng : availableToUseIng) {
            if (fridgeIng.getIngredient().equals(ingredient)) {
                isAvailable = true;
                break;
//                System.out.println("Available :" + ingredient.getTitle());
            }
        }
        return isAvailable;
    }

}
