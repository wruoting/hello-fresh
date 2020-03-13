
package com.hellofresh.api.model.response.RecipeDescriptionModel;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "country",
    "id",
    "type",
    "name",
    "slug",
    "description",
    "internalName",
    "shipped",
    "imageLink",
    "imagePath",
    "usage",
    "hasDuplicatedName",
    "allergens",
    "family"
})
@Data
@Builder
public class Ingredient {

    @JsonProperty("country")
    public String country;

    @JsonProperty("id")
    public String id;

    @JsonProperty("type")
    public String type;

    @JsonProperty("name")
    public String name;

    @JsonProperty("description")
    public Object description;

    @JsonProperty("internalName")
    public String internalName;

    @JsonProperty("shipped")
    public Boolean shipped;

    @JsonProperty("imageLink")
    public String imageLink;

    @JsonProperty("imagePath")
    public String imagePath;

    @JsonProperty("allergens")
    public List<String> allergens = null;

    @JsonProperty("family")
    public Family family;

}
