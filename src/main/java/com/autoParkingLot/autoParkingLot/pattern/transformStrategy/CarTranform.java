package com.autoParkingLot.autoParkingLot.pattern.transformStrategy;

import com.autoParkingLot.autoParkingLot.dto.ParkingDto;

public class CarTranform implements Transform {

    private Transform transform;

    public CarTranform(Transform transform) {
        this.transform = transform;
    }

    @Override
    public void transForm(ParkingDto parkingDto) {
        this.transform.transForm(parkingDto);
    }
}
