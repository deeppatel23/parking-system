package com.example.parking.system.repository;

import com.example.parking.system.models.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {

    ParkingSlot findByName(String name);

    ParkingSlot findByUsername(String username);

    List<ParkingSlot> findByFloor(String floor);

    @Query(value = "SELECT * FROM PARKING_SLOT WHERE USERNAME is NULL",
            nativeQuery = true)
    List<ParkingSlot> findEmpyParkingSlot();
}
