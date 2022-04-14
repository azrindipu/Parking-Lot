package com.autoParkingLot.autoParkingLot.pattern.scanStrategy;

import com.autoParkingLot.autoParkingLot.dto.Car;
import com.autoParkingLot.autoParkingLot.dto.FloorDto;

public interface ScanCar {
    FloorDto scanCarAndAssignFloor(Car car) throws Exception;
}
