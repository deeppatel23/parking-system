package com.example.parking.system.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 10)
    private String name;

    @NotBlank
    @Size(max = 4)
    private String floor;

    @Size(max = 20)
    private String username = null;

    @Size(max = 20)
    private String vehicleRegisterationNumber = null;

    private LocalDateTime entryTime = null;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Esize size;

    public ParkingSlot(Esize size) {
        this.size = size;
    }
}
