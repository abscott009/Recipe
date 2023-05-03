package com.github.recipe.controller;

import com.github.recipe.RecipeApplication;
import com.github.recipe.repository.RecipeRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = RecipeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")

class RecipeTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RecipeRepo recipeRepo;

    @Test
    public void testGetRecipeByIdSuccessBehavior() throws Exception {
        final long recipeId = 1;

        //set up GET request
        this.mockMvc.perform(get("/recipes/" + recipeId))

                //print response
                .andDo(print())
                //expect status 200 OK
                .andExpect(status().isOk())
                //expect return Content-Type header as application/json
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                //confirm returned JSON values
                .andExpect(jsonPath("id").value(recipeId))
                .andExpect(jsonPath("minutesToMake").value(2))
                .andExpect(jsonPath("reviews", hasSize(1)))
                .andExpect(jsonPath("ingredients", hasSize(1)))
                .andExpect(jsonPath("steps", hasSize(2)));
//                .andExpect(jsonPath("username").value("bob"));
    }

    @Test
    public void testGetRecipeByIdFailureBehavior() throws Exception {
        final long recipeId = 5000;

        //set up guaranteed to fail in testing environment request
        mockMvc.perform(get("/recipes/" + recipeId))

                //print response
                .andDo(print())
                //expect status 404 NOT FOUND
                .andExpect(status().isNotFound())
                //confirm that HTTP body contains correct error message
                .andExpect(content().string(containsString("No recipe with ID " + recipeId + " could be found.")));
    }

}