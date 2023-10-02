package com.eagle.hotelmanagementservice.business.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingUpdateRequest {
	private long id;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private double discountRate;
	private double payment;
	private long roomId;
}