
package com.hellofresh.api.model.response.RecipeDescriptionModel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "country",
    "id",
    "name",
    "category",
    "headline",
    "description",
    "descriptionHTML",
    "descriptionMarkdown",
    "comment",
    "difficulty",
    "prepTime",
    "totalTime",
    "servingSize",
    "createdAt",
    "updatedAt",
    "link",
    "imageLink",
    "imagePath",
    "cardLink",
    "videoLink",
    "nutrition",
    "ingredients",
    "allergens",
    "utensils",
    "tags",
    "cuisines",
    "wines",
    "marketplaceItems",
    "author",
    "label",
    "yieldType",
    "yields",
    "steps",
    "averageRating",
    "ratingsCount",
    "favoritesCount",
    "active",
    "highlighted",
    "isDinnerToLunch",
    "isExcludedFromIndex",
    "isPremium",
    "websiteUrl",
    "clonedFrom",
    "canonical",
    "canonicalLink"
})
@Data
@Builder(toBuilder = true)
public class RecipeDescriptionModel {

    @JsonProperty("country")
    private String country;

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("category")
    private Category category;

    @JsonProperty("headline")
    private String headline;

    @JsonProperty("description")
    private String description;

    @JsonProperty("descriptionHTML")
    private String descriptionHTML;

    @JsonProperty("descriptionMarkdown")
    private String descriptionMarkdown;

    @JsonProperty("comment")
    private Object comment;

    @JsonProperty("difficulty")
    private Integer difficulty;

    @JsonProperty("prepTime")
    private String prepTime;

    @JsonProperty("totalTime")
    private Object totalTime;

    @JsonProperty("servingSize")
    private Integer servingSize;

    @JsonProperty("link")
    private String link;

    @JsonProperty("imageLink")
    private String imageLink;

    @JsonIgnore
    @JsonProperty("imagePath")
    private String imagePath;

    @JsonProperty("cardLink")
    private String cardLink;

    @JsonProperty("videoLink")
    private Object videoLink;

    @JsonProperty("nutrition")
    private List<Nutrition> nutrition = null;

    @JsonProperty("ingredients")
    private List<Ingredient> ingredients = null;

    @JsonProperty("allergens")
    private List<Allergen> allergens = null;

    @JsonProperty("utensils")
    private List<Utensil> utensils = null;

    @JsonProperty("tags")
    private List<Tag> tags = null;

    @JsonProperty("cuisines")
    private List<Cuisine> cuisines = null;

    @JsonProperty("wines")
    private List<Object> wines = null;

    @JsonProperty("marketplaceItems")
    private List<Object> marketplaceItems = null;

    @JsonProperty("author")
    private String author;

    @JsonProperty("label")
    private Label label;

    @JsonProperty("yieldType")
    private String yieldType;

    @JsonProperty("yields")
    private List<Yield> yields = null;

    @JsonProperty("steps")
    private List<Step> steps = null;

    @JsonProperty("averageRating")
    private Integer averageRating;

    @JsonProperty("ratingsCount")
    private Integer ratingsCount;

    @JsonProperty("favoritesCount")
    private Integer favoritesCount;

    @JsonProperty("active")
    private Boolean active;

    @JsonProperty("highlighted")
    private Boolean highlighted;

    @JsonProperty("isDinnerToLunch")
    private Boolean isDinnerToLunch;

    @JsonProperty("websiteUrl")
    private String websiteUrl;

    @JsonProperty("clonedFrom")
    private String clonedFrom;

    @JsonProperty("canonical")
    private Object canonical;

    @JsonProperty("canonicalLink")
    private Object canonicalLink;

}
