package com.eagle.hotelmanagementservice.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "customers")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "postCode")
	private String postCode;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "isActive")
	private boolean isActive;
	
	@OneToMany(mappedBy = "customer")
	private List<Booking> bookings;
}