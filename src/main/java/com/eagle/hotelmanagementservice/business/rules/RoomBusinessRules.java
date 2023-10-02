package com.eagle.hotelmanagementservice.business.rules;

import org.springframework.stereotype.Service;

import com.eagle.hotelmanagementservice.core.exception.BadRequestException;
import com.eagle.hotelmanagementservice.core.exception.NotFoundException;
import com.eagle.hotelmanagementservice.dataaccess.abstracts.RoomRepository;
import com.eagle.hotelmanagementservice.entities.Room;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoomBusinessRules {
	private RoomRepository roomRepository;
	
	public void checkIfRoomNull(Room room, long id) {
		if(room==null) {
			throw new NotFoundException("Das Zimmer nicht gefunden. Gesuchte ID: " + id);
		}
	}
	
	public void checkIfRoomNameExists(String name) {
		if(this.roomRepository.existsByName(name)) {
			Room room = this.roomRepository.findByName(name);
			throw new BadRequestException("Der Name vom Zimmer existiert bereits. Zimmer-ID: " + room.getId());
		}
	}
	
	public void checkIfRoomIdNotExists(long id) {
		if(!this.roomRepository.existsById(id)) {
			throw new NotFoundException("Das Zimmer nicht gefunden. Gesuchte ID: " + id);
		}
	}
	
	public void checkIfRoomPassiv(long id) {
		Room room = this.roomRepository.findById(id).orElse(null);
		if(!room.isActive()) {
			throw new BadRequestException("Das Zimmer ist pasiv. Ausgewählte ID: " + id);
		}
	}

}
