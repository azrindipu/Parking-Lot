package com.autoParkingLot.autoParkingLot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Car {

    @JsonProperty("carType")
    private String carType;

    @JsonProperty("carHeight")
    private Long carHeight;

    @JsonProperty("carWeight")
    private Long carWeight;

}
