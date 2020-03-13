package com.hellofresh.api.service;

import com.hellofresh.api.config.RouteConfig;
import com.hellofresh.api.config.TokenConfig;
import com.hellofresh.api.model.response.RecipeDescriptionModel.RecipeDescriptionModel;
import com.hellofresh.api.model.response.RecipeSuggestionModel.RecipeSuggestionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
    ResponseEntity<RecipeDescriptionModel> response;
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + tokenConfig.getBearerToken());
      HttpEntity entity = new HttpEntity<String>(null, headers);

      UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(helloFreshRoute + "/api/recipes/" + id)
          .queryParam(COUNTRY_PARAM, country);

      response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
          new ParameterizedTypeReference<RecipeDescriptionModel>() {

          });
    } catch (HttpClientErrorException e) {
      System.out.println("Unexpected Response from getRecipeDescription: " + e);
      throw e;
    } catch (Exception e) {
      System.out.println("Something went wrong with this request: " + e);
      throw e;
    }
    return response.getBody();
  }
}
