package com.example.parking.system.services;

import com.example.parking.system.models.Vehicle;
import com.example.parking.system.payload.response.MessageResponse;
import com.example.parking.system.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public ResponseEntity<?> addVehicle(Vehicle vehicle) {
        if (vehicleRepository.findByRegisterationNumber(vehicle.getRegisterationNumber()).isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Vehicle is already registered!"));
        }

        vehicleRepository.save(vehicle);
        return ResponseEntity.ok(new MessageResponse("Vehicle added successfully!"));
    }

    @Override
    public ResponseEntity<?> findVehicleByUser(String username) {
        List<Vehicle> allVehicles = vehicleRepository.findByUsername(username);

        if (allVehicles.isEmpty()) {
            return ResponseEntity.ok("User owns no vehicle");
        }

        return ResponseEntity.ok(allVehicles);
    }

    @Override
    public ResponseEntity<?> findVehicleByRegisterationNumber(String registerationNumber) {
        Optional<Vehicle> vehicle = vehicleRepository.findByRegisterationNumber(registerationNumber);

        return ResponseEntity.ok(vehicle);
    }
}
