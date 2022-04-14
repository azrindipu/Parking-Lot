package com.autoParkingLot.autoParkingLot.pattern.transformStrategy;

import com.autoParkingLot.autoParkingLot.dto.ParkingDto;

public class TransFormStrategy implements Transform {
    @Override
    public void transForm(ParkingDto parkingDto) {
        parkingDto.setParkingDone(true);
    }
}
