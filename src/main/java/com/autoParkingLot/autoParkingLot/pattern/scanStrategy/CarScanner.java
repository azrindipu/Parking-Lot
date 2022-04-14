package com.autoParkingLot.autoParkingLot.pattern.scanStrategy;

import com.autoParkingLot.autoParkingLot.dto.Car;
import com.autoParkingLot.autoParkingLot.dto.FloorDto;

public class CarScanner implements ScanCar{
    private ScanCar scanCar;

    public CarScanner(ScanCar scanCar) {
        this.scanCar = scanCar;
    }

    @Override
    public FloorDto scanCarAndAssignFloor(Car car) throws Exception {
        return this.scanCar.scanCarAndAssignFloor(car);
    }
}
