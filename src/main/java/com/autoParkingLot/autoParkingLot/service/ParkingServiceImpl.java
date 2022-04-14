package com.autoParkingLot.autoParkingLot.service;

import com.autoParkingLot.autoParkingLot.dto.Car;
import com.autoParkingLot.autoParkingLot.dto.ParkingDto;
import com.autoParkingLot.autoParkingLot.entity.Floor;
import com.autoParkingLot.autoParkingLot.entity.Parking;
import com.autoParkingLot.autoParkingLot.manager.FloorManager;
import com.autoParkingLot.autoParkingLot.manager.ParkingManager;
import com.autoParkingLot.autoParkingLot.pattern.CheckoutTemplate;
import com.autoParkingLot.autoParkingLot.pattern.ParkingTemplate;
import com.autoParkingLot.autoParkingLot.repository.ParkingRepository;
import com.autoParkingLot.autoParkingLot.service.interfaces.FloorService;
import com.autoParkingLot.autoParkingLot.service.interfaces.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private ParkingTemplate parkingTemplate;

    @Autowired
    private ParkingManager parkingManager;

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private FloorService floorService;

    @Autowired
    private FloorManager floorManager;

    @Autowired
    private CheckoutTemplate checkoutTemplate;

    @Override
    public ParkingDto createNewParking(Car car) throws Exception {
        ParkingDto parkingDto = parkingTemplate.createNewParking(car);
        parkingManager.dtoIsNull(parkingDto);
        Parking parking = parkingManager.convertDtoToEntity(parkingDto);
        Floor floor = floorService.getFloorById(parkingDto.getFloorDto().getId());
        floor.setRemainingWeightCapacity(floor.getRemainingWeightCapacity()-parking.getCarWeight());
        floor.getParkings().add(parking);
        parking.setFloor(floor);
        parking = parkingRepository.save(parking);
        return parkingManager.convertEntityToDto(parking, floorManager.convertEnityToDto(floor));
    }

    @Override
    public ParkingDto getParking(Integer id) throws Exception {
        List<Parking> parkings = parkingRepository.findByIdIn(Arrays.asList(id));
        parkingManager.isNullOrEmpty(parkings);
        Parking parking = parkings.get(0);
        parkingManager.isNull(parking);
        return parkingManager.convertEntityToDto(parking, floorManager.convertEnityToDto(parking.getFloor()));
    }

    @Override
    public ParkingDto updateParking(Integer id, String status) throws Exception {
        parkingManager.validStatus(status);
        List<Parking> parkings = parkingRepository.findByIdIn(Arrays.asList(id));
        parkingManager.isNullOrEmpty(parkings);
        Parking parking = parkings.get(0);
        parkingManager.isNull(parking);
        parking = checkoutTemplate.checkOutParking(parking);
        return parkingManager.convertEntityToDto(parking, floorManager.convertEnityToDto(parking.getFloor()));
    }
}
