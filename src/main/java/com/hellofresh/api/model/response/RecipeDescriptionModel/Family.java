
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
    "priority",
    "iconLink",
    "iconPath",
    "createdAt",
    "updatedAt"
})
@Data
@Builder
public class Family {

    @JsonProperty("id")
    public String id;

    @JsonProperty("type")
    public String type;

    @JsonProperty("name")
    public String name;

    @JsonProperty("description")
    public Object description;

    @JsonProperty("iconLink")
    public String iconLink;

    @JsonProperty("iconPath")
    public String iconPath;

}
