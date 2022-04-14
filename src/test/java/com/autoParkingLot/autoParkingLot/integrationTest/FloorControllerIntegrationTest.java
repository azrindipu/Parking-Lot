package com.autoParkingLot.autoParkingLot.integrationTest;

import com.autoParkingLot.autoParkingLot.dto.FloorDto;
import com.autoParkingLot.autoParkingLot.entity.Floor;
import com.autoParkingLot.autoParkingLot.manager.FloorManager;
import com.autoParkingLot.autoParkingLot.repository.FloorRepository;
import com.autoParkingLot.autoParkingLot.utils.ApiEndpoints;
import com.autoParkingLot.autoParkingLot.utils.Converter;
import com.autoParkingLot.autoParkingLot.utils.ObjectCreator;
import com.autoParkingLot.autoParkingLot.utils.TestContainerSqlDB;
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
public class FloorControllerIntegrationTest extends TestContainerSqlDB {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private FloorManager floorManager;

    @Test
    public void createFloor_test() throws Exception {

        FloorDto floorDto = ObjectCreator.getFloorDto();
        String jsonString = Converter.convertDtoToJsonString(floorDto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(ApiEndpoints.FLOOR_POST_API)
                .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Floor response = Converter.convertMockMVCToFloorEntity(result);

        Assert.assertEquals(floorDto.getTotalWeightCapacity(), response.getTotalWeightCapacity());
        Assert.assertEquals(floorDto.getFloorNumber(), response.getFloorNumber());
        Assert.assertEquals(floorDto.getCeilingHeight(), response.getCeilingHeight());
    }

    @Test
    public void getFloor_test() throws Exception {

        Floor floor = ObjectCreator.getFloor();
        floorRepository.save(floor);
        List<Floor> floors = floorRepository.findAll();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(ApiEndpoints.FLOOR_GET_API + floors.get(0).getFloorNumber())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Floor response = Converter.convertMockMVCToFloorEntity(result);

        Assert.assertEquals(floor.getTotalWeightCapacity(), response.getTotalWeightCapacity());
        Assert.assertEquals(floor.getFloorNumber(), response.getFloorNumber());
        Assert.assertEquals(floor.getCeilingHeight(), response.getCeilingHeight());
    }

    @Test
    public void updateFloor_test() throws Exception {

        floorRepository.save(ObjectCreator.getFloor());
        FloorDto floorDto = floorManager.convertEnityToDto(floorRepository.findAll().get(0));
        floorDto.setCeilingHeight(10000l);
        floorDto.setRemainingWeightCapacity(1000l);
        String jsonString = Converter.convertDtoToJsonString(floorDto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(ApiEndpoints.FLOOR_UPDATE_API)
                .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Floor response = Converter.convertMockMVCToFloorEntity(result);

        Assert.assertEquals(floorDto.getTotalWeightCapacity(), response.getTotalWeightCapacity());
        Assert.assertEquals(floorDto.getFloorNumber(), response.getFloorNumber());
        Assert.assertEquals(floorDto.getCeilingHeight(), response.getCeilingHeight());
    }
}
