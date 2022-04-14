package com.autoParkingLot.autoParkingLot.pattern.entryGateSelectionStrategy;

import com.autoParkingLot.autoParkingLot.dto.Car;
import com.autoParkingLot.autoParkingLot.dto.ParkingDto;
import com.autoParkingLot.autoParkingLot.utils.GateType;

public class GateSelectionStrategy implements EntryGate {
    @Override
    public void entryGateSelection(Car car, ParkingDto parkingDto) {
        parkingDto.setEntryGateName(GateType.DEFAULT.toString());
    }
}
