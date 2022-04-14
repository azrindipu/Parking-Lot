package com.autoParkingLot.autoParkingLot.pattern.calculatePricestrategy;

import com.autoParkingLot.autoParkingLot.entity.Parking;

public class CalulateCheckoutPrice implements CalculatePrice{

    private CalculatePrice calculatePrice;

    public CalulateCheckoutPrice(CalculatePrice calculatePrice) {
        this.calculatePrice = calculatePrice;
    }

    public void calculateAndAddPrice(Parking parking) throws Exception {
        calculatePrice.calculateAndAddPrice(parking);
    }
}
