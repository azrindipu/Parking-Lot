package com.autoParkingLot.autoParkingLot.service;

import com.autoParkingLot.autoParkingLot.dto.Car;
import com.autoParkingLot.autoParkingLot.dto.FloorDto;
import com.autoParkingLot.autoParkingLot.entity.Floor;
import com.autoParkingLot.autoParkingLot.manager.FloorManager;
import com.autoParkingLot.autoParkingLot.repository.FloorRepository;
import com.autoParkingLot.autoParkingLot.service.interfaces.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FloorServiceImpl implements FloorService {

    @Autowired
    private FloorManager floorManager;

    @Autowired
    private FloorRepository floorRepository;

    @Override
    public FloorDto createFloor(FloorDto floorDto)  throws Exception{
        floorManager.validateFloorDto(floorDto);
        Floor floor = floorManager.convertDtoToEntity(floorDto);
        floor.setCreateDate(new Date());
        floor.setLastUpdateTime(new Date());
        floor.setRemainingWeightCapacity(floor.getTotalWeightCapacity());
        floor = floorRepository.save(floor);
        return floorManager.convertEnityToDto(floor);
    }

    @Override
    public FloorDto getFloor(Integer floorNumber)  throws Exception{
        floorManager.validFloorNumber(floorNumber);
        Floor floor = floorRepository.findByFloorNumber(floorNumber);
        floorManager.isFloorFound(floor);
        return floorManager.convertEnityToDto(floor);
    }

    @Override
    public FloorDto updateFloor(FloorDto floorDto) throws Exception {
        floorManager.validateFloorDto(floorDto);
        Floor floor = floorRepository.findByFloorNumber(floorDto.getFloorNumber());
        floorManager.isFloorFound(floor);
        Floor update = floorManager.convertDtoToEntity(floorDto);
        update.setId(floor.getId());
        update.setLastUpdateTime(new Date());
        update = floorRepository.save(update);
        return floorManager.convertEnityToDto(update);
    }

    @Override
    public FloorDto getFloorByCapacity(Car car) {
        List<Floor> floors = floorRepository.findByCeilingHeightAndRemainingWeightCapacityGreaterThan(car.getCarHeight(), car.getCarWeight());
        floorManager.isFloorFound(floors);
        floorManager.isFloorFound(floors.get(0));
        return floorManager.convertEnityToDto(floors.get(0));
    }

    @Override
    public Floor getFloorById(Integer id) {
        return floorRepository.findByIdIn(Arrays.asList(id));
    }


}
