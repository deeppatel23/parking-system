package com.example.parking.system.payload.request;

import com.example.parking.system.models.Esize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingRequest {

    @NotNull
    private Long id;

    @NotBlank
    @Size(max = 4)
    private String floor;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 20)
    private String vehicleRegisterationNumber;

}
