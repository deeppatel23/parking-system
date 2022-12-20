package com.example.parking.system.services;


import com.example.parking.system.models.Vehicle;
import org.springframework.http.ResponseEntity;

public interface VehicleService {
    ResponseEntity<?> addVehicle(Vehicle vehicle);

    ResponseEntity<?> findVehicleByUser(String username);

    ResponseEntity<?> findVehicleByRegisterationNumber(String registerationNumber);
}
