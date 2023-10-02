package com.eagle.hotelmanagementservice.business.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomersGetAllResponse {
	private long id;
	private String firstName;
	private String lastName;
	private String address;
	private String postCode;
	private String city;
	private String phone;
	private boolean isActive;
	//private List<BookingsGetAllResponse> bookingsGetAllRespons;
}