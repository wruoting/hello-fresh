package com.hellofresh.api.service;

import com.hellofresh.api.config.RouteConfig;
import com.hellofresh.api.config.TokenConfig;
import com.hellofresh.api.model.response.RecipeDescriptionModel.Ingredient;
import com.hellofresh.api.model.response.RecipeDescriptionModel.RecipeDescriptionModel;
import com.hellofresh.api.model.response.RecipeSuggestionModel.RecipeSuggestionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Component
public class MealSearchService {
  @Autowired
  private RouteConfig routeConfig;

  @Autowired
  private TokenConfig tokenConfig;

  @Autowired
  private RestTemplate restTemplate;

  private String QUERY_PARAM = "q";
  private String PAGE_PARAM = "page";
  private String TAKE_PARAM = "take";
  private String COUNTRY_PARAM = "country";

  public RecipeSuggestionModel getRecipeService (String query, String page, String take, String country){
    String helloFreshRoute = routeConfig.getHelloFreshEndpoint();
    ResponseEntity<RecipeSuggestionModel> response;
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + tokenConfig.getBearerToken());
      HttpEntity entity = new HttpEntity<String>(null, headers);

      UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(helloFreshRoute + "/api/recipes/search/suggestions")
          .queryParam(QUERY_PARAM, query)
          .queryParam(PAGE_PARAM, page)
          .queryParam(TAKE_PARAM, take)
          .queryParam(COUNTRY_PARAM, country);

      response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
          new ParameterizedTypeReference<RecipeSuggestionModel>() {

          });
    } catch (HttpClientErrorException e) {
      System.out.println("Unexpected Response from getRecipe: " + e);
      throw e;
    } catch (Exception e) {
      System.out.println("Something went wrong with this request: " + e);
      throw e;
    }
    return response.getBody();
  }

  public RecipeDescriptionModel getRecipeDescription (String id, String country) {
    String helloFreshRoute = routeConfig.getHelloFreshEndpoint();
    RecipeDescriptionModel responseBodyMapped;
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + tokenConfig.getBearerToken());
      HttpEntity entity = new HttpEntity<String>(null, headers);

      UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(helloFreshRoute + "/api/recipes/" + id)
          .queryParam(COUNTRY_PARAM, country);

      ResponseEntity<RecipeDescriptionModel> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
          new ParameterizedTypeReference<RecipeDescriptionModel>() {});

      RecipeDescriptionModel responseBody = response.getBody();
      responseBodyMapped = mapRecipeDecision(responseBody);

    } catch (HttpClientErrorException e) {
      System.out.println("Unexpected Response from getRecipeDescription: " + e);
      throw e;
    } catch (Exception e) {
      System.out.println("Something went wrong with this request: " + e);
      throw e;
    }
    return responseBodyMapped;
  }

  private RecipeDescriptionModel mapRecipeDecision(RecipeDescriptionModel recipeDescriptionModel) {
    List<Ingredient> recipeIngredientMode = new ArrayList<>();

    for (Ingredient ingredient: recipeDescriptionModel.getIngredients()) {
      recipeIngredientMode.add(
          Ingredient
              .builder()
              .id(ingredient.getId())
              .name(ingredient.getName())
              .description(ingredient.getDescription())
              .imageLink(appendToPath(ingredient.getImagePath()))
              .allergens(ingredient.getAllergens())
              .build()
      );
    }
    RecipeDescriptionModel newRecipeDescriptionModel = RecipeDescriptionModel
        .builder()
        .country(recipeDescriptionModel.getCountry())
        .id(recipeDescriptionModel.getId())
        .name(recipeDescriptionModel.getName())
        .category(recipeDescriptionModel.getCategory())
        .description(recipeDescriptionModel.getDescription())
        .descriptionHTML(recipeDescriptionModel.getDescriptionHTML())
        .difficulty(recipeDescriptionModel.getDifficulty())
        .prepTime(recipeDescriptionModel.getPrepTime())
        .totalTime(recipeDescriptionModel.getTotalTime())
        .servingSize(recipeDescriptionModel.getServingSize())
        .link(recipeDescriptionModel.getLink())
        .cardLink(recipeDescriptionModel.getCardLink())
        .videoLink(recipeDescriptionModel.getVideoLink())
        .nutrition(recipeDescriptionModel.getNutrition())
        .ingredients(recipeIngredientMode)
        .imageLink(appendToPath(recipeDescriptionModel.getImagePath()))
        .build();
    return newRecipeDescriptionModel;
  }

  private String appendToPath(String url) {
    if (!StringUtils.isEmpty(url)) {
      return routeConfig.getHelloFreshImageEndpoint() + url;
    }
    return url;
  }

}
