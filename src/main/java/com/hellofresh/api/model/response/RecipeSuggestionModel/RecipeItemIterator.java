
package com.hellofresh.api.model.response.RecipeSuggestionModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "title",
    "recipeId",
    "slug",
    "headline",
    "image"
})
@Data
public class RecipeItemIterator {

    @JsonProperty("title")
    private String title;

    @JsonProperty("recipeId")
    private String recipeId;

    @JsonProperty("headline")
    private String headline;

    @JsonProperty("image")
    private String image;

}
