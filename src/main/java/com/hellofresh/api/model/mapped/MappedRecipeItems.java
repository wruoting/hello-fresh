package com.hellofresh.api.model.mapped;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.hellofresh.api.model.response.RecipeSuggestionModel.RecipeItemIterator;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MappedRecipeItems {
  List<RecipeItemIterator> recipeItems;
}
