package com.autoParkingLot.autoParkingLot.utils;

import com.autoParkingLot.autoParkingLot.dto.Car;
import com.autoParkingLot.autoParkingLot.dto.FloorDto;
import com.autoParkingLot.autoParkingLot.dto.ParkingDto;
import com.autoParkingLot.autoParkingLot.entity.Floor;
import com.autoParkingLot.autoParkingLot.entity.Parking;

import java.util.ArrayList;
import java.util.Date;

public class ObjectCreator {

    public static FloorDto getFloorDto() {
        return FloorDto.builder()
                .ceilingHeight(10l)
                .totalWeightCapacity(10l)
                .floorNumber(generateRandomValue()).build();
    }

    public static Floor getFloor() {
        return Floor.builder()
                .lastUpdateTime(new Date())
                .createDate(new Date())
                .floorNumber(generateRandomValue())
                .ceilingHeight(10l)
                .totalWeightCapacity(10l)
                .remainingWeightCapacity(10l)
                .parkings(new ArrayList<>())
                .build();
    }

    public static Parking getParking() {
        return Parking.builder()
                .pricePerMinute(2l)
                .carWeight(1l)
                .carType("test")
                .carHeight(1l)
                .checkinTime(new Date())
                .entryGate(GateType.DEFAULT.toString())
                .build();
    }

    public static ParkingDto getParkingDto(){
        return ParkingDto.builder()
                .carHeight(2l)
                .carType("test")
                .carWeight(2l)
                .checkinTime(new Date())
                .entryGateName(GateType.DEFAULT.toString())
                .pricePerMinute(2l)
                .build();
    }

    public static Car getCar() {
        return Car.builder()
                .carHeight((long) generateRandomValue())
                .carType("test")
                .carWeight((long) generateRandomValue())
                .build();
    }

    public static int generateRandomValue() {
        int min = 1;
        int max = 1000;
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    public static int generateRandomValue(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }
}
