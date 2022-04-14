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
public class FloorDto {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("floorNumber")
    private Integer floorNumber;

    @JsonProperty("ceilingHeight")
    private Long ceilingHeight;

    @JsonProperty("totalWeightCapacity")
    private Long totalWeightCapacity;

    @JsonProperty("remainingWeightCapacity")
    private Long remainingWeightCapacity;

    @JsonProperty("createDate")
    private Date createDate;

    @JsonProperty("lastUpdateTime")
    private Date lastUpdateTime;
}
