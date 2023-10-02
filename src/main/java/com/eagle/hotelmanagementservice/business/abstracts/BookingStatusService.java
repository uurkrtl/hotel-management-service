package com.eagle.hotelmanagementservice.business.abstracts;

import java.util.List;

import com.eagle.hotelmanagementservice.business.request.BookingStatusCreateRequest;
import com.eagle.hotelmanagementservice.business.request.BookingStatusUpdateRequest;
import com.eagle.hotelmanagementservice.business.response.BookingStatusGetByIdResponse;
import com.eagle.hotelmanagementservice.business.response.BookingStatusesGetAllResponse;

public interface BookingStatusService {
	public List<BookingStatusesGetAllResponse> getAllBookingStatuses();
	public List<BookingStatusesGetAllResponse> getByActiveBookingStatuses(boolean active);
	public BookingStatusGetByIdResponse getByIdBookingStatus(long id);
	public boolean addBookingStatus(BookingStatusCreateRequest bookingStatusCreateRequest);
	public boolean updateBookingStatus (BookingStatusUpdateRequest bookingStatusUpdateRequest);
	public boolean makePassiveBookingStatus (long id);
	public boolean makeActiveBookingStatus (long id);
}