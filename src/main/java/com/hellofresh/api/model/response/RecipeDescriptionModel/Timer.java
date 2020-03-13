
package com.hellofresh.api.model.response.RecipeDescriptionModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "duration",
    "temperature",
    "temperatureUnit",
    "ovenMode"
})
@Data
@Builder
public class Timer {

    @JsonProperty("name")
    public String name;

    @JsonProperty("duration")
    public String duration;

    @JsonProperty("temperature")
    public Object temperature;

    @JsonProperty("temperatureUnit")
    public Object temperatureUnit;

    @JsonProperty("ovenMode")
    public Object ovenMode;

}
