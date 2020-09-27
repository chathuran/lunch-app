package com.example.recipe.lunch.controller;

import com.example.recipe.lunch.dao.FridgeIng;
import com.example.recipe.lunch.dao.Ingredient;
import com.example.recipe.lunch.service.FridgeIngService;
import com.example.recipe.lunch.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;


    @PostMapping("/save")
    public Ingredient saveIngredient(@RequestBody Ingredient ingredient) {
        Ingredient result = null;
        try {
            result = ingredientService.saveIngredient(ingredient);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @GetMapping("/get")
    public List<Ingredient> getAllIngredient() {
        List<Ingredient> ingredientList = new ArrayList<>();
        try {

            ingredientList = ingredientService.getAllIngredient();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ingredientList;
    }
}
