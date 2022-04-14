package com.autoParkingLot.autoParkingLot.unitTest;

import com.autoParkingLot.autoParkingLot.dto.Car;
import com.autoParkingLot.autoParkingLot.dto.FloorDto;
import com.autoParkingLot.autoParkingLot.entity.Floor;
import com.autoParkingLot.autoParkingLot.manager.FloorManager;
import com.autoParkingLot.autoParkingLot.repository.FloorRepository;
import com.autoParkingLot.autoParkingLot.service.FloorServiceImpl;
import com.autoParkingLot.autoParkingLot.utils.ObjectCreator;
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
public class FloorServiceImplTest {

    @InjectMocks
    private FloorServiceImpl floorService;

    @Mock
    private FloorRepository floorRepository;

    @Mock
    private FloorManager floorManager;

    @Test
    public void createFloor_test() throws Exception {
        FloorDto floorDto = ObjectCreator.getFloorDto();
        Floor floor = ObjectCreator.getFloor();

        //mock
        doNothing().when(floorManager).validateFloorDto(floorDto);
        when(floorManager.convertDtoToEntity(floorDto)).thenReturn(floor);
        when(floorRepository.save(floor)).thenReturn(floor);
        when(floorManager.convertEnityToDto(floor)).thenReturn(floorDto);

        FloorDto response = floorService.createFloor(floorDto);

        //function call verification
        verify(floorManager, times(1)).validateFloorDto(floorDto);
        verify(floorManager, times(1)).convertDtoToEntity(floorDto);
        verify(floorRepository, times(1)).save(floor);
        verify(floorManager, times(1)).convertEnityToDto(floor);

        //assertion
        Assert.assertEquals(floorDto.getFloorNumber(), response.getFloorNumber());
        Assert.assertEquals(floorDto.getCeilingHeight(), response.getCeilingHeight());
        Assert.assertEquals(floorDto.getTotalWeightCapacity(), response.getTotalWeightCapacity());
    }

    @Test
    public void getFloor_test() throws Exception {
        FloorDto floorDto = ObjectCreator.getFloorDto();
        floorDto.setId(1);
        Floor floor = ObjectCreator.getFloor();

        //mock
        doNothing().when(floorManager).validFloorNumber(floorDto.getId());
        when(floorRepository.findByFloorNumber(floorDto.getId())).thenReturn(floor);
        doNothing().when(floorManager).isFloorFound(floor);
        when(floorManager.convertEnityToDto(floor)).thenReturn(floorDto);

        FloorDto response = floorService.getFloor(floorDto.getId());

        //function call verification
        verify(floorManager, times(1)).validFloorNumber(floorDto.getId());
        verify(floorRepository, times(1)).findByFloorNumber(floorDto.getId());
        verify(floorManager, times(1)).isFloorFound(floor);
        verify(floorManager, times(1)).convertEnityToDto(floor);

        //assertion
        Assert.assertEquals(floorDto.getFloorNumber(), response.getFloorNumber());
        Assert.assertEquals(floorDto.getCeilingHeight(), response.getCeilingHeight());
        Assert.assertEquals(floorDto.getTotalWeightCapacity(), response.getTotalWeightCapacity());
    }

    @Test
    public void updateFloor_test() throws Exception {
        FloorDto floorDto = ObjectCreator.getFloorDto();
        floorDto.setId(1);
        Floor floor = ObjectCreator.getFloor();
        floor.setId(1);

        //mock
        doNothing().when(floorManager).validateFloorDto(floorDto);
        when(floorRepository.findByFloorNumber(any())).thenReturn(floor);
        doNothing().when(floorManager).isFloorFound(floor);
        when(floorManager.convertDtoToEntity(floorDto)).thenReturn(floor);
        when(floorRepository.save(floor)).thenReturn(floor);
        when(floorManager.convertEnityToDto(floor)).thenReturn(floorDto);

        FloorDto response = floorService.updateFloor(floorDto);

        //function call verification
        verify(floorManager, times(1)).validateFloorDto(floorDto);
        verify(floorRepository, times(1)).findByFloorNumber(any());
        verify(floorManager, times(1)).isFloorFound(floor);
        verify(floorManager, times(1)).convertDtoToEntity(floorDto);
        verify(floorRepository, times(1)).save(floor);
        verify(floorManager, times(1)).convertEnityToDto(floor);

        //assertion
        Assert.assertEquals(floorDto.getFloorNumber(), response.getFloorNumber());
        Assert.assertEquals(floorDto.getCeilingHeight(), response.getCeilingHeight());
        Assert.assertEquals(floorDto.getTotalWeightCapacity(), response.getTotalWeightCapacity());
    }

    @Test
    public void getFloorByCapacity_test() throws Exception {
        FloorDto floorDto = ObjectCreator.getFloorDto();
        floorDto.setId(1);
        Floor floor = ObjectCreator.getFloor();
        floor.setId(1);
        List<Floor> floors = new ArrayList<>();
        floors.add(floor);
        Car car = ObjectCreator.getCar();


        //mock
        when(floorRepository.findByCeilingHeightAndRemainingWeightCapacityGreaterThan(car.getCarHeight(),car.getCarWeight())).thenReturn(floors);
        doNothing().when(floorManager).isFloorFound(floor);
        doNothing().when(floorManager).isFloorFound(floors);
        when(floorManager.convertEnityToDto(floors.get(0))).thenReturn(floorDto);

        FloorDto response = floorService.getFloorByCapacity(car);

        //function call verification
        verify(floorRepository, times(1)).findByCeilingHeightAndRemainingWeightCapacityGreaterThan(car.getCarHeight(),car.getCarWeight());
        verify(floorManager, times(1)).isFloorFound(floor);
        verify(floorManager, times(1)).isFloorFound(floors);
        verify(floorManager, times(1)).convertEnityToDto(floor);

        //assertion
        Assert.assertEquals(floorDto.getFloorNumber(), response.getFloorNumber());
        Assert.assertEquals(floorDto.getCeilingHeight(), response.getCeilingHeight());
        Assert.assertEquals(floorDto.getTotalWeightCapacity(), response.getTotalWeightCapacity());
    }

    @Test
    public void getFloorById_test() throws Exception {
        Floor floor = ObjectCreator.getFloor();
        floor.setId(1);

        //mock
        when(floorRepository.findByIdIn(Arrays.asList(floor.getId()))).thenReturn(floor);

        Floor response = floorService.getFloorById(floor.getId());

        //function call verification
        verify(floorRepository, times(1)).findByIdIn(Arrays.asList(floor.getId()));

        //assertion
        Assert.assertEquals(floor.getFloorNumber(), response.getFloorNumber());
        Assert.assertEquals(floor.getCeilingHeight(), response.getCeilingHeight());
        Assert.assertEquals(floor.getTotalWeightCapacity(), response.getTotalWeightCapacity());
    }


}
