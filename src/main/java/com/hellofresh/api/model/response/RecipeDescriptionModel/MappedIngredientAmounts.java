package com.hellofresh.api.model.response.RecipeDescriptionModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.util.List;

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
    "family",
    "amount",
    "unit"
})
@Data
@Builder
public class MappedIngredientAmounts {

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

  @JsonProperty("amount")
  public Float amount;

  @JsonProperty("unit")
  public String unit;

}
