package com.eagle.hotelmanagementservice.business.rules;

import org.springframework.stereotype.Service;

import com.eagle.hotelmanagementservice.core.exception.BadRequestException;
import com.eagle.hotelmanagementservice.core.exception.NotFoundException;
import com.eagle.hotelmanagementservice.dataaccess.abstracts.BookingStatusRepository;
import com.eagle.hotelmanagementservice.entities.BookingStatus;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookingStatusBusinessRules {
	private BookingStatusRepository bookingStatusRepository;
	
	public void checkIfBookingStatusNull(BookingStatus bookingStatus, long id) {
		if(bookingStatus==null) {
			throw new NotFoundException("Der Buchungsstatus nicht gefunden. Gesuchte ID: " + id);
		}
	}
	
	public void checkIfBookingStatusNameExists(String name) {
		if(this.bookingStatusRepository.existsByName(name)) {
			BookingStatus bookingStatus = this.bookingStatusRepository.findByName(name);
			throw new BadRequestException("Der Statusname existiert bereits. Gast-ID: " + bookingStatus.getId());
		}
	}
	
	public void checkIfBookingStatusIdNotExists(long id) {
		if(!this.bookingStatusRepository.existsById(id)) {
			throw new NotFoundException("Der Status nicht gefunden. Gesuchte ID: " + id);
		}
	}
	
	public void checkIfBookingStatusPassiv(long id) {
		BookingStatus bookingStatus = this.bookingStatusRepository.findById(id).orElse(null);
		if(!bookingStatus.isActive()) {
			throw new BadRequestException("Der Status ist pasiv. Ausgewählte ID: " + id);
		}
	}

}
