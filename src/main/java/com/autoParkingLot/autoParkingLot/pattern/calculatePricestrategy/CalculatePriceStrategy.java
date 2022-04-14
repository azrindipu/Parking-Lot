package com.autoParkingLot.autoParkingLot.pattern.calculatePricestrategy;

import com.autoParkingLot.autoParkingLot.entity.Parking;

import java.util.Date;

public class CalculatePriceStrategy implements CalculatePrice {
    @Override
    public void calculateAndAddPrice(Parking parking) throws Exception {
        parking.setCheckoutTime(new Date());
        parking.setPaymentPrice(20l);
    }
}
