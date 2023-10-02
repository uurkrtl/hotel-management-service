package com.eagle.hotelmanagementservice.business.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomCreateRequest {
	@NotBlank(message = "Der Zimmername darf nicht leer sein")
	private String name;
	@NotBlank(message = "Der Stockwerksnummer darf nicht leer sein")
	private String floor;
	@Positive(message = "Der Preis muss Zahlen sein")
	private double price;
}