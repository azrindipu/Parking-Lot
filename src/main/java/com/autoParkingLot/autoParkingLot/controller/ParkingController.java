package com.autoParkingLot.autoParkingLot.controller;

import com.autoParkingLot.autoParkingLot.dto.Car;
import com.autoParkingLot.autoParkingLot.service.interfaces.ParkingService;
import com.autoParkingLot.autoParkingLot.utils.ResponseGenerator;
import io.swagger.v3.oas.annotations.Parameter;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/parking")
public class ParkingController {
    private static Logger logger = LoggerFactory.getLogger(ParkingController.class);

    @Autowired
    private ParkingService parkingService;

    @PostMapping(value = "/")
    public ResponseEntity<JSONObject> parkNewCar(@RequestBody Car car) throws Exception {
        return ResponseEntity.ok(ResponseGenerator.generateResponse(parkingService.createNewParking(car)));
    }

    @GetMapping(value = "/{parkingId}")
    public ResponseEntity<JSONObject> getParking(@Parameter(name = "parkingId", description = "Parking ID")
                                                 @PathVariable Integer parkingId) throws Exception {
        return ResponseEntity.ok(ResponseGenerator.generateResponse(parkingService.getParking(parkingId)));
    }

    @PutMapping(value = "/{parkingId}")
    public ResponseEntity<JSONObject> updateParking(@Parameter(name = "parkingId", description = "Parking ID")
                                                    @PathVariable Integer parkingId,
                                                    @Parameter(name = "status", description = "checkout")
                                                    @RequestParam String status) throws Exception {
        return ResponseEntity.ok(ResponseGenerator.generateResponse(parkingService.updateParking(parkingId, status)));
    }

}
