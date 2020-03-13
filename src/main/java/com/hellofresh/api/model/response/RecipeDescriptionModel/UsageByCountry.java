
package com.hellofresh.api.model.response.RecipeDescriptionModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "AO",
    "AT",
    "AU",
    "BE",
    "CA",
    "CH",
    "CK",
    "DE",
    "DK",
    "ER",
    "FR",
    "GB",
    "LU",
    "NL",
    "NZ",
    "SE",
    "US"
})
@Data
public class UsageByCountry {

    @JsonProperty("AO")
    public Integer aO;

    @JsonProperty("AT")
    public Integer aT;

    @JsonProperty("AU")
    public Integer aU;

    @JsonProperty("BE")
    public Integer bE;

    @JsonProperty("CA")
    public Integer cA;

    @JsonProperty("CH")
    public Integer cH;

    @JsonProperty("CK")
    public Integer cK;

    @JsonProperty("DE")
    public Integer dE;

    @JsonProperty("DK")
    public Integer dK;

    @JsonProperty("ER")
    public Integer eR;

    @JsonProperty("FR")
    public Integer fR;

    @JsonProperty("GB")
    public Integer gB;

    @JsonProperty("LU")
    public Integer lU;

    @JsonProperty("NL")
    public Integer nL;

    @JsonProperty("NZ")
    public Integer nZ;

    @JsonProperty("SE")
    public Integer sE;

    @JsonProperty("US")
    public Integer uS;

}
