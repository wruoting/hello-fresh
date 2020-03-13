
package com.hellofresh.api.model.response.RecipeDescriptionModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "amount",
    "unit"
})
@Data
public class YieldIngredient {

    @JsonProperty("id")
    public String id;

    @JsonProperty("amount")
    public Object amount;

    @JsonProperty("unit")
    public Object unit;

}
