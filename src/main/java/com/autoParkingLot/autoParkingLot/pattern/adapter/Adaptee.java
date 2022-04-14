package com.autoParkingLot.autoParkingLot.pattern.adapter;

import com.autoParkingLot.autoParkingLot.entity.Parking;

public interface Adaptee {
    void specificRequestForPayment(Parking parking) throws Exception;
}
