package com.hellofresh.api.service;

import com.hellofresh.api.config.RouteConfig;
import com.hellofresh.api.config.TokenConfig;
import com.hellofresh.api.model.mapped.MappedRecipeItems;
import com.hellofresh.api.model.response.RecipeDescriptionModel.Ingredient;
import com.hellofresh.api.model.response.RecipeDescriptionModel.MappedIngredientAmounts;
import com.hellofresh.api.model.response.RecipeDescriptionModel.RecipeDescriptionModel;
import com.hellofresh.api.model.response.RecipeDescriptionModel.YieldIngredient;
import com.hellofresh.api.model.response.RecipeSuggestionModel.RecipeItemIterator;
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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.io.UnsupportedEncodingException;

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
          .queryParam(QUERY_PARAM, encodeValue(query))
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

  public MappedRecipeItems mapRecipeItems(MappedRecipeItems mappedRecipeItems) {
    List<RecipeItemIterator> recipeItemIterator = new ArrayList<>();

    for (RecipeItemIterator item: mappedRecipeItems.getRecipeItems()) {
      recipeItemIterator.add(
          RecipeItemIterator
              .builder()
              .title(item.getTitle())
              .recipeId(item.getRecipeId())
              .headline(item.getHeadline())
              .image(appendToPath(item.getImage()))
              .build()
      );
    }
    MappedRecipeItems newMappedRecipeItems = MappedRecipeItems
        .builder()
        .recipeItems(recipeItemIterator)
        .build();
    return newMappedRecipeItems;
  }

  public MappedIngredientAmounts mapIngredientAmounts(Ingredient ingredient, YieldIngredient yieldIngredient) {
    return MappedIngredientAmounts
        .builder()
        .id(ingredient.getId())
        .name(ingredient.getName())
        .description(ingredient.getDescription())
        .type(ingredient.getType())
        .amount(yieldIngredient.getAmount())
        .unit(yieldIngredient.getUnit())
        .build();
  }

  public List<MappedIngredientAmounts> reduceIngredientAmounts(List<MappedIngredientAmounts> mappedIngredientAmounts) {
    List<MappedIngredientAmounts> condensedListMappedIngredientAmounts = new ArrayList<>();
    for (MappedIngredientAmounts ingredientAmounts: mappedIngredientAmounts) {
      if (condensedListMappedIngredientAmounts.isEmpty()) {
        condensedListMappedIngredientAmounts.add(ingredientAmounts);
      } else {
        // Check if ids are equal, and condense into one
        boolean isPresent = false;
        for(MappedIngredientAmounts condensedIngredient: condensedListMappedIngredientAmounts) {
          if (condensedIngredient.getId().equals(ingredientAmounts.getId())) {
            float amountCondensedIngredient = condensedIngredient.getAmount() != null ? condensedIngredient.getAmount() : 0;
            float amountIngredientAmounts = ingredientAmounts.getAmount() != null ? ingredientAmounts.getAmount(): 0;
            int index = condensedListMappedIngredientAmounts.indexOf(condensedIngredient);
            condensedListMappedIngredientAmounts.set(index,
                MappedIngredientAmounts.builder()
                    .country(condensedIngredient.getCountry())
                    .id(condensedIngredient.getId())
                    .type(condensedIngredient.getType())
                    .name(condensedIngredient.getName())
                    .description(condensedIngredient.getDescription())
                    .internalName(condensedIngredient.getInternalName())
                    .shipped(condensedIngredient.getShipped())
                    .imageLink(condensedIngredient.getImageLink())
                    .imageLink(condensedIngredient.getImagePath())
                    .allergens(condensedIngredient.getAllergens())
                    .family(condensedIngredient.getFamily())
                    .amount(Float.sum(amountCondensedIngredient, amountIngredientAmounts))
                    .unit(condensedIngredient.getUnit())
                    .build()
                );
            isPresent = true;
          }
        }
        if(!isPresent) {
          condensedListMappedIngredientAmounts.add(ingredientAmounts);
        }
      }
    }
    return condensedListMappedIngredientAmounts;
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
        .yields(recipeDescriptionModel.getYields())
        .build();
    return newRecipeDescriptionModel;
  }

  private String appendToPath(String url) {
    if (!StringUtils.isEmpty(url)) {
      return url.replaceAll(routeConfig.getHelloFreshCloudfrontEndpoint(),
          routeConfig.getHelloFreshImageEndpoint());
    }
    return url;
  }

  // Method to encode a string value using `UTF-8` encoding scheme
  private static String encodeValue(String value) {
      try {
          return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
      } catch (UnsupportedEncodingException ex) {
          throw new RuntimeException(ex.getCause());
      }
  }
}
