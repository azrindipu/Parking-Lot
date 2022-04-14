package com.autoParkingLot.autoParkingLot.pattern;

import com.autoParkingLot.autoParkingLot.dto.Car;
import com.autoParkingLot.autoParkingLot.dto.FloorDto;
import com.autoParkingLot.autoParkingLot.dto.ParkingDto;

import java.util.Date;

public abstract class ParkingTemplate {
    public ParkingDto createNewParking(Car car) throws Exception{
        ParkingDto parkingDto = new ParkingDto();
        parkingDto.setFloorDto(scanCar(car));
        generatePricePerMinuteForParking(car, parkingDto);
        getAppropritaeEntryPoint(car, parkingDto);
        transportCarToParkingPlace(parkingDto);
        parkingDto.setCarHeight(car.getCarHeight());
        parkingDto.setCarType(car.getCarType());
        parkingDto.setCarWeight(car.getCarWeight());
        parkingDto.setCheckinTime(new Date());
        return parkingDto;
    }

    public abstract FloorDto scanCar(Car car) throws Exception;
    public abstract void generatePricePerMinuteForParking(Car car, ParkingDto parkingDto) throws Exception;
    public abstract void getAppropritaeEntryPoint(Car car, ParkingDto parkingDto) throws Exception;
    public abstract void transportCarToParkingPlace(ParkingDto parkingDto) throws Exception;
}
