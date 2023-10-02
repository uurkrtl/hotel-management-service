package com.eagle.hotelmanagementservice.webApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eagle.hotelmanagementservice.business.abstracts.BookingService;
import com.eagle.hotelmanagementservice.business.request.BookingCreateRequest;
import com.eagle.hotelmanagementservice.business.request.BookingUpdateRequest;
import com.eagle.hotelmanagementservice.business.response.BookingGetByIdResponse;
import com.eagle.hotelmanagementservice.business.response.BookingsGetAllResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class BookingController {
	private BookingService bookingService;
	
	@GetMapping("/getAllBookings")
	public List<BookingsGetAllResponse> getAllBookings(){
		return this.bookingService.getAllBookings();
	}
	
	@GetMapping("/getByStatusBookings/{statusId}")
	public List<BookingsGetAllResponse> getByStatusBookings(@RequestParam long statusId){
		return this.bookingService.getByStatusBookings(statusId);
	}
	
	@GetMapping("/getByCustomerBookings/{customerId}")
	public List<BookingsGetAllResponse> getByCustomerBookings(@RequestParam long customerId){
		return this.bookingService.getByCustomerBookings(customerId);
	}
	
	@GetMapping("/getByRoomBookings/{roomId}")
	public List<BookingsGetAllResponse> getByRoomBookings(@RequestParam long roomId){
		return this.bookingService.getByRoomBookings(roomId);
	}
	
	@GetMapping("/getByIdBooking")
	public BookingGetByIdResponse getByIdBooking(@RequestParam long id) {
		return this.bookingService.getByIdBooking(id);
	}
	
	@PostMapping("/addBooking")
	public boolean addBooking(@Valid @RequestBody BookingCreateRequest bookingCreateRequest) {
		return this.bookingService.addBooking(bookingCreateRequest);
	}
	
	@PutMapping("/updateBooking")
	public boolean updateBooking(@Valid @RequestBody BookingUpdateRequest bookingUpdateRequest) {
		return this.bookingService.updateBooking(bookingUpdateRequest);
	}
	
	@PutMapping("/updateStatusBooking")
	public boolean updateStatusBooking(@RequestParam long id, @RequestParam long statusId) {
		return this.bookingService.updateStatusBooking(id, statusId);
	}
}