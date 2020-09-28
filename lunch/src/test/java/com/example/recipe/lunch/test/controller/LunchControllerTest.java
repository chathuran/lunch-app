package com.example.recipe.lunch.test.controller;

import com.example.recipe.lunch.controller.LunchController;
import com.example.recipe.lunch.dao.FridgeIng;
import com.example.recipe.lunch.dao.Ingredient;
import com.example.recipe.lunch.dao.Recipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = LunchController.class)
@WithMockUser
public class LunchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LunchController lunchController;

    @Spy
    private List<Recipe> recipes = new ArrayList<>();

    @Test
    public void TestGetAvailableLunchOptionsToday() throws Exception{

        Ingredient mockIngredient = new Ingredient();
        List<Ingredient> mockIngredientList = new ArrayList<>();
        List<Recipe> mockRecipeList = new ArrayList<>();

        mockIngredientList.add(mockIngredient);
        mockIngredientList.add(mockIngredient);
        mockIngredient.setIngredientId("Ing001");
        mockIngredient.setTitle("Ham");
        Recipe mockRecipe = new Recipe();

        mockRecipe.setRecipeId("Res101");
        mockRecipe.setTitle("Test Recipe");
        mockRecipe.setIngredients(mockIngredientList);

        mockRecipeList.add(mockRecipe);
        mockRecipeList.add(mockRecipe);

        Mockito.when(lunchController.getAvailableLunchOptionsToday()).thenReturn(mockRecipeList);


        String URI = "/lunch";

//        RequestBuilder requestBuilder =MockMvcRequestBuilders.get(URI)
//                .accept(MediaType.APPLICATION_JSON);
//        Mockito.when(lunchController.getAvailableLunchOptionsToday()).thenReturn(recipes);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse response = result.getResponse();
    String expectedJson = this.mapToJson(mockRecipeList);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);
//        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }
    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
