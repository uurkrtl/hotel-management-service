package com.eagle.hotelmanagementservice.webApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eagle.hotelmanagementservice.business.abstracts.RoomService;
import com.eagle.hotelmanagementservice.business.request.RoomCreateRequest;
import com.eagle.hotelmanagementservice.business.request.RoomUpdateRequest;
import com.eagle.hotelmanagementservice.business.response.RoomGetByIdResponse;
import com.eagle.hotelmanagementservice.business.response.RoomsGetAllResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class RoomController {
	private RoomService roomService;
	
	@GetMapping("/getAllRooms")
	public List<RoomsGetAllResponse> getAllRooms() {
		return this.roomService.getAllRooms();
	}
	
	@GetMapping("/getByActiveRooms")
	public List<RoomsGetAllResponse> getByActiveRooms(@RequestParam boolean active) {
		return this.roomService.getByActiveRooms(active);
	}
	
	@GetMapping("/getByIdRoom")
	public RoomGetByIdResponse getByIdRoom(@RequestParam long id) {
		return this.roomService.getByIdRoom(id);
	}
	
	@PostMapping("/addRoom")
	public boolean addRoom(@Valid @RequestBody RoomCreateRequest roomCreateRequest) {
		return this.roomService.addRoom(roomCreateRequest);
	}
	
	@PutMapping("/updateRoom")
	public boolean updateRoom(@Valid @RequestBody RoomUpdateRequest roomUpdateRequest) {
		return this.roomService.updateRoom(roomUpdateRequest);
	}
	
	@PutMapping("makePassiveRoom")
	public boolean makePassiveRoom(@RequestParam long id) {
		return this.roomService.makePassiveRoom(id);
	}
	
	@PutMapping("makeActiveRoom")
	public boolean makeActiveRoom(@RequestParam long id) {
		return this.roomService.makeActiveRoom(id);
	}
}