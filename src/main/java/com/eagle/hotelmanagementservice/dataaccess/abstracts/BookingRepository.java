package com.eagle.hotelmanagementservice.dataaccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagle.hotelmanagementservice.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

	List<Booking> findByBookingStatusId(long statusId);

	List<Booking> findByCustomerId(long customerId);

	List<Booking> findByRoomId(long roomId);

}
