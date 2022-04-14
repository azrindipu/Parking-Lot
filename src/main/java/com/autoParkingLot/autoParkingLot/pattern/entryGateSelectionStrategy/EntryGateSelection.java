package com.autoParkingLot.autoParkingLot.pattern.entryGateSelectionStrategy;

import com.autoParkingLot.autoParkingLot.dto.Car;
import com.autoParkingLot.autoParkingLot.dto.ParkingDto;

public class EntryGateSelection implements EntryGate {
    private EntryGate entryGate;

    public EntryGateSelection(EntryGate entryGate) {
        this.entryGate = entryGate;
    }

    @Override
    public void entryGateSelection(Car car, ParkingDto parkingDto) {
        this.entryGate.entryGateSelection(car, parkingDto);
    }
}
