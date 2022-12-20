package com.example.parking.system.services;

import com.example.parking.system.models.ParkingSlot;
import com.example.parking.system.payload.request.ParkingRequest;
import org.springframework.http.ResponseEntity;

public interface ParkingSlotService {
    ResponseEntity<?> addParkingSlot(ParkingSlot parkingSlot);

    ResponseEntity<?> findParkingSlotByName(String name);

    ResponseEntity<?> findParkingSlotByFloor(String floor);

    ResponseEntity<?> allocateParkingSlotToUser(ParkingRequest parkingRequest);

    ResponseEntity<?> deallocateParkingSlotToUser(String username);

    ResponseEntity<?> findEmptyParkingSlot();
}
