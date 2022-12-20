package com.example.parking.system.services;

import com.example.parking.system.models.ParkingSlot;
import com.example.parking.system.payload.request.ParkingRequest;
import com.example.parking.system.payload.response.MessageResponse;
import com.example.parking.system.repository.ParkingSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingSlotServiceImpl implements ParkingSlotService{

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @Override
    public ResponseEntity<?> addParkingSlot(ParkingSlot parkingSlot) {
        if (parkingSlotRepository.findByName(parkingSlot.getName()) != null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Parking Slot exist!"));
        }
        parkingSlotRepository.save(parkingSlot);
        return ResponseEntity.ok(new MessageResponse("Parking Slot added successfully!"));
    }

    @Override
    public ResponseEntity<?> findParkingSlotByName(String name) {
        ParkingSlot parkingSlot = parkingSlotRepository.findByName(name);

        return ResponseEntity.ok(parkingSlot);
    }

    @Override
    public ResponseEntity<?> findParkingSlotByFloor(String floor) {
        List<ParkingSlot> allParkingSlotOnFloor = parkingSlotRepository.findByFloor(floor);

        return ResponseEntity.ok(allParkingSlotOnFloor);
    }

    @Override
    public ResponseEntity<?> allocateParkingSlotToUser(ParkingRequest parkingRequest) {
        ParkingSlot parkingSlot = parkingSlotRepository.findByName(parkingRequest.getName());
        if (parkingSlot.getUsername() != null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Parking Slot already occupied"));
        }

        parkingSlot.setUsername(parkingRequest.getUsername());
        parkingSlot.setVehicleRegisterationNumber(parkingRequest.getVehicleRegisterationNumber());
        parkingSlotRepository.save(parkingSlot);
        return ResponseEntity.ok(new MessageResponse("Parking Slot assigned to !" + parkingRequest.getUsername()));
    }

    @Override
    public ResponseEntity<?> deallocateParkingSlotToUser(String username) {
        ParkingSlot parkingSlot = parkingSlotRepository.findByUsername(username);
        if (parkingSlot.getUsername() == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Parking Slot already empty"));
        }

        parkingSlot.setUsername(null);
        parkingSlotRepository.save(parkingSlot);
        return ResponseEntity.ok(new MessageResponse("Parking Slot now free!"));
    }

    @Override
    public ResponseEntity<?> findEmptyParkingSlot() {
        List<ParkingSlot> parkingSlots = parkingSlotRepository.findEmpyParkingSlot();

        if (parkingSlots.isEmpty()) {
            return ResponseEntity.ok("No free parking slots available");
        }
        return ResponseEntity.ok(parkingSlots);
    }
}
