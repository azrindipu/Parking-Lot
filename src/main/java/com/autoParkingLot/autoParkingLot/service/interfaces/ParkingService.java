package com.autoParkingLot.autoParkingLot.service.interfaces;

import com.autoParkingLot.autoParkingLot.dto.Car;
import com.autoParkingLot.autoParkingLot.dto.ParkingDto;

public interface ParkingService {
    ParkingDto createNewParking(Car car) throws Exception;
    ParkingDto getParking(Integer id) throws Exception;
    ParkingDto updateParking(Integer id, String status) throws Exception;
}
