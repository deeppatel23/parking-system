package com.example.parking.system.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse {
	private Long id;
	private String username;
	private String email;
	private String mobile;
	private String drivingLisence;
	private List<String> roles;
}
