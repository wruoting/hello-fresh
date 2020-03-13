
package com.hellofresh.api.model.response.RecipeDescriptionModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
    "usageByCountry",
    "createdAt",
    "updatedAt"
})
@Data
public class Family {

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

    @JsonProperty("priority")
    public Integer priority;

    @JsonProperty("iconLink")
    public String iconLink;

    @JsonProperty("iconPath")
    public String iconPath;

    @JsonProperty("usageByCountry")
    public UsageByCountry usageByCountry;

    @JsonProperty("createdAt")
    public String createdAt;

    @JsonProperty("updatedAt")
    public String updatedAt;

}
