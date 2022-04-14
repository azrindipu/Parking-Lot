package com.autoParkingLot.autoParkingLot.pattern;

import com.autoParkingLot.autoParkingLot.entity.Floor;
import com.autoParkingLot.autoParkingLot.entity.Parking;
import com.autoParkingLot.autoParkingLot.pattern.adapter.AdapterFactory;
import com.autoParkingLot.autoParkingLot.pattern.adapter.Target;
import com.autoParkingLot.autoParkingLot.pattern.calculatePricestrategy.CalculatePrice;
import com.autoParkingLot.autoParkingLot.pattern.calculatePricestrategy.CalculatePriceStrategy;
import com.autoParkingLot.autoParkingLot.pattern.calculatePricestrategy.CalulateCheckoutPrice;
import com.autoParkingLot.autoParkingLot.repository.FloorRepository;
import com.autoParkingLot.autoParkingLot.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CheckoutTemplateImpl extends CheckoutTemplate {

    @Autowired
    private AdapterFactory adapterFactory;

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private FloorRepository floorRepository;

    private Target target;
    private CalculatePrice calculatePrice;

    @PostConstruct
    public void initialization(){
        target = adapterFactory.getTarget();
        calculatePrice = new CalulateCheckoutPrice(new CalculatePriceStrategy());
    }

    @Override
    public void makePayment(Parking parking) throws Exception {
        this.target.request(parking);
    }

    @Override
    public void updateParking(Parking parking) throws Exception {
        calculatePrice.calculateAndAddPrice(parking);
        parkingRepository.save(parking);
    }

    @Override
    public void updateFloor(Parking parking) throws Exception {
        Floor floor = parking.getFloor();
        floor.setRemainingWeightCapacity(floor.getRemainingWeightCapacity()+parking.getCarWeight());
        floorRepository.save(floor);
    }
}
