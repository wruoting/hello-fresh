
package com.hellofresh.api.model.response.RecipeDescriptionModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "type",
    "name",
    "slug",
    "iconLink",
    "iconPath",
    "numberOfRecipes",
})
@Data
@Builder
public class Tag {

    @JsonProperty("id")
    public String id;

    @JsonProperty("type")
    public String type;

    @JsonProperty("name")
    public String name;

    @JsonProperty("slug")
    public String slug;

    @JsonProperty("iconLink")
    public Object iconLink;

    @JsonProperty("iconPath")
    public Object iconPath;

    @JsonProperty("numberOfRecipes")
    public Integer numberOfRecipes;

}
