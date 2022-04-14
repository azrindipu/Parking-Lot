package com.autoParkingLot.autoParkingLot.pattern.entryGateSelectionStrategy;

import com.autoParkingLot.autoParkingLot.dto.Car;
import com.autoParkingLot.autoParkingLot.dto.ParkingDto;

public interface EntryGate {
    void entryGateSelection(Car car, ParkingDto parkingDto);
}
