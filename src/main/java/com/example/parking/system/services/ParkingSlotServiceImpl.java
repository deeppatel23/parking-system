package com.example.parking.system.services;

import com.example.parking.system.models.History;
import com.example.parking.system.models.ParkingSlot;
import com.example.parking.system.models.Vehicle;
import com.example.parking.system.payload.request.ParkingRequest;
import com.example.parking.system.payload.response.MessageResponse;
import com.example.parking.system.repository.HistoryRepository;
import com.example.parking.system.repository.ParkingSlotRepository;
import com.example.parking.system.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingSlotServiceImpl implements ParkingSlotService{

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public ResponseEntity<?> addParkingSlot(ParkingSlot parkingSlot) {
        Optional<ParkingSlot> parkingSlot1 = parkingSlotRepository.findByName(parkingSlot.getName());
        if (parkingSlot1.isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Parking Slot exist!"));
        }
        parkingSlotRepository.save(parkingSlot);
        return ResponseEntity.ok(new MessageResponse("Parking Slot added successfully!"));
    }

    @Override
    public ResponseEntity<?> findParkingSlotByName(String name) {
        Optional<ParkingSlot> parkingSlot = parkingSlotRepository.findByName(name);
        if (parkingSlot.isPresent()) {
            return ResponseEntity.ok(parkingSlot);
        }

        return ResponseEntity.ok("No parking slot present with name: " + name);
    }

    @Override
    public ResponseEntity<?> findParkingSlotByFloor(String floor) {
        List<ParkingSlot> allParkingSlotOnFloor = parkingSlotRepository.findByFloor(floor);

        return ResponseEntity.ok(allParkingSlotOnFloor);
    }

    @Override
    public ResponseEntity<?> allocateParkingSlotToUser(ParkingRequest parkingRequest) {
        Optional<ParkingSlot> parkingSlot = parkingSlotRepository.findById(parkingRequest.getId());
        if (parkingSlot.isPresent()) {
            ParkingSlot parkingSlot1 = parkingSlot.get();

            if (parkingSlot1.getUsername() != null) {
                return ResponseEntity.badRequest().body(new MessageResponse("Parking Slot already occupied"));
            }

            Optional<Vehicle> vehicle = vehicleRepository.findByRegisterationNumber(parkingRequest.getVehicleRegisterationNumber());
            if (vehicle.isPresent()) {
                Vehicle vehicle1 = vehicle.get();
                System.out.println(vehicle1.getUsername());
                if (parkingSlot1.getSize() != vehicle1.getSize()) {
                    return ResponseEntity.badRequest().body(new MessageResponse("Vehicle size and slot size must be same"));
                }
            }
            else {
                ResponseEntity.ok("No such vehicle exist!");
            }

            parkingSlot1.setUsername(parkingRequest.getUsername());
            parkingSlot1.setVehicleRegisterationNumber(parkingRequest.getVehicleRegisterationNumber());
            parkingSlot1.setEntryTime(LocalDateTime.now());
            parkingSlotRepository.save(parkingSlot1);
            return ResponseEntity.ok(new MessageResponse("Parking Slot assigned to !" + parkingRequest.getUsername()));
        }
        return ResponseEntity.ok("No such parking slot present with id" + parkingRequest.getId());
    }

    @Override
    public ResponseEntity<?> deallocateParkingSlot(Long id) {
        Optional<ParkingSlot> parkingSlot = parkingSlotRepository.findById(id);

        if (parkingSlot.isPresent()) {
            ParkingSlot parkingSlot1 = parkingSlot.get();
            if (parkingSlot1.getUsername() == null) {
                return ResponseEntity.badRequest().body(new MessageResponse("Parking Slot already empty"));
            }

            History history = new History();
            history.setParkingSlotId(parkingSlot1.getId());
            history.setParkingSlotName(parkingSlot1.getName());
            history.setUsername(parkingSlot1.getUsername());
            history.setVehicleRegisterationNumber(parkingSlot1.getVehicleRegisterationNumber());
            history.setEntryTime(parkingSlot1.getEntryTime());
            history.setExitTime(LocalDateTime.now());
            historyRepository.save(history);

            parkingSlot1.setUsername(null);
            parkingSlot1.setVehicleRegisterationNumber(null);
            parkingSlot1.setEntryTime(null);
            parkingSlotRepository.save(parkingSlot1);
            return ResponseEntity.ok(new MessageResponse("Parking Slot now free!"));
        }
        return ResponseEntity.ok("Parking slot is already free");
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
