package com.autoParkingLot.autoParkingLot.pattern.calculatePricestrategy;

import com.autoParkingLot.autoParkingLot.entity.Parking;

public interface CalculatePrice {
    void calculateAndAddPrice(Parking parking) throws Exception;
}
