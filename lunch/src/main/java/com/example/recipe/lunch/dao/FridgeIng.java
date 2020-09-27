package com.example.recipe.lunch.dao;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


@Entity
public class FridgeIng {

    @Id
    @GeneratedValue(generator = "fridgeIngId")
    @Column
    @GenericGenerator(
            name = "fridgeIngId",
            strategy = "com.example.recipe.lunch.identifier.FridgeIngIdGenerator"
    )
    private String fridgeIngId;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Ingredient ingredient;

    @Column
    @Temporal(TemporalType.DATE)
    private Date bestBefore;

    @Column
    @Temporal(TemporalType.DATE)
    private Date useBy;



    public String getFridgeIngId() {
        return fridgeIngId;
    }

    public void setFridgeIngId(String fridgeIngId) {
        this.fridgeIngId = fridgeIngId;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Date getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(Date bestBefore) {
        this.bestBefore = bestBefore;
    }

    public Date getUseBy() {
        return useBy;
    }

    public void setUseBy(Date useBy) {
        this.useBy = useBy;
    }
}
