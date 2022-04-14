package com.autoParkingLot.autoParkingLot.exception;

import com.autoParkingLot.autoParkingLot.utils.Constants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExceptionResponse {

    @JsonProperty("time")
    private String time;

    @JsonProperty(Constants.RESPONSE_BODY_MESSAGE)
    private String errorMessage;
}
