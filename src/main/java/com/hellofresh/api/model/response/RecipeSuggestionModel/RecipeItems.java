
package com.hellofresh.api.model.response.RecipeSuggestionModel;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "items"
})
public class RecipeItems {

    @JsonProperty("items")
    private List<RecipeItemIterator> items = null;

}
