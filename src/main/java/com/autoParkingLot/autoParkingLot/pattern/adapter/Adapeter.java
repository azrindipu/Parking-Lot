package com.autoParkingLot.autoParkingLot.pattern.adapter;

import com.autoParkingLot.autoParkingLot.entity.Parking;

public class Adapeter implements Target{

    private Adaptee adaptee;

    public Adapeter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request(Parking parking) throws Exception {
        adaptee.specificRequestForPayment(parking);
    }
}
