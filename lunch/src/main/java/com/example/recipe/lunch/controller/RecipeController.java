package com.example.recipe.lunch.controller;

import com.example.recipe.lunch.dao.Recipe;
import com.example.recipe.lunch.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping("/save")
    public Recipe saveRecipe(@RequestBody Recipe recipe) {
        Recipe result = null;
        try {
            result = recipeService.saveRecipe(recipe);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @GetMapping("/getall")
    public List<Recipe> getAllRecipe() {
        List<Recipe> recipeList = new ArrayList<>();
        try {
            recipeList = recipeService.getAllRecipes();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return recipeList;
    }
}
