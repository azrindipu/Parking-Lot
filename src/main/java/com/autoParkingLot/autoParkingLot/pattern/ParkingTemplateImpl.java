package com.autoParkingLot.autoParkingLot.pattern;

import com.autoParkingLot.autoParkingLot.dto.Car;
import com.autoParkingLot.autoParkingLot.dto.FloorDto;
import com.autoParkingLot.autoParkingLot.dto.ParkingDto;
import com.autoParkingLot.autoParkingLot.pattern.entryGateSelectionStrategy.EntryGate;
import com.autoParkingLot.autoParkingLot.pattern.entryGateSelectionStrategy.EntryGateSelection;
import com.autoParkingLot.autoParkingLot.pattern.entryGateSelectionStrategy.GateSelectionStrategy;
import com.autoParkingLot.autoParkingLot.pattern.priceGeneratorStrategy.GeneratePrice;
import com.autoParkingLot.autoParkingLot.pattern.priceGeneratorStrategy.GeneratePricePerMinute;
import com.autoParkingLot.autoParkingLot.pattern.priceGeneratorStrategy.GeneratePricePerMinuteStrategy;
import com.autoParkingLot.autoParkingLot.pattern.scanStrategy.CarScanner;
import com.autoParkingLot.autoParkingLot.pattern.scanStrategy.ScanCar;
import com.autoParkingLot.autoParkingLot.pattern.scanStrategy.ScanTrategy;
import com.autoParkingLot.autoParkingLot.pattern.transformStrategy.CarTranform;
import com.autoParkingLot.autoParkingLot.pattern.transformStrategy.TransFormStrategy;
import com.autoParkingLot.autoParkingLot.pattern.transformStrategy.Transform;
import com.autoParkingLot.autoParkingLot.service.interfaces.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ParkingTemplateImpl extends ParkingTemplate {

    @Autowired
    private FloorService floorService;

    private ScanCar scanCar;
    private GeneratePricePerMinute generatePricePerMinute;
    private EntryGate entryGate;
    private Transform transform;

    @PostConstruct
    public void initializations(){
        scanCar = new CarScanner(new ScanTrategy(floorService));
        entryGate = new EntryGateSelection(new GateSelectionStrategy());
        transform = new CarTranform(new TransFormStrategy());
        generatePricePerMinute = new GeneratePrice(new GeneratePricePerMinuteStrategy());
    }

    @Override
    public FloorDto scanCar(Car car) throws Exception {
        return scanCar.scanCarAndAssignFloor(car);
    }

    @Override
    public void generatePricePerMinuteForParking(Car car, ParkingDto parkingDto) throws Exception{
        generatePricePerMinute.generatePrive(car, parkingDto);
    }

    @Override
    public void getAppropritaeEntryPoint(Car car, ParkingDto parkingDto) throws Exception{
        entryGate.entryGateSelection(car, parkingDto);
    }

    @Override
    public void transportCarToParkingPlace(ParkingDto parkingDto) throws Exception{
        this.transform.transForm(parkingDto);
    }
}
