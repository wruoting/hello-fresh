
package com.hellofresh.api.model.response;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonProperty("slug")
    private String slug;
    @JsonProperty("headline")
    private String headline;
    @JsonProperty("image")
    private String image;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

}
