package com.eagle.hotelmanagementservice.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.eagle.hotelmanagementservice.business.abstracts.RoomService;
import com.eagle.hotelmanagementservice.business.request.RoomCreateRequest;
import com.eagle.hotelmanagementservice.business.request.RoomUpdateRequest;
import com.eagle.hotelmanagementservice.business.response.RoomGetByIdResponse;
import com.eagle.hotelmanagementservice.business.response.RoomsGetAllResponse;
import com.eagle.hotelmanagementservice.business.rules.RoomBusinessRules;
import com.eagle.hotelmanagementservice.core.utilities.mappers.ModelMapperService;
import com.eagle.hotelmanagementservice.dataaccess.abstracts.RoomRepository;
import com.eagle.hotelmanagementservice.entities.Room;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;

@Service
@AllArgsConstructor
public class RoomManager implements RoomService {
	private RoomRepository roomRepository;
	private ModelMapperService modelMapperService;
	private RoomBusinessRules roomBusinessRules;

	@Override
	public List<RoomsGetAllResponse> getAllRooms() {
		List<Room> rooms = this.roomRepository.findAllByOrderByNameAsc();
		List<RoomsGetAllResponse> roomsGetAllRespons = rooms.stream().map(room->this.modelMapperService.forResponse().map(room, RoomsGetAllResponse.class)).collect(Collectors.toList());
		return roomsGetAllRespons;
	}

	@Override
	public List<RoomsGetAllResponse> getByActiveRooms(boolean active) {
		List<Room> rooms = this.roomRepository.findByIsActive(active, Sort.by(Sort.Direction.ASC, "name"));
		List<RoomsGetAllResponse> roomsGetAllRespons = rooms.stream().map(room->this.modelMapperService.forResponse().map(room, RoomsGetAllResponse.class)).collect(Collectors.toList());
		return roomsGetAllRespons;
	}

	@Override
	public RoomGetByIdResponse getByIdRoom(long id) {
		Room room = this.roomRepository.findById(id).orElse(null);
		this.roomBusinessRules.checkIfRoomNull(room, id);
		RoomGetByIdResponse roomGetByIdResponse = this.modelMapperService.forResponse().map(room, RoomGetByIdResponse.class);
		return roomGetByIdResponse;
	}

	@Override
	public boolean addRoom(RoomCreateRequest roomCreateRequest) {
		this.roomBusinessRules.checkIfRoomNameExists(roomCreateRequest.getName());
		Room room = this.modelMapperService.forRequest().map(roomCreateRequest, Room.class);
		room.setActive(true);
		this.roomRepository.save(room);
		return true;
	}

	@Override
	public boolean updateRoom(RoomUpdateRequest roomUpdateRequest) {
		long selectedRoomId = roomUpdateRequest.getId();
		this.roomBusinessRules.checkIfRoomIdNotExists(selectedRoomId);
		Room room = this.modelMapperService.forRequest().map(roomUpdateRequest, Room.class);
		Room selectedRoom = this.roomRepository.findById(selectedRoomId).orElse(room);
		room.setActive(selectedRoom.isActive());
		this.roomRepository.save(room);
		return true;
	}

	@Override
	public boolean makePassiveRoom(long id) {
		this.roomBusinessRules.checkIfRoomIdNotExists(id);
		Room room = this.roomRepository.findById(id).orElse(null);
		room.setActive(false);
		this.roomRepository.save(room);
		return true;
	}

	@Override
	public boolean makeActiveRoom(long id) {
		this.roomBusinessRules.checkIfRoomIdNotExists(id);
		Room room = this.roomRepository.findById(id).orElse(null);
		room.setActive(true);
		this.roomRepository.save(room);
		return true;
	}
}