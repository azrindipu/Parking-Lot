package com.autoParkingLot.autoParkingLot.controller;

import com.autoParkingLot.autoParkingLot.dto.FloorDto;
import com.autoParkingLot.autoParkingLot.service.interfaces.FloorService;
import com.autoParkingLot.autoParkingLot.utils.ResponseGenerator;
import io.swagger.v3.oas.annotations.Parameter;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/floor")
public class FloorController {
    private static Logger logger = LoggerFactory.getLogger(FloorController.class);

    @Autowired
    private FloorService floorService;

    @PostMapping(value = "/")
    public ResponseEntity<JSONObject> createFloor(@RequestBody FloorDto floorDto) throws Exception {
        return ResponseEntity.ok(ResponseGenerator.generateResponse(floorService.createFloor(floorDto)));
    }

    @GetMapping(value = "/{floorNumber}")
    public ResponseEntity<JSONObject> getFloor(@Parameter(name = "floorNumber", required = true, description = "floor Number")
                                               @PathVariable Integer floorNumber) throws Exception {
        return ResponseEntity.ok(ResponseGenerator.generateResponse(floorService.getFloor(floorNumber)));
    }

    @PutMapping(value = "/")
    public ResponseEntity<JSONObject> updateFloor(@RequestBody FloorDto floorDto) throws Exception {
        return ResponseEntity.ok(ResponseGenerator.generateResponse(floorService.updateFloor(floorDto)));
    }
}
