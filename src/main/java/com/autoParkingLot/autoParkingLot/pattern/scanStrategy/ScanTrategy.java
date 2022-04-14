package com.autoParkingLot.autoParkingLot.pattern.scanStrategy;


import com.autoParkingLot.autoParkingLot.dto.Car;
import com.autoParkingLot.autoParkingLot.dto.FloorDto;
import com.autoParkingLot.autoParkingLot.service.interfaces.FloorService;


public class ScanTrategy implements ScanCar{
    private FloorService floorService;

    public ScanTrategy(FloorService floorService) {
        this.floorService = floorService;
    }

    @Override
    public FloorDto scanCarAndAssignFloor(Car car) throws Exception {
        return floorService.getFloorByCapacity(car);
    }
}
