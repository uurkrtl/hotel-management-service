package com.eagle.hotelmanagementservice.dataaccess.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.eagle.hotelmanagementservice.entities.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

	List<Room> findByIsActive(boolean active, Sort by);

	boolean existsByName(String name);

	Room findByName(String name);

	List<Room> findAllByOrderByNameAsc();

}
