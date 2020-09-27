package com.example.recipe.lunch.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(generator = "ingredientId")
    @Column
    @GenericGenerator(
            name = "ingredientId",
            strategy = "com.example.recipe.lunch.identifier.IngredientIdGenerator"
    )
    private String ingredientId;
    @Column
    private String title;

    public String getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(String ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
