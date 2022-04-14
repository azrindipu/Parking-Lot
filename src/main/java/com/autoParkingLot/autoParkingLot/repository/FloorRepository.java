package com.autoParkingLot.autoParkingLot.repository;

import com.autoParkingLot.autoParkingLot.entity.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Integer>{
    Floor findByFloorNumber(Integer floorNumber);
    Floor findByIdIn(List<Integer> id);

    @Query("SELECT floor FROM Floor floor where floor.ceilingHeight>=:carHeigh and floor.remainingWeightCapacity>=:carWeight")
    List<Floor> findByCeilingHeightAndRemainingWeightCapacityGreaterThan(@Param("carHeigh") long carHeigh, @Param("carWeight") long carWeight);
}
