package com.example.recipe.lunch.test.controller;

import com.example.recipe.lunch.controller.FridgeIngController;
import com.example.recipe.lunch.dao.FridgeIng;
import com.example.recipe.lunch.dao.Ingredient;
import com.example.recipe.lunch.service.FridgeIngService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FridgeIngController.class)
@WithMockUser
public class FridgeIngControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FridgeIngService fridgeIngService;
    @Test
    public void testCreateFridgeIng() throws Exception{
        Date bestBeforeDate=new Date();
        Date useByDate=new Date();

        Ingredient mockIngredient = new Ingredient();
        mockIngredient.setIngredientId("Ing001");
        mockIngredient.setTitle("Ham");


        FridgeIng mockFridgeIng = new FridgeIng();
        mockFridgeIng.setFridgeIngId("Fri001");
//        mockFridgeIng.setBestBefore(bestBeforeDate);
//        mockFridgeIng.setUseBy(useByDate);
        mockFridgeIng.setIngredient(mockIngredient);

        String inputJson = this.mapToJson(mockFridgeIng);

        String URI = "/fridge-ing/save";

        Mockito.when(fridgeIngService.saveFridgeIngredient(Mockito.any(FridgeIng.class))).thenReturn(mockFridgeIng);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();
        assertThat(outputInJson).isEqualTo(inputJson);
        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }
    private String mapToJson(Object object) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
