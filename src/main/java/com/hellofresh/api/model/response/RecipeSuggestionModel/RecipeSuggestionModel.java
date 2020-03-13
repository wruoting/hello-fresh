
package com.hellofresh.api.model.response.RecipeSuggestionModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "items"
})
public class RecipeSuggestionModel {

    @JsonProperty("items")
    public List<RecipeItems> items = null;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
