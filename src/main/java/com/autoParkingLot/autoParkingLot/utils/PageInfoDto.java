package com.autoParkingLot.autoParkingLot.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageInfoDto {
    private boolean last;
    private int totalPages;
    private long totalElements;
    private boolean first;
    private int numberOfElements;
    private int size;
    private int number;
}
