package com.autoParkingLot.autoParkingLot.pattern.adapter;

import com.autoParkingLot.autoParkingLot.entity.Parking;

public interface Target {
    void request(Parking parking) throws Exception;
}
