package com.eagle.hotelmanagementservice.business.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingStatusCreateRequest {
	@NotBlank(message = "Der Statusname darf nicht leer sein")
	private String name;
}