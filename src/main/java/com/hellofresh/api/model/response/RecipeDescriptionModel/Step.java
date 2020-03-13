
package com.hellofresh.api.model.response.RecipeDescriptionModel;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "index",
    "instructionsMarkdown",
    "instructionsHTML",
    "instructions",
    "timers",
    "images",
    "videos",
    "ingredients",
    "utensils"
})
@Data
public class Step {

    @JsonProperty("index")
    public Integer index;

    @JsonProperty("instructionsMarkdown")
    public String instructionsMarkdown;

    @JsonProperty("instructionsHTML")
    public String instructionsHTML;

    @JsonProperty("instructions")
    public String instructions;

    @JsonProperty("timers")
    public List<Timer> timers = null;

    @JsonProperty("images")
    public List<Image> images = null;

    @JsonProperty("videos")
    public List<Object> videos = null;

    @JsonProperty("ingredients")
    public List<String> ingredients = null;

    @JsonProperty("utensils")
    public List<String> utensils = null;

}
