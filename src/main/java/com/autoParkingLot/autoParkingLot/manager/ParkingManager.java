package com.autoParkingLot.autoParkingLot.manager;

import com.autoParkingLot.autoParkingLot.dto.FloorDto;
import com.autoParkingLot.autoParkingLot.dto.ParkingDto;
import com.autoParkingLot.autoParkingLot.entity.Parking;
import com.autoParkingLot.autoParkingLot.exception.ApiExceptionBadRequest;
import com.autoParkingLot.autoParkingLot.utils.Status;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParkingManager {

    public Parking convertDtoToEntity(ParkingDto parkingDto){
        return Parking.builder()
                .carHeight(parkingDto.getCarHeight())
                .carType(parkingDto.getCarType())
                .carWeight(parkingDto.getCarWeight())
                .pricePerMinute(parkingDto.getPricePerMinute())
                .entryGate(parkingDto.getEntryGateName())
                .checkinTime(parkingDto.getCheckinTime())
                .build();
    }

    public void dtoIsNull(ParkingDto parkingDto){
        if(parkingDto == null) throw new ApiExceptionBadRequest("ParkingDto is null");
    }

    public ParkingDto convertEntityToDto(Parking parking, FloorDto floorDto){
        return ParkingDto.builder()
                .carHeight(parking.getCarHeight())
                .carType(parking.getCarType())
                .carWeight(parking.getCarWeight())
                .checkinTime(parking.getCheckinTime())
                .entryGateName(parking.getEntryGate())
                .TotalAmountToPay(parking.getPaymentPrice()== null? null: parking.getPaymentPrice())
                .checkoutTime(parking.getCheckoutTime()==null?null:parking.getCheckoutTime())
                .floorDto(floorDto).build();
    }

    public void isNullOrEmpty(List<Parking> parkings){
        if(parkings == null || parkings.size() == 0) throw new ApiExceptionBadRequest("Parking list is null or empty");
    }

    public void isNull(Parking parking){
        if(parking == null) throw new ApiExceptionBadRequest("Parking is null");
    }

    public void validStatus(String status){
        Status[] allStatus = Status.values();
        for(Status eachStatus : allStatus){
            if(!eachStatus.toString().equalsIgnoreCase(status)){
                throw new ApiExceptionBadRequest("Invalid status");
            }
        }
    }
}
