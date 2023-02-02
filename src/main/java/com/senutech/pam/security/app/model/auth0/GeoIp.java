package com.senutech.pam.security.app.model.auth0;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeoIp {
    @JsonProperty("country_code")
    private String countryCode;
    @JsonProperty("country_code3")
    private String countryCode3;
    @JsonProperty("country_name")
    private String countryName;
    @JsonProperty("city_name")
    private String cityName;
    private double latitude;
    private double longitude;
    @JsonProperty("time_zone")
    private String timeZone;
    @JsonProperty("continent_code")
    private String continentCode;
    @JsonProperty("subdivision_code")
    private String subDivisionCode;
    @JsonProperty("subDivisionName")
    private String subDivisionName;
}
