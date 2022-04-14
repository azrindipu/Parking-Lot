package com.autoParkingLot.autoParkingLot.pattern.adapter;

import com.autoParkingLot.autoParkingLot.entity.Parking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdapteeImpl implements Adaptee {
    private static Logger logger = LoggerFactory.getLogger(AdapteeImpl.class);
    @Override
    public void specificRequestForPayment(Parking parking) throws Exception {
        logger.info("Payment with third party is done");
    }
}
