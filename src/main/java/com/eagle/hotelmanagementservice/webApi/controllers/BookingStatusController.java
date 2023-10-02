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

import com.eagle.hotelmanagementservice.business.abstracts.BookingStatusService;
import com.eagle.hotelmanagementservice.business.request.BookingStatusCreateRequest;
import com.eagle.hotelmanagementservice.business.request.BookingStatusUpdateRequest;
import com.eagle.hotelmanagementservice.business.response.BookingStatusGetByIdResponse;
import com.eagle.hotelmanagementservice.business.response.BookingStatusesGetAllResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class BookingStatusController {
	private BookingStatusService bookingStatusService;
	
	@GetMapping("/getAllBookingStatuses")
	public List<BookingStatusesGetAllResponse> getAllBookingStatuses(){
		return this.bookingStatusService.getAllBookingStatuses();
	}
	
	@GetMapping("/getByActiveBookingStatuses")
	public List<BookingStatusesGetAllResponse> getByActiveBookingStatuses(@RequestParam boolean active){
		return this.bookingStatusService.getByActiveBookingStatuses(active);
	}
	
	@GetMapping("/getByIdBookingStatus")
	public BookingStatusGetByIdResponse getByIdBookingStatus(@RequestParam long id) {
		return this.bookingStatusService.getByIdBookingStatus(id);
	}
	
	@PostMapping("/addBookingStatus")
	public boolean addBookingStatus(@Valid @RequestBody BookingStatusCreateRequest bookingStatusCreateRequest) {
		return this.bookingStatusService.addBookingStatus(bookingStatusCreateRequest);
	}
	
	@PutMapping("/updateBookingStatus")
	public boolean updateBookingStatus(@Valid @RequestBody BookingStatusUpdateRequest bookingStatusUpdateRequest) {
		return this.bookingStatusService.updateBookingStatus(bookingStatusUpdateRequest);
	}
	
	@PutMapping("/makePassiveBookingStatus")
	public boolean makePassiveBookingStatus(@RequestParam long id) {
		return this.bookingStatusService.makePassiveBookingStatus(id);
	}
	
	@PutMapping("/makeActiveBookingStatus")
	public boolean makeActiveBookingStatus(@RequestParam long id) {
		return this.bookingStatusService.makeActiveBookingStatus(id);
	}
}