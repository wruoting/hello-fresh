
package com.hellofresh.api.model.response.RecipeDescriptionModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "amount",
    "unit"
})
@Data
@Builder
public class YieldIngredient {

    @JsonProperty("id")
    public String id;

    @JsonProperty("amount")
    public Float amount;

    @JsonProperty("unit")
    public String unit;

}
