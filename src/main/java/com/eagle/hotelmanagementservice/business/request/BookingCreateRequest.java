package com.eagle.hotelmanagementservice.business.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingCreateRequest {
	@NotNull(message = "Das Check-in Datum darf nicht leer sein")
	private LocalDate checkInDate;
	@NotNull(message = "Das Check-in Datum darf nicht leer sein")
	private LocalDate checkOutDate;
	@PositiveOrZero(message = "Der Diskonzsatz muss Zahlen sein")
	private double discountRate;
	@PositiveOrZero(message = "Die Zahlung muss Zahlen sein")
	private double payment;
	private long bookingStatusId;
	private long customerId;
	private long roomId;
}