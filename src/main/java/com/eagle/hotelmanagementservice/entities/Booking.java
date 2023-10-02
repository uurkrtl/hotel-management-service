package com.eagle.hotelmanagementservice.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "bookings")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "check_in_date")
	private LocalDate checkInDate;
	
	@Column(name = "check_out_date")
	private LocalDate checkOutDate;
	
	@Column(name = "discount_rate")
	private double discountRate;
	
	@Column(name = "payment")
	private double payment;
	
	@ManyToOne
	@JoinColumn(name = "status_id")
	private BookingStatus bookingStatus;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;
}