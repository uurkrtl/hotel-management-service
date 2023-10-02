package com.eagle.hotelmanagementservice.dataaccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagle.hotelmanagementservice.entities.BookingStatus;

public interface BookingStatusRepository extends JpaRepository<BookingStatus, Long> {

	List<BookingStatus> findByIsActive(boolean active);

	boolean existsByName(String name);

	BookingStatus findByName(String name);

}
