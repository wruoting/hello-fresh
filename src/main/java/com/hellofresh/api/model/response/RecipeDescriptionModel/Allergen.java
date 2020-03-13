
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
    "description",
    "tracesOf",
    "triggersTracesOf",
    "iconLink",
    "iconPath",
    "usage"
})
@Data
@Builder
public class Allergen {

    @JsonProperty("id")
    public String id;

    @JsonProperty("type")
    public String type;

    @JsonProperty("name")
    public String name;

    @JsonProperty("slug")
    public String slug;

    @JsonProperty("description")
    public Object description;

    @JsonProperty("tracesOf")
    public Boolean tracesOf;

    @JsonProperty("triggersTracesOf")
    public Boolean triggersTracesOf;

    @JsonProperty("iconLink")
    public String iconLink;

    @JsonProperty("iconPath")
    public String iconPath;

    @JsonProperty("usage")
    public Integer usage;
}
