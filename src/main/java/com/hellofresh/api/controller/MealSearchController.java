package com.hellofresh.api.controller;

import com.hellofresh.api.config.RouteConfig;
import com.hellofresh.api.config.TokenConfig;
import com.hellofresh.api.model.mapped.MappedRecipeItems;
import com.hellofresh.api.model.response.RecipeDescriptionModel.RecipeDescriptionModel;
import com.hellofresh.api.model.response.RecipeSuggestionModel.RecipeItemIterator;
import com.hellofresh.api.model.response.RecipeSuggestionModel.RecipeSuggestionModel;
import com.hellofresh.api.service.MealSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/recipes")
public class MealSearchController {

  @Autowired
  private RouteConfig routeConfig;

  @Autowired
  private TokenConfig tokenConfig;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private MealSearchService mealSearchService;

  @GetMapping
  public ResponseEntity<?> getRecipe(
      @RequestParam(value = "query", required = false) String query,
      @RequestParam(value = "page", defaultValue = "1", required = false) String page,
      @RequestParam(value = "take", defaultValue = "10", required = false) String take,
      @RequestParam(value = "country", defaultValue = "us", required = false) String country) {
    RecipeSuggestionModel recipeSuggestionModel = mealSearchService.getRecipeService(query, page, take, country);
    if(!recipeSuggestionModel.getItems().isEmpty()) {
      return ResponseEntity.ok().body(MappedRecipeItems.builder()
          .recipeItems(recipeSuggestionModel
              .getItems()
              .get(0)
              .getItems())
          .build());
    } else {
      List<RecipeItemIterator> listEmptyItemIterator = new ArrayList<>();
      return ResponseEntity.ok().body(MappedRecipeItems.builder()
        .recipeItems(listEmptyItemIterator)
        .build());
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getRecipeDescription(
      @PathVariable(value = "id") String id,
      @RequestParam(value = "country", defaultValue = "us", required = false) String country) {
    RecipeDescriptionModel recipeSuggestionModel = mealSearchService.getRecipeDescription(id, country);
    return ResponseEntity.ok().body(recipeSuggestionModel);
  }
}
