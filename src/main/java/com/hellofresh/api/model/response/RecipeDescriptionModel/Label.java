
package com.hellofresh.api.model.response.RecipeDescriptionModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "text",
    "handle",
    "backgroundColor",
    "foregroundColor"
})
@Data
@Builder
public class Label {

    @JsonProperty("text")
    public String text;

    @JsonProperty("handle")
    public String handle;

    @JsonProperty("backgroundColor")
    public String backgroundColor;

    @JsonProperty("foregroundColor")
    public String foregroundColor;

}
