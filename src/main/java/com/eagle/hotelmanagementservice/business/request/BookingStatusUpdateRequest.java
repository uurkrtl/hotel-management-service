package com.eagle.hotelmanagementservice.business.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingStatusUpdateRequest {
	private long id;
	@NotBlank(message = "Der Statusname darf nicht leer sein")
	private String name;
}