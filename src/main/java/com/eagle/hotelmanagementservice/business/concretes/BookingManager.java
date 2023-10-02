package com.eagle.hotelmanagementservice.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eagle.hotelmanagementservice.business.abstracts.BookingService;
import com.eagle.hotelmanagementservice.business.request.BookingCreateRequest;
import com.eagle.hotelmanagementservice.business.request.BookingUpdateRequest;
import com.eagle.hotelmanagementservice.business.response.BookingGetByIdResponse;
import com.eagle.hotelmanagementservice.business.response.BookingsGetAllResponse;
import com.eagle.hotelmanagementservice.business.rules.BookingBusinessRules;
import com.eagle.hotelmanagementservice.business.rules.BookingStatusBusinessRules;
import com.eagle.hotelmanagementservice.business.rules.CustomerBusinessRules;
import com.eagle.hotelmanagementservice.business.rules.RoomBusinessRules;
import com.eagle.hotelmanagementservice.core.utilities.mappers.ModelMapperService;
import com.eagle.hotelmanagementservice.dataaccess.abstracts.BookingRepository;
import com.eagle.hotelmanagementservice.dataaccess.abstracts.BookingStatusRepository;
import com.eagle.hotelmanagementservice.entities.Booking;
import com.eagle.hotelmanagementservice.entities.BookingStatus;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookingManager implements BookingService {
	private BookingRepository bookingRepository;
	private BookingStatusRepository bookingStatusRepository;
	private ModelMapperService modelMapperService;
	private BookingBusinessRules bookingBusinessRules;
	private BookingStatusBusinessRules bookingStatusBusinessRules;
	private CustomerBusinessRules customerBusinessRules;
	private RoomBusinessRules roomBusinessRules;

	@Override
	public List<BookingsGetAllResponse> getAllBookings() {
		List<Booking> bookings = this.bookingRepository.findAll();
		return bookingBusinessRules.listMapAndpriceCalculate(bookings);
	}

	@Override
	public List<BookingsGetAllResponse> getByStatusBookings(long statusId) {
		this.bookingStatusBusinessRules.checkIfBookingStatusIdNotExists(statusId);
		
		List<Booking> bookings = this.bookingRepository.findByBookingStatusId(statusId);
		return bookingBusinessRules.listMapAndpriceCalculate(bookings);
	}
	
	@Override
	public List<BookingsGetAllResponse> getByCustomerBookings(long customerId){
		this.customerBusinessRules.checkIfCustomerIdNotExists(customerId);
		
		List<Booking> bookings = this.bookingRepository.findByCustomerId(customerId);
		return bookingBusinessRules.listMapAndpriceCalculate(bookings);
	}
	
	@Override
	public List<BookingsGetAllResponse> getByRoomBookings(long roomId){
		this.roomBusinessRules.checkIfRoomIdNotExists(roomId);
		
		List<Booking> bookings = this.bookingRepository.findByRoomId(roomId);
		return bookingBusinessRules.listMapAndpriceCalculate(bookings);
	}

	@Override
	public BookingGetByIdResponse getByIdBooking(long id) {
		this.bookingBusinessRules.checkIfBookingIdNotExists(id);
		
		Booking booking = this.bookingRepository.findById(id).orElse(null);
		BookingGetByIdResponse bookingGetByIdResponse = this.modelMapperService.forResponse().map(booking, BookingGetByIdResponse.class);
		bookingGetByIdResponse.setDiscountedPrice((bookingGetByIdResponse.getRoomPrice()-(bookingGetByIdResponse.getRoomPrice()/100*bookingGetByIdResponse.getDiscountRate())));
		bookingGetByIdResponse.setDebt(bookingGetByIdResponse.getDiscountedPrice()-bookingGetByIdResponse.getPayment());
		return bookingGetByIdResponse;
	}

	@Override
	public boolean addBooking(BookingCreateRequest bookingCreateRequest) {
		this.roomBusinessRules.checkIfRoomIdNotExists(bookingCreateRequest.getRoomId());
		this.roomBusinessRules.checkIfRoomPassiv(bookingCreateRequest.getRoomId());
		this.customerBusinessRules.checkIfCustomerIdNotExists(bookingCreateRequest.getCustomerId());
		this.customerBusinessRules.checkIfCustomerPassiv(bookingCreateRequest.getCustomerId());
		this.bookingStatusBusinessRules.checkIfBookingStatusIdNotExists(bookingCreateRequest.getBookingStatusId());
		this.bookingStatusBusinessRules.checkIfBookingStatusPassiv(bookingCreateRequest.getBookingStatusId());
		
		Booking booking = this.modelMapperService.forRequest().map(bookingCreateRequest, Booking.class);
		this.bookingBusinessRules.checkIfPaymentMoreThanDebt(booking);
		this.bookingRepository.save(booking);
		return true;
	}

	@Override
	public boolean updateBooking(BookingUpdateRequest bookingUpdateRequest) {
		long selectedBookingId = bookingUpdateRequest.getId();
		this.bookingBusinessRules.checkIfBookingIdNotExists(selectedBookingId);
		this.roomBusinessRules.checkIfRoomIdNotExists(bookingUpdateRequest.getRoomId());
		
		Booking booking = this.modelMapperService.forRequest().map(bookingUpdateRequest, Booking.class);
		this.bookingBusinessRules.checkIfPaymentMoreThanDebt(booking);
		Booking selectedBooking = this.bookingRepository.findById(selectedBookingId).orElse(null);
		booking.setBookingStatus(selectedBooking.getBookingStatus());
		booking.setCustomer(selectedBooking.getCustomer());
		this.bookingRepository.save(booking);
		return true;
	}

	@Override
	public boolean updateStatusBooking(long id, long statusId) {
		this.bookingBusinessRules.checkIfBookingIdNotExists(id);
		this.bookingStatusBusinessRules.checkIfBookingStatusIdNotExists(statusId);
		this.bookingStatusBusinessRules.checkIfBookingStatusPassiv(statusId);
		
		Booking booking = this.bookingRepository.findById(id).orElse(null);
		BookingStatus incomingStatus = this.bookingStatusRepository.findById(statusId).orElse(null);
		booking.setBookingStatus(incomingStatus);
		this.bookingRepository.save(booking);
		return true;
	}
}