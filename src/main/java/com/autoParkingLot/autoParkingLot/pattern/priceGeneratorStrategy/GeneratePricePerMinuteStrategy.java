package com.autoParkingLot.autoParkingLot.pattern.priceGeneratorStrategy;

import com.autoParkingLot.autoParkingLot.dto.Car;
import com.autoParkingLot.autoParkingLot.dto.ParkingDto;

public class GeneratePricePerMinuteStrategy implements GeneratePricePerMinute {
    @Override
    public void generatePrive(Car car, ParkingDto parkingDto) {
        parkingDto.setPricePerMinute(2l);
    }
}
