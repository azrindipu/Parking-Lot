package com.autoParkingLot.autoParkingLot.pattern;

import com.autoParkingLot.autoParkingLot.entity.Parking;

public abstract class CheckoutTemplate {

    public Parking checkOutParking(Parking parking) throws Exception {
        this.makePayment(parking);
        this.updateParking(parking);
        this.updateFloor(parking);
        return parking;
    }

    public abstract void makePayment(Parking parking) throws Exception;
    public abstract void updateParking(Parking parking) throws Exception;
    public abstract void updateFloor(Parking parking) throws Exception;
}
