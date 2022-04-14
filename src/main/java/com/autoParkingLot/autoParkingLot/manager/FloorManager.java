package com.autoParkingLot.autoParkingLot.manager;

import com.autoParkingLot.autoParkingLot.dto.FloorDto;
import com.autoParkingLot.autoParkingLot.entity.Floor;
import com.autoParkingLot.autoParkingLot.exception.ApiExceptionBadRequest;
import com.autoParkingLot.autoParkingLot.exception.ApiExceptionNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FloorManager {

    public void validateFloorDto(FloorDto floorDto){
        if(floorDto == null)
            throw new ApiExceptionBadRequest("Floor is not valid");
        this.validFloorNumber(floorDto.getFloorNumber());
        if(floorDto.getCeilingHeight() == null || floorDto.getCeilingHeight() <= 0)
            throw new ApiExceptionBadRequest("Floor height is not valid");
        if(floorDto.getTotalWeightCapacity() == null || floorDto.getTotalWeightCapacity() <= 0)
            throw new ApiExceptionBadRequest("Floor weight is not valid");
    }

    public void validFloorNumber(Integer floorNumber){
        if(floorNumber == null || floorNumber < 0)
            throw new ApiExceptionBadRequest("Floor number is not valid");
    }

    public void isFloorFound(Floor floor){
        if(floor == null) throw new ApiExceptionNotFoundException("Floor not found");
    }

    public void isFloorFound(List<Floor> floors){
        if(floors == null || floors.size() == 0) throw new ApiExceptionNotFoundException("Floor not found");
    }

    public Floor convertDtoToEntity(FloorDto floorDto){
        return Floor.builder()
                .floorNumber(floorDto.getFloorNumber())
                .ceilingHeight(floorDto.getCeilingHeight())
                .createDate(floorDto.getCreateDate())
                .totalWeightCapacity(floorDto.getTotalWeightCapacity())
                .remainingWeightCapacity(floorDto.getTotalWeightCapacity())
                .build();
    }

    public FloorDto convertEnityToDto(Floor floor){
        return FloorDto.builder()
                .id(floor.getId())
                .floorNumber(floor.getFloorNumber())
                .createDate(floor.getCreateDate())
                .lastUpdateTime(floor.getLastUpdateTime())
                .ceilingHeight(floor.getCeilingHeight())
                .totalWeightCapacity(floor.getTotalWeightCapacity())
                .remainingWeightCapacity(floor.getRemainingWeightCapacity())
                .build();
    }
}
