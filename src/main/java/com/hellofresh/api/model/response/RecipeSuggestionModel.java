
package com.hellofresh.api.model.response;

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
    "take",
    "skip",
    "count",
    "total",
    "items"
})
public class RecipeSuggestionModel {
    @JsonProperty("take")
    public Integer take;
    @JsonProperty("skip")
    public Integer skip;
    @JsonProperty("count")
    public Integer count;
    @JsonProperty("total")
    public Object total;
    @JsonProperty("items")
    public List<RecipeItem> items = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
