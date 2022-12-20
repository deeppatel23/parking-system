package com.example.parking.system.repository;

import com.example.parking.system.models.Vehicle;
import com.example.parking.system.services.VehicleService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query(value = "SELECT * FROM VEHICLE WHERE REGISTERATION_NUMBER = ?1",
            nativeQuery = true)
    Optional<Vehicle> findByRegisterationNumber(String registerationNumber);
    List<Vehicle> findByUsername(String username);
}
