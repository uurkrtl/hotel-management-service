package com.eagle.hotelmanagementservice.business.rules;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.eagle.hotelmanagementservice.business.response.BookingsGetAllResponse;
import com.eagle.hotelmanagementservice.core.exception.BadRequestException;
import com.eagle.hotelmanagementservice.core.exception.NotFoundException;
import com.eagle.hotelmanagementservice.core.utilities.mappers.ModelMapperService;
import com.eagle.hotelmanagementservice.dataaccess.abstracts.BookingRepository;
import com.eagle.hotelmanagementservice.dataaccess.abstracts.RoomRepository;
import com.eagle.hotelmanagementservice.entities.Booking;
import com.eagle.hotelmanagementservice.entities.Room;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookingBusinessRules {
	private BookingRepository bookingRepository;
	private RoomRepository roomRepository;
	private ModelMapperService modelMapperService;
	
	public List<BookingsGetAllResponse> listMapAndpriceCalculate(List<Booking> bookings) {
		List<BookingsGetAllResponse> bookingsGetAllRespons = bookings.stream().map(booking->this.modelMapperService.forResponse().map(booking, BookingsGetAllResponse.class)).collect(Collectors.toList());		
		for(BookingsGetAllResponse booking:bookingsGetAllRespons) {
			booking.setDiscountedPrice((booking.getRoomPrice()-(booking.getRoomPrice()/100*booking.getDiscountRate())));
			booking.setDebt(booking.getDiscountedPrice()-booking.getPayment());
		}
		return bookingsGetAllRespons;
	}
	
	public void checkIfBookingIdNotExists(long id) {
		if(!this.bookingRepository.existsById(id)) {
			throw new NotFoundException("Die Buchung nicht gefunden. Gesuchte ID: " + id);
		}
	}
	
	public void checkIfPaymentMoreThanDebt(Booking booking) {
		Room room = this.roomRepository.findById(booking.getRoom().getId()).orElse(null);
		double roomPrice = room.getPrice();
		double discountRate = booking.getDiscountRate();
		double payment = booking.getPayment();
		double debt = (roomPrice - (roomPrice/100*discountRate)) - payment;
		if(debt<0) {
			throw new BadRequestException("Die Zahlung darf die Schulden nicht übersteigen");
		}
	}

}
