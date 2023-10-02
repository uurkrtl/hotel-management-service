package com.eagle.hotelmanagementservice.business.abstracts;

import java.util.List;

import com.eagle.hotelmanagementservice.business.request.BookingCreateRequest;
import com.eagle.hotelmanagementservice.business.request.BookingUpdateRequest;
import com.eagle.hotelmanagementservice.business.response.BookingGetByIdResponse;
import com.eagle.hotelmanagementservice.business.response.BookingsGetAllResponse;

public interface BookingService {
	public List<BookingsGetAllResponse> getAllBookings();
	public List<BookingsGetAllResponse> getByStatusBookings(long statusId);
	public List<BookingsGetAllResponse> getByCustomerBookings(long customerId);
	public List<BookingsGetAllResponse> getByRoomBookings(long roomId);
	public BookingGetByIdResponse getByIdBooking(long id);
	public boolean addBooking(BookingCreateRequest bookingCreateRequest);
	public boolean updateBooking(BookingUpdateRequest bookingUpdateRequest);
	public boolean updateStatusBooking(long id, long statusId);
}