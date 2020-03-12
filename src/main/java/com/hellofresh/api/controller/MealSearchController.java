package com.hellofresh.api.controller;

import com.hellofresh.api.config.RouteConfig;
import com.hellofresh.api.config.TokenConfig;
import com.hellofresh.api.model.response.RecipeSuggestionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/v1/recipes")
public class MealSearchController {

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

  @GetMapping
  public ResponseEntity<RecipeSuggestionModel> getRecipe(
      @RequestParam(value = "query", required = false) String query,
      @RequestParam(value = "page", defaultValue = "1", required = false) String page,
      @RequestParam(value = "take", defaultValue = "10", required = false) String take,
      @RequestParam(value = "country", defaultValue = "us", required = false) String country) {

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
    return response;
  }
}
