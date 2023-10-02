package com.eagle.hotelmanagementservice.business.abstracts;

import java.util.List;

import com.eagle.hotelmanagementservice.business.request.RoomCreateRequest;
import com.eagle.hotelmanagementservice.business.request.RoomUpdateRequest;
import com.eagle.hotelmanagementservice.business.response.RoomGetByIdResponse;
import com.eagle.hotelmanagementservice.business.response.RoomsGetAllResponse;

public interface RoomService {
	public List<RoomsGetAllResponse> getAllRooms();
	public List<RoomsGetAllResponse> getByActiveRooms(boolean active);
	public RoomGetByIdResponse getByIdRoom(long id);
	public boolean addRoom(RoomCreateRequest roomCreateRequest);
	public boolean updateRoom(RoomUpdateRequest roomUpdateRequest);
	public boolean makePassiveRoom (long id);
	public boolean makeActiveRoom (long id);
}