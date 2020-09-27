package com.example.recipe.lunch.controller;


import com.example.recipe.lunch.dao.Recipe;
import com.example.recipe.lunch.service.FridgeIngService;
import com.example.recipe.lunch.service.LunchService;
import com.example.recipe.lunch.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class LunchController {

    @Autowired
    private LunchService lunchService;


    @GetMapping("/lunch")
    public List<Recipe> getAvailableLunchOptionsToday(){
        Date date =new Date();
        List<Recipe> recipeList = new ArrayList<>();
        try {
//           date= new SimpleDateFormat( "yyyy-MM-dd" ).parse( "2020-09-22" );
            recipeList = lunchService.getAvailableLunchOptionsToday(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipeList;
    }
}
