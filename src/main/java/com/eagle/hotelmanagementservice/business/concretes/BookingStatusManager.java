package com.eagle.hotelmanagementservice.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.eagle.hotelmanagementservice.business.abstracts.BookingStatusService;
import com.eagle.hotelmanagementservice.business.request.BookingStatusCreateRequest;
import com.eagle.hotelmanagementservice.business.request.BookingStatusUpdateRequest;
import com.eagle.hotelmanagementservice.business.response.BookingStatusGetByIdResponse;
import com.eagle.hotelmanagementservice.business.response.BookingStatusesGetAllResponse;
import com.eagle.hotelmanagementservice.business.rules.BookingStatusBusinessRules;
import com.eagle.hotelmanagementservice.core.utilities.mappers.ModelMapperService;
import com.eagle.hotelmanagementservice.dataaccess.abstracts.BookingStatusRepository;
import com.eagle.hotelmanagementservice.entities.BookingStatus;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookingStatusManager implements BookingStatusService {
	private BookingStatusRepository bookingStatusRepository;
	private ModelMapperService modelMapperService;
	private BookingStatusBusinessRules bookingStatusBusinessRules;

	@Override
	public List<BookingStatusesGetAllResponse> getAllBookingStatuses() {
		List<BookingStatus> bookingStatuses = this.bookingStatusRepository.findAll();
		List<BookingStatusesGetAllResponse> bookingStatusesGetAllResponses = bookingStatuses.stream().map(bookingStatus->this.modelMapperService.forResponse().map(bookingStatus, BookingStatusesGetAllResponse.class)).collect(Collectors.toList());
		return bookingStatusesGetAllResponses;
	}
	
	@Override
	public List<BookingStatusesGetAllResponse> getByActiveBookingStatuses(boolean active) {
		List<BookingStatus> bookingStatuses = this.bookingStatusRepository.findByIsActive(active);
		List<BookingStatusesGetAllResponse> bookingStatusesGetAllResponses = bookingStatuses.stream().map(bookingStatus->this.modelMapperService.forResponse().map(bookingStatus, BookingStatusesGetAllResponse.class)).collect(Collectors.toList());
		return bookingStatusesGetAllResponses;
	}

	@Override
	public BookingStatusGetByIdResponse getByIdBookingStatus(long id) {
		BookingStatus bookingStatus = this.bookingStatusRepository.findById(id).orElse(null);
		this.bookingStatusBusinessRules.checkIfBookingStatusNull(bookingStatus, id);
		BookingStatusGetByIdResponse bookingStatusGetByIdResponse = this.modelMapperService.forResponse().map(bookingStatus, BookingStatusGetByIdResponse.class);
		return bookingStatusGetByIdResponse;
	}

	@Override
	public boolean addBookingStatus(BookingStatusCreateRequest bookingStatusCreateRequest) {
		this.bookingStatusBusinessRules.checkIfBookingStatusNameExists(bookingStatusCreateRequest.getName());
		BookingStatus bookingStatus = this.modelMapperService.forRequest().map(bookingStatusCreateRequest, BookingStatus.class);
		bookingStatus.setActive(true);
		this.bookingStatusRepository.save(bookingStatus);
		return true;
	}

	@Override
	public boolean updateBookingStatus(BookingStatusUpdateRequest bookingStatusUpdateRequest) {
		long selectedBookingStatusId = bookingStatusUpdateRequest.getId();
		this.bookingStatusBusinessRules.checkIfBookingStatusIdNotExists(selectedBookingStatusId);
		BookingStatus selectedBookingStatus = this.bookingStatusRepository.findById(selectedBookingStatusId).orElse(null);
		BookingStatus bookingStatus = this.modelMapperService.forRequest().map(bookingStatusUpdateRequest, BookingStatus.class);
		bookingStatus.setActive(selectedBookingStatus.isActive());
		this.bookingStatusRepository.save(bookingStatus);
		return true;
	}

	@Override
	public boolean makePassiveBookingStatus(long id) {
		this.bookingStatusBusinessRules.checkIfBookingStatusIdNotExists(id);
		BookingStatus bookingStatus = this.bookingStatusRepository.findById(id).orElse(null);
		bookingStatus.setActive(false);
		this.bookingStatusRepository.save(bookingStatus);
		return true;
	}

	@Override
	public boolean makeActiveBookingStatus(long id) {
		this.bookingStatusBusinessRules.checkIfBookingStatusIdNotExists(id);
		BookingStatus bookingStatus = this.bookingStatusRepository.findById(id).orElse(null);
		bookingStatus.setActive(true);
		this.bookingStatusRepository.save(bookingStatus);
		return true;
	}
}