package com.autoParkingLot.autoParkingLot.integrationTest;

import com.autoParkingLot.autoParkingLot.dto.Car;
import com.autoParkingLot.autoParkingLot.entity.Floor;
import com.autoParkingLot.autoParkingLot.entity.Parking;
import com.autoParkingLot.autoParkingLot.repository.FloorRepository;
import com.autoParkingLot.autoParkingLot.repository.ParkingRepository;
import com.autoParkingLot.autoParkingLot.utils.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class ParkingControllerIntegrationTest extends TestContainerSqlDB {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private FloorRepository floorRepository;

    @Test
    public void parkNewCar_test() throws Exception {

        floorRepository.save(ObjectCreator.getFloor());
        List<Floor> floors = floorRepository.findAll();
        Car car = ObjectCreator.getCar();
        car.setCarHeight((long) ObjectCreator.generateRandomValue(0, floors.get(0).getCeilingHeight().intValue()));
        car.setCarWeight((long) ObjectCreator.generateRandomValue(0, floors.get(0).getRemainingWeightCapacity().intValue()));
        String jsonString = Converter.convertDtoToJsonString(car);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(ApiEndpoints.PARKING_POST_API)
                .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Parking response = Converter.convertMockMVCToParkingEntity(result);

        Assert.assertEquals(car.getCarHeight(), response.getCarHeight());
        Assert.assertEquals(car.getCarWeight(), response.getCarWeight());
        Assert.assertEquals(car.getCarType(), response.getCarType());
    }

    @Test
    public void getParking_test() throws Exception {

        Parking parking =getParking();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(ApiEndpoints.PARKING_GET_API + parking.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Parking response = Converter.convertMockMVCToParkingEntity(result);

        Assert.assertEquals(parking.getCarHeight(), response.getCarHeight());
        Assert.assertEquals(parking.getCarWeight(), response.getCarWeight());
        Assert.assertEquals(parking.getCarType(), response.getCarType());
    }

    @Test
    public void updateParking_test() throws Exception {

        Parking parking =getParking();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(ApiEndpoints.PARKING_UPDATE_API + parking.getId())
                .param("status", Status.CHECKOUT.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Parking response = Converter.convertMockMVCToParkingEntity(result);

        Assert.assertEquals(parking.getCarHeight(), response.getCarHeight());
        Assert.assertEquals(parking.getCarWeight(), response.getCarWeight());
        Assert.assertEquals(parking.getCarType(), response.getCarType());
        Assert.assertNotNull(response.getCheckoutTime());
    }

    private Parking getParking(){
        floorRepository.save(ObjectCreator.getFloor());
        List<Floor> floors = floorRepository.findAll();
        Parking parking = ObjectCreator.getParking();
        parking.setFloor(floors.get(0));
        parkingRepository.save(parking);
        List<Parking> parkings = parkingRepository.findAll();
        return parkings.get(0);
    }
}
