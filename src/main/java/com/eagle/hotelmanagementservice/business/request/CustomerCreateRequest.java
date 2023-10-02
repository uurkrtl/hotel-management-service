package com.eagle.hotelmanagementservice.business.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreateRequest {
	@NotBlank(message = "Der Vorname darf nicht leer sein")
	private String firstName;
	@NotBlank(message = "Der Nachname darf nicht leer sein")
	private String lastName;
	@NotBlank(message = "Die Adresse darf nicht leer sein")
	private String address;
	@Positive(message = "Die Postleitzahl muss Zahlen sein")
	private String postCode;
	@NotBlank(message = "Die Stadt darf nicht leer sein")
	private String city;
	@Positive(message = "Die Telefon- oder Handynummer muss Zahlen sein")
	private String phone;
}