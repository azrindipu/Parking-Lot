package com.autoParkingLot.autoParkingLot.service.interfaces;

import com.autoParkingLot.autoParkingLot.dto.Car;
import com.autoParkingLot.autoParkingLot.dto.FloorDto;
import com.autoParkingLot.autoParkingLot.entity.Floor;

public interface FloorService {
    FloorDto createFloor(FloorDto floorDto) throws Exception;
    FloorDto getFloor(Integer floorNumber) throws Exception;
    FloorDto updateFloor(FloorDto floorDto) throws Exception;
    FloorDto getFloorByCapacity(Car car) throws Exception;
    Floor getFloorById(Integer id);
}
