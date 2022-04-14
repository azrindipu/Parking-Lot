package com.autoParkingLot.autoParkingLot.pattern.priceGeneratorStrategy;

import com.autoParkingLot.autoParkingLot.dto.Car;
import com.autoParkingLot.autoParkingLot.dto.ParkingDto;

public interface GeneratePricePerMinute {
    void generatePrive(Car car, ParkingDto parkingDto);
}
