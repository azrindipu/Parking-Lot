package com.autoParkingLot.autoParkingLot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParkingDto {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("floorDto")
    private FloorDto floorDto;

    @JsonProperty("carType")
    private String carType;

    @JsonProperty("carHeight")
    private Long carHeight;

    @JsonProperty("carWeight")
    private Long carWeight;

    @JsonProperty("checkinTime")
    private Date checkinTime;

    @JsonProperty("checkoutTime")
    private Date checkoutTime;

    @JsonProperty("pricePerMinute")
    private Long pricePerMinute;

    @JsonProperty("TotalAmountToPay")
    private Long TotalAmountToPay;

    @JsonProperty("entryGateName")
    private String entryGateName;

    @JsonProperty("parkingDone")
    private Boolean parkingDone;

}
