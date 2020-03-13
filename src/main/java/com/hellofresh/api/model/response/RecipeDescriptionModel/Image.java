
package com.hellofresh.api.model.response.RecipeDescriptionModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "link",
    "path",
    "caption"
})
@Data
@Builder
public class Image {

    @JsonProperty("link")
    public String link;

    @JsonProperty("path")
    public String path;

    @JsonProperty("caption")
    public String caption;

}
