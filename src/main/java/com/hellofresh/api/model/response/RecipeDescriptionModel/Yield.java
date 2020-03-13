
package com.hellofresh.api.model.response.RecipeDescriptionModel;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "yields",
    "ingredients"
})
@Data
public class Yield {

    @JsonProperty("yields")
    public Integer yields;

    @JsonProperty("ingredients")
    public List<YieldIngredient> ingredients = null;

}
