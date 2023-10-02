package com.eagle.hotelmanagementservice.business.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomsGetAllResponse {
	private long id;
	private String name;
	private String floor;
	private double price;
	private boolean isActive;
	//private List<BookingsGetAllResponse> bookingsGetAllRespons;
}