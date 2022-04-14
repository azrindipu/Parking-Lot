package com.autoParkingLot.autoParkingLot.unitTest;

import com.autoParkingLot.autoParkingLot.dto.Car;
import com.autoParkingLot.autoParkingLot.dto.FloorDto;
import com.autoParkingLot.autoParkingLot.dto.ParkingDto;
import com.autoParkingLot.autoParkingLot.entity.Floor;
import com.autoParkingLot.autoParkingLot.entity.Parking;
import com.autoParkingLot.autoParkingLot.manager.FloorManager;
import com.autoParkingLot.autoParkingLot.manager.ParkingManager;
import com.autoParkingLot.autoParkingLot.pattern.CheckoutTemplate;
import com.autoParkingLot.autoParkingLot.pattern.ParkingTemplate;
import com.autoParkingLot.autoParkingLot.repository.ParkingRepository;
import com.autoParkingLot.autoParkingLot.service.ParkingServiceImpl;
import com.autoParkingLot.autoParkingLot.service.interfaces.FloorService;
import com.autoParkingLot.autoParkingLot.utils.ObjectCreator;
import com.autoParkingLot.autoParkingLot.utils.Status;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class ParkingServiceImplTest {

    @InjectMocks
    private ParkingServiceImpl parkingService;

    @Mock
    private ParkingTemplate parkingTemplate;

    @Mock
    private CheckoutTemplate checkoutTemplate;

    @Mock
    private ParkingManager parkingManager;

    @Mock
    private ParkingRepository parkingRepository;

    @Mock
    private FloorService floorService;

    @Mock
    private FloorManager floorManager;

    @Test
    public void createNewParking_test() throws Exception {
        Car car = ObjectCreator.getCar();
        ParkingDto parkingDto = ObjectCreator.getParkingDto();
        Parking parking = ObjectCreator.getParking();
        Floor floor = ObjectCreator.getFloor();
        FloorDto floorDto = ObjectCreator.getFloorDto();
        parking.setFloor(floor);
        parkingDto.setFloorDto(floorDto);

        when(parkingTemplate.createNewParking(car)).thenReturn(parkingDto);
        doNothing().when(parkingManager).dtoIsNull(parkingDto);
        when(parkingManager.convertDtoToEntity(parkingDto)).thenReturn(parking);
        when(floorService.getFloorById(parkingDto.getFloorDto().getId())).thenReturn(floor);
        when(parkingRepository.save(parking)).thenReturn(parking);
        when(floorManager.convertEnityToDto(floor)).thenReturn(floorDto);
        when(parkingManager.convertEntityToDto(parking, floorDto)).thenReturn(parkingDto);

        ParkingDto response = parkingService.createNewParking(car);

        verify(parkingTemplate, times(1)).createNewParking(car);
        verify(parkingManager, times(1)).convertDtoToEntity(parkingDto);
        verify(floorService, times(1)).getFloorById(parkingDto.getFloorDto().getId());
        verify(parkingRepository, times(1)).save(parking);
        verify(floorManager, times(1)).convertEnityToDto(floor);
        verify(parkingManager, times(1)).convertEntityToDto(parking, floorDto);

        Assert.assertEquals(parkingDto.getCarHeight(), response.getCarHeight());
        Assert.assertEquals(parkingDto.getCarType(), response.getCarType());
        Assert.assertEquals(parkingDto.getCarWeight(), response.getCarWeight());
    }

    @Test
    public void getParking_test() throws Exception {
        ParkingDto parkingDto = ObjectCreator.getParkingDto();
        Parking parking = ObjectCreator.getParking();
        Floor floor = ObjectCreator.getFloor();
        FloorDto floorDto = ObjectCreator.getFloorDto();
        parking.setFloor(floor);
        parkingDto.setFloorDto(floorDto);
        List<Parking> parkings = new ArrayList<>();
        parkings.add(parking);

        when(parkingRepository.findByIdIn(Arrays.asList(1))).thenReturn(parkings);
        doNothing().when(parkingManager).isNullOrEmpty(parkings);
        doNothing().when(parkingManager).isNull(parkings.get(0));
        when(floorManager.convertEnityToDto(floor)).thenReturn(floorDto);
        when(parkingManager.convertEntityToDto(parking, floorDto)).thenReturn(parkingDto);


        ParkingDto response = parkingService.getParking(1);

        verify(parkingRepository, times(1)).findByIdIn(Arrays.asList(1));
        verify(parkingManager, times(1)).isNullOrEmpty(parkings);
        verify(parkingManager, times(1)).isNull(parkings.get(0));
        verify(floorManager, times(1)).convertEnityToDto(floor);
        verify(parkingManager, times(1)).convertEntityToDto(parking, floorDto);

        Assert.assertEquals(parkingDto.getCarHeight(), response.getCarHeight());
        Assert.assertEquals(parkingDto.getCarType(), response.getCarType());
        Assert.assertEquals(parkingDto.getCarWeight(), response.getCarWeight());
    }

    @Test
    public void updateParking_test() throws Exception {
        ParkingDto parkingDto = ObjectCreator.getParkingDto();
        Parking parking = ObjectCreator.getParking();
        Floor floor = ObjectCreator.getFloor();
        FloorDto floorDto = ObjectCreator.getFloorDto();
        parking.setFloor(floor);
        parkingDto.setFloorDto(floorDto);
        List<Parking> parkings = new ArrayList<>();
        parkings.add(parking);

        doNothing().when(parkingManager).validStatus(Status.CHECKOUT.name());
        when(parkingRepository.findByIdIn(Arrays.asList(1))).thenReturn(parkings);
        doNothing().when(parkingManager).isNullOrEmpty(parkings);
        doNothing().when(parkingManager).isNull(parkings.get(0));
        when(checkoutTemplate.checkOutParking(parking)).thenReturn(parking);
        when(floorManager.convertEnityToDto(floor)).thenReturn(floorDto);
        when(parkingManager.convertEntityToDto(parking, floorDto)).thenReturn(parkingDto);


        ParkingDto response = parkingService.updateParking(1, Status.CHECKOUT.toString());

        verify(parkingManager, times(1)).validStatus(Status.CHECKOUT.toString());
        verify(parkingRepository, times(1)).findByIdIn(Arrays.asList(1));
        verify(parkingManager, times(1)).isNullOrEmpty(parkings);
        verify(parkingManager, times(1)).isNull(parkings.get(0));
        verify(checkoutTemplate, times(1)).checkOutParking(parking);
        verify(floorManager, times(1)).convertEnityToDto(floor);
        verify(parkingManager, times(1)).convertEntityToDto(parking, floorDto);

        Assert.assertEquals(parkingDto.getCarHeight(), response.getCarHeight());
        Assert.assertEquals(parkingDto.getCarType(), response.getCarType());
        Assert.assertEquals(parkingDto.getCarWeight(), response.getCarWeight());
    }
}
