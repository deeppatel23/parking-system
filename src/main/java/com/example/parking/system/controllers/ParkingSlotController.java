package com.example.parking.system.controllers;

import com.example.parking.system.models.ParkingSlot;
import com.example.parking.system.payload.request.ParkingRequest;
import com.example.parking.system.repository.ParkingSlotRepository;
import com.example.parking.system.services.ParkingSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/parking")
public class ParkingSlotController {

    @Autowired
    private ParkingSlotService parkingSlotService;

    @PostMapping("/add")
    public ResponseEntity<?> addParkingSlot(@Valid @RequestBody ParkingSlot parkingSlot) {
        return parkingSlotService.addParkingSlot(parkingSlot);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> findParkingSlotByName(@PathVariable("name") String name) {
        return parkingSlotService.findParkingSlotByName(name);
    }

    @GetMapping("/floor/{floor}")
    public ResponseEntity<?> findParkingSlotByFloor(@PathVariable("floor") String floor) {
        return parkingSlotService.findParkingSlotByFloor(floor);
    }

    @PostMapping("/park")
    public ResponseEntity<?> allocateParkingSlotToUser(@Valid @RequestBody ParkingRequest parkingRequest) {
        return parkingSlotService.allocateParkingSlotToUser(parkingRequest);
    }

    @PostMapping("/unpark/{parkingSlotName}")
    public ResponseEntity<?> deallocateParkingSlotToUser(@PathVariable("parkingSlotName") String parkingSlotName) {
        return parkingSlotService.deallocateParkingSlotToUser(parkingSlotName);
    }

    @GetMapping("/empty")
    public ResponseEntity<?> findEmptyParkingSlot() {
        return parkingSlotService.findEmptyParkingSlot();
    }
}
