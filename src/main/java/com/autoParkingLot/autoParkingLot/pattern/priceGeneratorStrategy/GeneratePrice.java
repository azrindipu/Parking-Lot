package com.autoParkingLot.autoParkingLot.pattern.priceGeneratorStrategy;

import com.autoParkingLot.autoParkingLot.dto.Car;
import com.autoParkingLot.autoParkingLot.dto.ParkingDto;

public class GeneratePrice implements GeneratePricePerMinute {

    private GeneratePricePerMinute generatePricePerMinute;

    public GeneratePrice(GeneratePricePerMinute generatePricePerMinute) {
        this.generatePricePerMinute = generatePricePerMinute;
    }

    @Override
    public void generatePrive(Car car, ParkingDto parkingDto) {
        this.generatePricePerMinute.generatePrive(car, parkingDto);
    }
}
