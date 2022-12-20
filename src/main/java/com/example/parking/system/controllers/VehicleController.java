package com.example.parking.system.controllers;

import com.example.parking.system.models.Vehicle;
import com.example.parking.system.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/add")
    public ResponseEntity<?> addVehicle(@Valid @RequestBody Vehicle vehicle) {
        return vehicleService.addVehicle(vehicle);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?> findVehicleByUser(@PathVariable("username") String username) {
        return vehicleService.findVehicleByUser(username);
    }

    @GetMapping("/regnum/{registerationNumber}")
    public ResponseEntity<?> findVehicleByRegisterationNumber(@PathVariable("registerationNumber") String registerationNumber) {
        return vehicleService.findVehicleByRegisterationNumber(registerationNumber);
    }
}
