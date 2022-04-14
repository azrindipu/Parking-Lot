package com.autoParkingLot.autoParkingLot.repository;

import com.autoParkingLot.autoParkingLot.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingRepository extends JpaRepository<Parking, Integer> {
    List<Parking> findByIdIn(List<Integer> ids);
}
