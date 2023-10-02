package com.eagle.hotelmanagementservice.business.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingStatusGetByIdResponse {
	private long id;
	private String name;
	private boolean isActive;
}