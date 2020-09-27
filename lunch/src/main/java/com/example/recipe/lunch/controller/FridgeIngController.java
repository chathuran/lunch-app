package com.example.recipe.lunch.controller;

import com.example.recipe.lunch.dao.FridgeIng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.recipe.lunch.service.FridgeIngService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fridge-ing")
public class FridgeIngController {
    @Autowired
    private FridgeIngService fridgeIngService;


    @PostMapping("/save")
    public FridgeIng saveIngredient(@RequestBody FridgeIng fridgeIng) {
        FridgeIng result = null;
        try {
            result = fridgeIngService.saveFridgeIngredient(fridgeIng);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


    @GetMapping("/get")
    public List<FridgeIng> getAllIngredient() {
        List<FridgeIng> fridgeIngList = new ArrayList<>();
        try {

            fridgeIngList = fridgeIngService.getAllFridgeIngredient();
        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();

        }
        return fridgeIngList;
    }
}
