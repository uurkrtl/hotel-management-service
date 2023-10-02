package com.eagle.hotelmanagementservice.business.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingGetByIdResponse {
	private long id;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private double roomPrice;
	private double discountRate;
	private double discountedPrice;
	private double payment;
	private double debt;
	private long bookingStatusId;
	private String bookingStatusName;
	private long CustomerId;
	private String customerFirstName;
	private String customerLastName;
	private long roomId;
	private String roomName;
}